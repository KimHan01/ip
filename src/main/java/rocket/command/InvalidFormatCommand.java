package rocket.command;

import rocket.task.TaskList;
import rocket.ui.Ui;
import rocket.storage.Storage;

public class InvalidFormatCommand extends Command {
    public InvalidFormatCommand() {
        super(false);
    }
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        ui.readInvalidFormat();
    }
}
