package rocket.command;

import rocket.task.TaskList;
import rocket.ui.Ui;
import rocket.storage.Storage;

/**
 * Represents a command to handle invalid date format.
 */
public class InvalidDateCommand extends Command {
    /**
     * Creates a new {@code InvalidDateCommand}.
     */
    public InvalidDateCommand() {
        super(false);
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        ui.readInvalidDateFormat();
    }
}
