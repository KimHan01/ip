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
