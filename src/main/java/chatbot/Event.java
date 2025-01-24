package chatbot;

public class Event extends Task{
    private String from;
    private String to;
    public Event(String taskName, String from, String to) {
        super(taskName);
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
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
