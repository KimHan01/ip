package rocket.command;

import rocket.storage.Storage;
import rocket.task.Deadline;
import rocket.task.Event;
import rocket.task.Task;
import rocket.task.TaskList;
import rocket.task.TaskType;
import rocket.task.Todo;
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
     * Returns an {@code AddCommand} based on the given task type.
     * @param input the user input string
     * @param taskType the type of task to be added
     * @return the appropriate AddCommand based on the task type,
     *     or an appropriate error command if the input is invalid
     */
    public static Command getAddCommand(String input, TaskType taskType) {
        if (taskType == TaskType.TODO) {
            return Todo.getAddTodoCommand(input);
        } else if (taskType == TaskType.DEADLINE) {
            return Deadline.getAddDeadlineCommand(input);
        } else if (taskType == TaskType.EVENT) {
            return Event.getAddEventCommand(input);
        }
        return new InvalidFormatCommand();
    }

    /**
     * Returns a response for successfully adding a task into the task list.
     */
    private String getAddTaskResponse(Task task, int listSize) {
        return "Successfully added:\n"
                + task.toString() + "\n"
                + "Now you have " + listSize + " tasks in the list";
    }
}
