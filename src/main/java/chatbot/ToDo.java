package chatbot;

public class ToDo extends Task {
    public ToDo(int taskNum, String taskName) {
        super(taskNum, taskName);
    }

    public static boolean isTodo(String input) {
        return input.length() > 5
                && input.substring(0, 4).equalsIgnoreCase("todo")
                && input.substring(4, 5).isBlank();
    }

    @Override
    public String toString() {
        // Splits the task num from the toString method in Task
        String[] split = super.toString().split("\\.", 2);
        return split[0] + ".[T]" + split[1];
    }
}
