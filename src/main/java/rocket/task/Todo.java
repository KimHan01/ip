package rocket.task;

import rocket.command.AddCommand;
import rocket.command.Command;
import rocket.command.EmptyTaskNameCommand;
import rocket.exception.EmptyTaskNameException;

/**
 * Represents a task with a name and a mark.
 */
public class Todo extends Task {
    public Todo(String taskName, boolean mark) {
        super(taskName, mark);
    }

    /**
     * Returns formatted String representation ("T|MARK|NAME") of {@code Todo} object.
     */
    @Override
    public String toTxt() {
        String mark = super.getMark() ? "1" : "0";
        return "T|" + mark + "|" + super.getName() + "\n";
    }

    @Override
    public TaskType getType() {
        return TaskType.TODO;
    }

    /**
     * Returns the task description of {@code Todo} object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a {@code Todo} object from a formatted String without its header("T|").
     * @throws ArrayIndexOutOfBoundsException if the format is wrong
     */
    public static Todo fromTxt(String body) throws ArrayIndexOutOfBoundsException {
        // Format of given body should be: 0/1|NAME
        String[] parts = body.split("\\|", 2);
        boolean mark = parts[0].equals("1");
        String name = parts[1];
        return new Todo(name, mark);
    }

    /**
     * Checks if the input is a {@code Todo} task.
     */
    public static boolean isTodo(String input) {
        return input.length() > 5
                && input.substring(0, 4).equalsIgnoreCase(TaskType.TODO.name())
                && input.substring(4, 5).isBlank();
    }

    /**
     * Returns the {@code AddCommand} for a {@code Todo} task from the given input if valid,
     * otherwise the appropriate error {@code Command} is returned.
     */
    public static Command getAddTodoCommand(String input) {
        try {
            Todo todo = Todo.createTodoFromInput(input);
            return new AddCommand(todo, false);
        } catch (EmptyTaskNameException e) {
            return new EmptyTaskNameCommand();
        }
    }

    private static Todo createTodoFromInput(String input) throws EmptyTaskNameException {
        String name = input.substring(5);
        if (name.isBlank()) {
            throw new EmptyTaskNameException("Blank Todo name given");
        }
        return new Todo(name.trim(), false);
    }
}
