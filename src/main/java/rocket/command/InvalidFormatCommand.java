package rocket.command;

import rocket.task.TaskList;
import rocket.ui.Ui;
import rocket.storage.Storage;

/**
 * Represents a command to handle invalid format commands.
 */
public class InvalidFormatCommand extends Command {
    /**
     * Creates a new {@code InvalidFormatCommand}.
     */
    public InvalidFormatCommand() {
        super(false);
    }
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        ui.readInvalidFormat();
    }
}
