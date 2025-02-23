package rocket.command;

import rocket.storage.Storage;
import rocket.task.TaskList;
import rocket.ui.Ui;

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
        ui.read(getInvalidDateFormatResponse());
        return getInvalidDateFormatResponse();
    }

    /**
     * Returns a response to invalid date format.
     */
    private String getInvalidDateFormatResponse() {
        return "Invalid date format given. Please use yyyy-mm-dd";
    }
}
