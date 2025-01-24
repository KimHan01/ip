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

    @Override
    public String toString() {
        if (this.isDone) {
            return taskNum + ".[X] " + taskName;
        } else {
            return taskNum + ".[ ] " + taskName;
        }
    }
}
