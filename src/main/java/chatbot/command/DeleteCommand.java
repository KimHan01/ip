package chatbot.command;

import chatbot.task.Task;
import chatbot.TaskList;
import chatbot.Ui;
import chatbot.chatbot.storage.Storage;

public class DeleteCommand extends Command {
    private final int indexToDelete;

    public DeleteCommand(int taskNum) {
        super(false);
        this.indexToDelete = taskNum - 1;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        try {
            Task removedTask = list.remove(indexToDelete);
            //storage.updateStorage(list);
            ui.readRemoveTask(removedTask, list.getSize());
        } catch (IndexOutOfBoundsException e) {
            ui.readInvalidRemoveTask();
        }
    }
}
