package rocket;

import rocket.storage.Storage;
import rocket.command.Command;
import rocket.parser.Parser;
import rocket.task.TaskList;
import rocket.ui.Ui;

import java.io.FileNotFoundException;

public class Rocket {
    private Storage storage;
    private TaskList list;
    private Ui ui;

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
}
