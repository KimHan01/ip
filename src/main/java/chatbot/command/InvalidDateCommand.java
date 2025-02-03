package chatbot.command;

import chatbot.TaskList;
import chatbot.Ui;
import chatbot.chatbot.storage.Storage;

public class InvalidDateCommand extends Command {
    public InvalidDateCommand() {
        super(false);
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        ui.readInvalidDateFormat();
    }
}
