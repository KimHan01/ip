package chatbot;

public class Event extends Task{
    private String from;
    private String to;
    public Event(int taskNum, String taskName, String from, String to) {
        super(taskNum, taskName);
        this.from = from;
        this.to = to;
    }

    public static boolean isEvent(String input) {
        return input.length() > 6
                && input.substring(0, 5).equalsIgnoreCase("event")
                && input.substring(5, 6).isBlank();
    }

    @Override
    public String toString() {
        // Splits the task num from the toString method in Task
        String[] split = super.toString().split("\\.", 2);
        return split[0] + ".[E]" + split[1] + "(from: "
                + from + " to: " + to + ")";
    }
}
