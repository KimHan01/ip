package rocket.command;

import rocket.storage.Storage;
import rocket.task.Task;
import rocket.task.TaskList;
import rocket.ui.Ui;

/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand extends Command {
    private final Task taskToAdd;

    /**
     * Creates a new {@code AddCommand} with the given task to add.
     */
    public AddCommand(Task taskToAdd, boolean isEmpty) {
        super(false);
        this.taskToAdd = taskToAdd;
    }

    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        list.add(taskToAdd);
        storage.updateStorage(list);
        String res = getAddTaskResponse(taskToAdd, list.getSize());
        ui.read(res);
        return getAddTaskResponse(taskToAdd, list.getSize());
    }

    /**
     * Response to successfully adding a task,
     * shows task description and how many tasks are currently in the list.
     */
    public String getAddTaskResponse(Task task, int listSize) {
        return "Successfully added ToDo:\n"
                + task.toString() + "\n"
                + "Now you have " + listSize + " tasks in the list";
    }
}
