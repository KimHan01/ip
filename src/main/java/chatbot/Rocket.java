package chatbot;

import chatbot.chatbot.storage.Storage;
import chatbot.command.Command;
import chatbot.parser.Parser;

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
        String filePath = "src/main/java/chatbot/storage/storage.txt";
        new Rocket(filePath).run();
    }
}
