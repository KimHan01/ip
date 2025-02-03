package rocket.task;

import java.time.LocalDate;

public abstract class Task {
    private final String taskName;
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

    public abstract TaskType getType();

    public LocalDate getBy() {
        throw new UnsupportedOperationException("This method is not supported for this class");
    }

    public LocalDate getFrom() {
        throw new UnsupportedOperationException("This method is not supported for this class");
    }

    public LocalDate getTo() {
        throw new UnsupportedOperationException("This method is not supported for this class");
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + taskName;
        } else {
            return "[ ] " + taskName;
        }
    }
}
