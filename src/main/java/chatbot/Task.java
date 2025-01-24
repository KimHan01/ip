package chatbot;

public class Task {
    private int taskNum;
    private String taskName;
    private boolean isDone = false;

    public Task(int taskNum, String taskName) {
        this.taskNum = taskNum;
        this.taskName = taskName;
    }

    public void markTask() {
        this.isDone = true;
    }

    public void unmarkTask() {
        this.isDone = false;
    }

    public static boolean isMark(String input) {
        return input.length() > 5
                && input.substring(0, 4).equalsIgnoreCase("mark")
                && input.substring(4, 5).isBlank()
                && isInteger(input.substring(5));
    }

    public static boolean isUnmark(String input) {
        return input.length() > 7
                && input.substring(0, 6).equalsIgnoreCase("unmark")
                && input.substring(6, 7).isBlank()
                && isInteger(input.substring(7));
    }

    private static boolean isInteger(String x) {
        try {
            int intVal = Integer.parseInt(x);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return taskNum + ".[X] " + taskName;
        } else {
            return taskNum + ".[ ] " + taskName;
        }
    }
}
