package rocket.command;

import rocket.storage.Storage;
import rocket.task.Task;
import rocket.task.TaskList;
import rocket.ui.Ui;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private final int indexToDelete;

    /**
     * Creates a new {@code DeleteCommand} with the given task number to delete.
     */
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
