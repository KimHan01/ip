package rocket.command;

import rocket.task.TaskList;
import rocket.ui.Ui;
import rocket.storage.Storage;

/**
 * Represents a command that tries to add a task with an empty name to the task list.
 */
public class EmptyTaskNameCommand extends Command {
    /**
     * Creates a new {@code EmptyTaskNameCommand}.
     */
    public EmptyTaskNameCommand() {
        super(false);
    }
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        ui.addEmptyTask();
    }
}
