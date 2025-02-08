package rocket.task;

import java.time.LocalDate;

/**
 * Represents a task in the task list.
 */
public abstract class Task {
    private final String taskName;
    private boolean isDone = false;

    /**
     * Creates a {@code Task} object with a name and mark.
     */
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

    /**
     * Returns formatted String representation for storage file of {@code Task} object.
     * Format to be returned depends on the type of task.
     * @return formatted String for storage
     */
    public abstract String toTxt();

    /**
     * Returns the task type of {@code Task} object.
     */
    public abstract TaskType getType();

    /**
     * Returns due date of {@code Task}. This method is not supported for {@link Task} class
     * and is to be overridden by relevant child classes.
     */
    public LocalDate getBy() {
        throw new UnsupportedOperationException("This method is not supported for this class");
    }

    /**
     * Returns start date of {@code Task}. This method is not supported for {@link Task} class
     * and is to be overridden by relevant child classes.
     */
    public LocalDate getFrom() {
        throw new UnsupportedOperationException("This method is not supported for this class");
    }

    /**
     * Returns end date of {@code Task}. This method is not supported for {@link Task} class
     * and is to be overridden by relevant child classes.
     */
    public LocalDate getTo() {
        throw new UnsupportedOperationException("This method is not supported for this class");
    }

    /**
     * Returns task description of {@code Task}.
     */
    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + taskName;
        } else {
            return "[ ] " + taskName;
        }
    }
}
