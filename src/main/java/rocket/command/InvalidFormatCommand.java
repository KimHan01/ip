package rocket.command;

import rocket.storage.Storage;
import rocket.task.TaskList;
import rocket.ui.Ui;

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
    public String execute(TaskList list, Ui ui, Storage storage) {
        ui.read(getInvalidFormatResponse());
        return getInvalidFormatResponse();
    }

    /**
     * Returns a response to invalid input from user.
     */
    private String getInvalidFormatResponse() {
        return "Maybe you wanna try giving me something that makes sense,\n"
                + "unless you want me to invent something out of thin air,\n"
                + "which, trust me, you don't.\n"
                + "Type help if you need it.";
    }
}
