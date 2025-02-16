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
    public String execute(TaskList list, Ui ui, Storage storage) {
        try {
            Task removedTask = list.remove(indexToDelete);
            storage.updateStorage(list);
            String res = getDeleteResponse(removedTask, list.getSize());
            ui.read(res);
            return res;
        } catch (IndexOutOfBoundsException e) {
            ui.read(getInvalidDeleteResponse());
            return getInvalidDeleteResponse();
        }
    }

    /**
     * Response to successfully deleting a task from the list,
     * shows task description and how many tasks are currently in the list.
     */
    public String getDeleteResponse(Task task, int listSize) {
        return "I've removed this task:\n" + task.toString()
                + "\n" + "Now you have " + listSize + " tasks in the list.";
    }

    /**
     * Response to trying to remove a task that does not exist.
     */
    public String getInvalidDeleteResponse() {
        return "Yeah, that's not happening. The list has limits, just like my patience.\n" +
                "Check your list, make sure the task exists, and try again";
    }
}
