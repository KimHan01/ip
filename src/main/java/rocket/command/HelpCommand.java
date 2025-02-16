package rocket.command;

import rocket.storage.Storage;
import rocket.task.TaskList;
import rocket.ui.Ui;

public class HelpCommand extends Command {

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
                BYE
                    --> exits the program
                
                Constraints:
                *There must be a space after a command keyword.
                *Command keywords are case-insensitive
                *Date format: YYYY-MM-DD (e.g. 2025-06-07 --> 2025 June 07)
                *Index: an integer value representing the task number in the list
                """;
        System.out.println(helpMessage);
        return helpMessage;
    }
}
