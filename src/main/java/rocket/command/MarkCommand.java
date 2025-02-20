package rocket.command;

import rocket.common.Utils;
import rocket.task.Task;
import rocket.task.TaskList;
import rocket.ui.Ui;
import rocket.storage.Storage;

/**
 * Represents a command to mark a task as done in the task list.
 */
public class MarkCommand extends Command {

    private final int indexToMark;

    /**
     * Creates a new {@code MarkCommand} with the given task number to mark.
     */
    public MarkCommand(int taskNum) {
        super(false);
        this.indexToMark = taskNum - 1;
    }

    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        try {
            Task markedTask = list.mark(indexToMark);
            storage.updateStorage(list);
            String res = getMarkResponse(markedTask);
            ui.read(res);
            return res;
        } catch (IndexOutOfBoundsException e) {
            ui.read(getInvalidMarkResponse());
            return getInvalidMarkResponse();
        }
    }

    /**
     * Checks if the given input is a {@code MarkCommand}.
     */
    public static boolean isMark(String input) {
        return input.length() > 5
                && input.substring(0, 4).equalsIgnoreCase(InputCommandType.MARK.name())
                && input.substring(4, 5).isBlank()
                && Utils.isInteger(input.substring(5));
    }

    /**
     * Returns the {@code MarkCommand} from the given input if valid,
     * otherwise an {@code InvalidFormatCommand}.
     */
    public static Command getMarkCommand(String input) {
        try {
            int taskNum = getTaskNumToMark(input);
            return new MarkCommand(taskNum);
        } catch (NumberFormatException e) {
            return new InvalidFormatCommand();
        }
    }

    /**
     * Returns a response to successfully marking a given task.
     */
    private String getMarkResponse(Task task) {
        return "Successfully marked:\n" + task.toString();
    }

    /**
     * Returns a response for trying to mark a task that is not in the task list.
     */
    private String getInvalidMarkResponse() {
        return "Oh, you're trying to mark off a task that doesn't even exist? \n" +
                "If it's not on the list, it can't be marked as done. \n" +
                "Check again, maybe the task is hiding in a parallel dimension or something.";
    }

    private static int getTaskNumToMark(String input) throws NumberFormatException {
        String taskNum = input.substring(InputCommandType.MARK.name().length() + 1);
        return Integer.parseInt(taskNum);
    }
}
