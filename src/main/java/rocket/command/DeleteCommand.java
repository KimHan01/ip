package rocket.command;

import rocket.storage.Storage;
import rocket.task.Task;
import rocket.task.TaskList;
import rocket.ui.Ui;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private static final int HEADER_LEN = InputCommandType.DELETE.name().length() + 1;
    private final int indexToDelete;

    /**
     * Creates a new {@code DeleteCommand} with the given task number to delete.
     */
    public DeleteCommand(int taskNum) {
        super(false);
        this.indexToDelete = taskNum - 1;
    }

    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        try {
            Task removedTask = list.remove(indexToDelete);
            storage.updateStorage(list);
            String res = getDeleteResponse(removedTask, list.getSize());
            ui.read(res);
            return res;
        } catch (IndexOutOfBoundsException e) {
            ui.read(getInvalidDeleteResponse());
            return getInvalidDeleteResponse();
        }
    }

    /**
     * Checks if the given input is a {@code DeleteCommand}.
     */
    public static boolean isDelete(String input) {
        return (input.length() > 7 && input.substring(0, 6).equalsIgnoreCase(InputCommandType.DELETE.name()));
    }

    /**
     * Returns the {@code DeleteCommand} from given input, otherwise an {@code InvalidFormatCommand}.
     */
    public static Command getDeleteCommand(String input) {
        try {
            int taskNum = getTaskNumToDelete(input);
            return new DeleteCommand(taskNum);
        } catch (NumberFormatException e) {
            return new InvalidFormatCommand();
        }
    }

    /**
     * Returns a response for successfully deleting a task from the task list.
     */
    private String getDeleteResponse(Task task, int listSize) {
        return "I've removed this task:\n" + task.toString()
                + "\n" + "Now you have " + listSize + " tasks in the list.";
    }

    /**
     * Returns a response for trying to delete a task that does not exist in the task list.
     */
    private String getInvalidDeleteResponse() {
        return "Yeah, that's not happening. The list has limits, just like my patience.\n"
                + "Check your list, make sure the task exists, and try again";
    }

    private static int getTaskNumToDelete(String input) throws NumberFormatException {
        String taskNum = input.substring(HEADER_LEN);
        return Integer.parseInt(taskNum);
    }
}
