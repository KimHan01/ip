package rocket.command;

import rocket.task.TaskList;
import rocket.ui.Ui;
import rocket.storage.Storage;

public class ExitCommand extends Command {
    public ExitCommand() {
        super(true);
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        ui.bye();
    }
}
