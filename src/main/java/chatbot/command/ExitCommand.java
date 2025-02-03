package chatbot.command;

import chatbot.TaskList;
import chatbot.Ui;
import chatbot.chatbot.storage.Storage;

public class ExitCommand extends Command {
    public ExitCommand() {
        super(true);
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        ui.bye();
    }
}
