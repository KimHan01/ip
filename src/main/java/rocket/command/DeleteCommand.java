package rocket.command;

import rocket.storage.Storage;
import rocket.task.Task;
import rocket.task.TaskList;
import rocket.ui.Ui;

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
            storage.updateStorage(list);
            ui.readRemoveTask(removedTask, list.getSize());
        } catch (IndexOutOfBoundsException e) {
            ui.readInvalidRemoveTask();
        }
    }
}
