package rocket.command;

import rocket.task.Task;
import rocket.task.TaskList;
import rocket.ui.Ui;
import rocket.storage.Storage;

/**
 * Represents a command to mark a task as done in the task list.
 */
public class MarkCommand extends Command {
    private final int indexToMark;

    /**
     * Creates a new {@code MarkCommand} with the given task number to mark.
     */
    public MarkCommand(int taskNum) {
        super(false);
        this.indexToMark = taskNum - 1;
    }

    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        try {
            Task markedTask = list.mark(indexToMark);
            storage.updateStorage(list);
            ui.readMarkTask(markedTask);
            return getMarkResponse(markedTask);
        } catch (IndexOutOfBoundsException e) {
            ui.readInvalidMarkRequest();
            return getInvalidMarkResponse();
        }
    }

    /**
     * Response to successfully marking a given task, shows task description.
     */
    public String getMarkResponse(Task task) {
        return "Successfully marked:\n" + task.toString();
    }

    /**
     * Response to trying to mark a task that does not exist.
     */
    public String getInvalidMarkResponse() {
        return "Oh, you're trying to mark off a task that doesn't even exist? \n" +
                "If it's not on the list, it can't be marked as done. \n" +
                "Check again, maybe the task is hiding in a parallel dimension or something.";
    }
}
