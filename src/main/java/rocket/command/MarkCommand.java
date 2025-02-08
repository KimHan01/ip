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
    public void execute(TaskList list, Ui ui, Storage storage) {
        try {
            Task markedTask = list.mark(indexToMark);
            storage.updateStorage(list);
            ui.readMarkTask(markedTask);
        } catch (IndexOutOfBoundsException e) {
            ui.readInvalidMarkRequest();
        }
    }
}
