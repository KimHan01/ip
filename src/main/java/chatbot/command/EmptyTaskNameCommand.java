package chatbot.command;

import chatbot.TaskList;
import chatbot.Ui;
import chatbot.chatbot.storage.Storage;

public class EmptyTaskNameCommand extends Command {

    public EmptyTaskNameCommand() {
        super(false);
    }
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        ui.addEmptyTask();
    }
}
