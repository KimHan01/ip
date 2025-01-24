package chatbot;

public class ToDo extends Task {
    public ToDo(String taskName) {
        super(taskName);
    }

    public static boolean isTodo(String input) {
        return input.length() > 5
                && input.substring(0, 4).equalsIgnoreCase("todo")
                && input.substring(4, 5).isBlank();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
