package rocket;

import rocket.storage.Storage;
import rocket.command.Command;
import rocket.parser.Parser;
import rocket.task.TaskList;
import rocket.ui.Ui;

import java.io.FileNotFoundException;

/**
 * The main class which represents the chatbot {@link Rocket}.
 */
public class Rocket {
    private Storage storage;
    private TaskList list;
    private Ui ui;

    /**
     * Constructor for {@link Rocket} class,
     * creates a new {@code Rocket} chatbot with a given valid filePath
     */
    public Rocket(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            list = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.fileNotFound();
            list = new TaskList();
        }
    }

    /**
     * Runs the @{code Rocket} chatbot
     */
    public void run() {
        ui.introduction();
        boolean isExit = false;
        while (!isExit) {
            String input = ui.readInputCommand();
            Command c = Parser.parse(input);
            c.execute(list, ui, storage);
            isExit = c.isExit();
        }
    }

    public static void main(String[] args) {
        String filePath = "./data/storage.txt";
        new Rocket(filePath).run();
    }

    /**
     * Generates a response according to user input from chat message
     */
    public String getResponse(String input) {
        Command c = Parser.parse(input);
        return c.execute(list, ui, storage);
    }

    /**
     * Returns rocket's introduction
     */
    public String rocketIntro() {
        return "Oh, great. Another day, another gig as a chatbot. "
                + "Alright listen up, kid.\nName's Rocket. Rocket Raccoon. Yeah, the genius, weapons expert, "
                + "and the best pilot you'll ever meet.\nNow, I'm stuck here tracking your tasks instead of "
                + "blasting through the galaxy with Groot.\nSo, what do ya want? "
                + "Make it quick, I've got virtual raccoon stuff to do.";
    }
}
