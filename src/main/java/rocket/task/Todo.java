package rocket.task;

/**
 * Represents a task with a name and a mark.
 */
public class Todo extends Task {
    public Todo(String taskName, boolean mark) {
        super(taskName, mark);
    }

    /**
     * Returns formatted String representation for storage file of {@code Todo} object.
     * Format to be returned is "T|MARK|NAME".
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
     * Returns a {@code Todo} object from a formatted String without its header("T|").
     * @throws ArrayIndexOutOfBoundsException
     */
    public static Todo fromTxt(String body) throws ArrayIndexOutOfBoundsException {
        String[] parts = body.split("\\|", 2); // Split once
        boolean mark = parts[0].equals("1");
        String name = parts[1];
        return new Todo(name, mark);
    }

    /**
     * Returns the task description of {@code Todo} object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
