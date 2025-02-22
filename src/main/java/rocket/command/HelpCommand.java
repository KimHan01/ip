package rocket.command;

import rocket.storage.Storage;
import rocket.task.TaskList;
import rocket.ui.Ui;

/**
 * Represents a command to show the user guide
 */
public class HelpCommand extends Command {
    /**
     * Creates a new {@code HelpCommand}.
     */
    public HelpCommand() {
        super(false);
    }

    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        String helpMessage = """
                Commands:
                LIST
                    --> shows your task list
                TODO [name]
                    --> adds a todo task
                DEADLINE [name] /by[date]
                    --> adds a deadline task
                EVENT [name] /from[date] /to[date]
                    --> adds an event task
                DELETE [index]
                    --> deletes a task
                MARK [index]
                    --> marks a task as done
                UNMARK [index]
                    --> unmarks a task
                FIND [name]
                    --> finds a task by name
                EDIT [index] [field specifier][new value for the field]
                    --> Edits a field of a task
                BYE
                    --> exits the program

                Constraints:
                * There must be a space after any command keyword.
                * Date format: YYYY-MM-DD (e.g. 2025-06-07)
                * Index: an integer value representing the task number in the list
                * Field specifier: "/n" for name, "/by" for deadline date, "/from" or "/to" for event dates
                * You can only edit one field at a time
                * Command keywords are case-insensitive
                """;
        ui.read(helpMessage);
        return helpMessage;
    }

    /**
     * Checks if the given input is a {@code HelpCommand}.
     */
    public static boolean isHelp(String input) {
        return input.trim().equalsIgnoreCase(InputCommandType.HELP.name());
    }
}
