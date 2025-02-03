package rocket.command;

import rocket.task.Task;
import rocket.task.TaskList;
import rocket.ui.Ui;
import rocket.storage.Storage;

public class UnmarkCommand extends Command {
    private final int indexToUnmark;

    public UnmarkCommand(int taskNum) {
        super(false);
        this.indexToUnmark = taskNum - 1;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        try {
            Task unmarkedTask = list.unmark(indexToUnmark);
            storage.updateStorage(list);
            ui.readUnmarkTask(unmarkedTask);
        } catch (IndexOutOfBoundsException e) {
            ui.readInvalidUnmarkRequest();
        }
    }
}
