package rocket.command;

import rocket.common.Utils;
import rocket.storage.Storage;
import rocket.task.Task;
import rocket.task.TaskList;
import rocket.ui.Ui;

/**
 * Represents a command to unmark a task from the task list.
 */
public class UnmarkCommand extends Command {

    private final int indexToUnmark;

    /**
     * Creates a new {@code UnmarkCommand} with the given task number to unmark.
     */
    public UnmarkCommand(int taskNum) {
        super(false);
        this.indexToUnmark = taskNum - 1;
    }

    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        try {
            Task unmarkedTask = list.unmark(indexToUnmark);
            storage.updateStorage(list);
            String res = getUnmarkResponse(unmarkedTask);
            ui.read(res);
            return res;
        } catch (IndexOutOfBoundsException e) {
            ui.read(getInvalidUnmarkResponse());
            return getInvalidUnmarkResponse();
        }
    }

    /**
     * Checks if the given input is an {@code UnmarkCommand}.
     */
    public static boolean isUnmark(String input) {
        return input.length() > 7
                && input.substring(0, 6).equalsIgnoreCase(InputCommandType.UNMARK.name())
                && input.substring(6, 7).isBlank()
                && Utils.isInteger(input.substring(7));
    }

    /**
     * Returns the {@code UnmarkCommand} from the given input if valid,
     * otherwise an {@code InvalidFormatCommand}.
     */
    public static Command getUnmarkCommand(String input) {
        try {
            int taskNum = getTaskNumToUnmark(input);
            return new UnmarkCommand(taskNum);
        } catch (NumberFormatException e) {
            return new InvalidFormatCommand();
        }
    }

    /**
     * Returns a response to successfully unmarking a given task.
     */
    private String getUnmarkResponse(Task task) {
        return "Successfully unmarked:\n" + task.toString();
    }

    /**
     * Returns a response for trying to unmark a task that is not in the task list.
     */
    private String getInvalidUnmarkResponse() {
        return "You're trying to unmark a task that doesn't even exist? \n"
                + "That's some next-level overthinking right there. \n"
                + "Look, if it's not on the list, there's nothing to unmark. \n"
                + "Double-check your list, alright?";
    }

    private static int getTaskNumToUnmark(String input) throws NumberFormatException {
        String taskNum = input.substring(InputCommandType.UNMARK.name().length() + 1);
        return Integer.parseInt(taskNum);
    }
}
