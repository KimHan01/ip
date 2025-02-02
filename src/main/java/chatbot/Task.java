package chatbot;

import static chatbot.Rocket.isInteger;

public abstract class Task {
    private String taskName;
    private boolean isDone = false;

    public Task(String taskName, boolean mark) {
        this.taskName = taskName;
        this.isDone = mark;
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

    public String getName() {
        return this.taskName;
    }

    public boolean getMark() {
        return this.isDone;
    }

    public abstract String toTxt();

    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + taskName;
        } else {
            return "[ ] " + taskName;
        }
    }
}
