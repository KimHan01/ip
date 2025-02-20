package rocket.parser;

import rocket.command.EditCommand;
import rocket.task.Deadline;
import rocket.task.Event;
import rocket.task.TaskType;
import rocket.task.Todo;

import rocket.command.InputCommandType;
import rocket.command.Command;
import rocket.command.AddCommand;
import rocket.command.DeleteCommand;
import rocket.command.ExitCommand;
import rocket.command.FindCommand;
import rocket.command.InvalidFormatCommand;
import rocket.command.ListCommand;
import rocket.command.MarkCommand;
import rocket.command.UnmarkCommand;
import rocket.command.HelpCommand;

/**
 * Represents a parser that handles parsing user input into commands.
 */
public class Parser {

    /**
     * Parses user input into a command.
     * @return the appropriate Command based on the given input
     */
    public static Command parse(String input) {
        InputCommandType commandType = getCommandType(input);
        return switch (commandType) {
            case TODO -> AddCommand.getAddCommand(input, TaskType.TODO);
            case DEADLINE -> AddCommand.getAddCommand(input, TaskType.DEADLINE);
            case EVENT -> AddCommand.getAddCommand(input, TaskType.EVENT);
            case DELETE -> DeleteCommand.getDeleteCommand(input);
            case MARK -> MarkCommand.getMarkCommand(input);
            case UNMARK -> UnmarkCommand.getUnmarkCommand(input);
            case BYE -> new ExitCommand();
            case LIST -> new ListCommand();
            case FIND -> FindCommand.getFindCommand(input);
            case HELP -> new HelpCommand();
            case EDIT -> EditCommand.getEditCommand(input);
            default -> new InvalidFormatCommand();
        };
    }

    /**
     * Gets the command type from given input
     * @return The command type
     */
    private static InputCommandType getCommandType(String input) {
        if (Todo.isTodo(input)) {
            return InputCommandType.TODO;
        } else if (Deadline.isDeadline(input)) {
            return InputCommandType.DEADLINE;
        } else if (Event.isEvent(input)) {
            return InputCommandType.EVENT;
        } else if (DeleteCommand.isDelete(input)) {
            return InputCommandType.DELETE;
        } else if (MarkCommand.isMark(input)) {
            return InputCommandType.MARK;
        } else if (UnmarkCommand.isUnmark(input)) {
            return InputCommandType.UNMARK;
        } else if (ExitCommand.isExit(input)) {
            return InputCommandType.BYE;
        } else if (ListCommand.isList(input)) {
            return InputCommandType.LIST;
        } else if (FindCommand.isFind(input)) {
            return InputCommandType.FIND;
        } else if (HelpCommand.isHelp(input)) {
            return InputCommandType.HELP;
        } else if (EditCommand.isEdit(input)) {
            return InputCommandType.EDIT;
        } else {
            return InputCommandType.INVALID;
        }
    }
}
