package chatbot;

public class Deadline extends Task{
    private String by;

    // Input format is deadline <TaskName> /by <Date>
    public Deadline(String taskName, String by) {
        super(taskName);
        this.by = by;
    }

    public static boolean isDeadline(String input) {
        return input.length() > 9
                && input.substring(0, 8).equalsIgnoreCase("deadline")
                && input.substring(8, 9).isBlank();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
