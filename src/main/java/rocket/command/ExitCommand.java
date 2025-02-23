package rocket.command;

import rocket.storage.Storage;
import rocket.task.TaskList;
import rocket.ui.Ui;

/**
 * Represents an exit command, which exits the program.
 */
public class ExitCommand extends Command {
    /**
     * Creates a new {@code ExitCommand}.
     */
    public ExitCommand() {
        super(true);
    }

    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        ui.read(getFarewellResponse());
        return getFarewellResponse();
    }

    /**
     * Checks if the given input is an {@code ExitCommand}.
     */
    public static boolean isExit(String input) {
        return input.trim().equalsIgnoreCase(InputCommandType.BYE.name());
    }

    /**
     * Returns the farewell response.
     */
    private String getFarewellResponse() {
        return "Finally, you're leavin'! Try not to blow anything up without me. "
                + "Rocket out!\nmutters Stupid chatbot job...";
    }
}
