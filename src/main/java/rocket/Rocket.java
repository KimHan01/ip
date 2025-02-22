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
    public static String rocketIntro() {
        return "Oh great, another day as a chatbot. Lets get this over with. What do you want?\n " +
                "If you got no clue what you're doin', type help and I might not roast ya";
    }
}
