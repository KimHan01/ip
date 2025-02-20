package rocket.command;

/**
 * Stores all commands which can be expected from the user input.
 * It contains the exact command keywords that user will need to type in order to execute the command.
 */
public enum InputCommandType {
    TODO,
    DEADLINE,
    EVENT,
    DELETE,
    BYE, //Exit
    FIND,
    HELP,
    LIST,
    MARK,
    UNMARK,
    EDIT,
    INVALID
}
