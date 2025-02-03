package rocket.command;

import rocket.task.TaskList;
import rocket.ui.Ui;
import rocket.storage.Storage;

public class EmptyTaskNameCommand extends Command {

    public EmptyTaskNameCommand() {
        super(false);
    }
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        ui.addEmptyTask();
    }
}
