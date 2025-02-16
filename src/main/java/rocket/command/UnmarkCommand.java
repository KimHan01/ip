package rocket.command;

import rocket.task.Task;
import rocket.task.TaskList;
import rocket.ui.Ui;
import rocket.storage.Storage;

/**
 * Represents a command to unmark a task from the task list.
 */
public class UnmarkCommand extends Command {
    private final int indexToUnmark;

    /**
     * Creates a new {@code UnmarkCommand} with the given task number to unmark.
     */
    public UnmarkCommand(int taskNum) {
        super(false);
        this.indexToUnmark = taskNum - 1;
    }

    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        try {
            Task unmarkedTask = list.unmark(indexToUnmark);
            storage.updateStorage(list);
            String res = getUnmarkResponse(unmarkedTask);
            ui.read(res);
            return res;
        } catch (IndexOutOfBoundsException e) {
            ui.read(getInvalidUnmarkReponse());
            return getInvalidUnmarkReponse();
        }
    }

    /**
     * Response to successfully unmarking a given task, shows task description.
     */
    public String getUnmarkResponse(Task task) {
        return "Successfully unmarked:\n" + task.toString();
    }

    /**
     * Response to trying to unmark a task that does not exist.
     */
    public String getInvalidUnmarkReponse() {
        return "You're trying to unmark a task that doesn't even exist? \n" +
                "That's some next-level overthinking right there. \n" +
                "Look, if it's not on the list, there's nothing to unmark. \n" +
                "Double-check your list, alright?";
    }
}
