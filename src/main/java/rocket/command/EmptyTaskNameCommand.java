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
    public String execute(TaskList list, Ui ui, Storage storage) {
        ui.read(getAddEmptyTaskResponse());
        return getAddEmptyTaskResponse();
    }

    /**
     * Returns a response for adding a task with no name.
     */
    private String getAddEmptyTaskResponse() {
        return "Seriously? An empty task? What, are you trying to add \"nothing\" " +
                "to your to-do list?\n" +
                "Hate to break it to you, but that's not how this works.\n" +
                "Try adding something, even if it's \"stare at the wall dramatically.\"\n" +
                "At least give me something to work with here!";
    }
}
