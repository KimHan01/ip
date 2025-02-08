package rocket.command;

import rocket.storage.Storage;
import rocket.task.TaskList;
import rocket.ui.Ui;

/**
 * Represents a command from the user.
 */
public abstract class Command {
    private final boolean isExit; // Checks if the command is an exit command

    /**
     * Creates a new {@code Command}
     * @param isExit whether the command is an exit command
     */
    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Executes the command
     */
    public abstract void execute(TaskList list, Ui ui, Storage storage);
}
