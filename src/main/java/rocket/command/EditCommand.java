package rocket.command;

import rocket.common.Utils;
import rocket.storage.Storage;
import rocket.task.Task;
import rocket.task.TaskList;
import rocket.ui.Ui;

/**
 * Represents a command to Edit a task
 */
public class EditCommand extends Command {
    private static final int headerLen = InputCommandType.EDIT.name().length() + 1;

    public EditCommand() {
        super(false);
    }

    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        return "EditCommand: Error reaching the right edit command";
    }

    /**
     * Checks if the input is an edit command
     */
    public static boolean isEdit(String input) {
        return input.length() > headerLen
                && input.substring(0, headerLen - 1).equalsIgnoreCase(InputCommandType.EDIT.name())
                && input.substring(headerLen - 1, headerLen).isBlank();
    }

    /**
     * Returns an {@code EditCommand} if the given input is a valid edit command,
     * otherwise the appropriate error command is returned.
     */
    public static Command getEditCommand(String input) {
        // input format should be "edit <index> <field to change>" (only 1 field to change expected)
        // Checks if the input is of this format, and if <index> and <field to change> are valid.
        String[] splitInput = input.split(" ", 3);
        if (splitInput.length != 3
                || !Utils.isInteger(splitInput[1])
                || !isFieldSpecifier(splitInput[2])) {
            return new InvalidFormatCommand();
        }

        int taskNum = Integer.parseInt(splitInput[1]);
        if (splitInput[2].startsWith("/n")) {
            String newName = splitInput[2].substring(2).trim();
            return new EditNameCommand(newName, taskNum);
        } else {
            String fieldToChange = splitInput[2].trim();
            return new EditDateCommand(fieldToChange, taskNum);
        }
    }

    private static boolean isFieldSpecifier(String str) {
        // Checks str against all valid field specifiers (/n, /by, /from, /to)
        String[] validFieldSpecifiers = {"/n", "/by", "/from", "/to"};
        for (String fieldSpecifier: validFieldSpecifiers) {
            if (str.startsWith(fieldSpecifier)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns successful edit message
     * @param oldTask The task before edit
     * @param newTask The task after edit
     */
    String getEditMessage(Task oldTask, Task newTask) {
        return "I have edited this task:\n" + oldTask.toString() + "\nTo:\n" + newTask.toString();
    }

    String getEmptyNameMessage() {
        return "Buddy, I can't do this with an empty name!";
    }

    String getIndexOutOfBoundsMessage() {
        return "That task ain't there, try again!";
    }

    String getClassCastExceptionMessage() {
        return "Get a grip on yourself, genius. Your trying to edit something that does not belong to the task!";
    }
}
