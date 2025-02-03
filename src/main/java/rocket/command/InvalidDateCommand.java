package rocket.command;

import rocket.task.TaskList;
import rocket.ui.Ui;
import rocket.storage.Storage;

public class InvalidDateCommand extends Command {
    public InvalidDateCommand() {
        super(false);
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        ui.readInvalidDateFormat();
    }
}
