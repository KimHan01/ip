package rocket.command;

import rocket.storage.Storage;
import rocket.task.Task;
import rocket.task.TaskList;
import rocket.ui.Ui;

public class AddCommand extends Command {
    private final Task taskToAdd;

    public AddCommand(Task taskToAdd, boolean isEmpty) {
        super(false);
        this.taskToAdd = taskToAdd;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        list.add(taskToAdd);
        storage.updateStorage(list);
        ui.readAddTask(taskToAdd, list.getSize());
    }
}
