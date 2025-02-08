package rocket.command;

import rocket.task.TaskList;
import rocket.ui.Ui;
import rocket.storage.Storage;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Creates a new {@code ListCommand}.
     */
    public ListCommand() {
        super(false);
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        if (list.isEmpty()) {
            ui.readEmptyList();
        } else {
            ui.readListItems(list.getTasks());
        }
    }
}
