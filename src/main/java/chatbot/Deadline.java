package chatbot;

public class Deadline extends Task{
    private String by;

    // Input format is deadline <TaskName> /by <Date>
    public Deadline(int taskNum, String taskName, String by) {
        super(taskNum, taskName);
        this.by = by;
    }

    public static boolean isDeadline(String input) {
        return input.length() > 9
                && input.substring(0, 8).equalsIgnoreCase("deadline")
                && input.substring(8, 9).isBlank();
    }

    @Override
    public String toString() {
        // Splits the task num from the toString method in Task
        String[] split = super.toString().split("\\.", 2);
        return split[0] + ".[D]" + split[1] + " (by: " + by + ")";
    }
}
