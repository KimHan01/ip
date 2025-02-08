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
    public void execute(TaskList list, Ui ui, Storage storage) {
        ui.bye();
    }
}
