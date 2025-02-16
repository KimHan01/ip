package rocket.command;

import rocket.task.TaskList;
import rocket.ui.Ui;
import rocket.storage.Storage;

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

    public String getFarewellResponse() {
        return "Alright, fine. You're leaving already? Whatever. " +
                "Just don't blow anything up without me. Rocket out!\nmutters Stupid chatbot job...";
    }
}
