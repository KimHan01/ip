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
    public String execute(TaskList list, Ui ui, Storage storage) {
        ui.readInvalidDateFormat();
        return getInvalidDateFormatResponse();
    }

    /**
     * Response to invalid date format given by user.
     */
    public String getInvalidDateFormatResponse() {
        return "Invalid date format given. Please use yyyy-mm-dd";
    }
}
