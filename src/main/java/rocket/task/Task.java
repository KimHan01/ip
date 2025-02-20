package rocket.task;

import rocket.exception.RocketException;

import java.time.LocalDate;

/**
 * Represents a task in the task list.
 */
public abstract class Task {
    private String taskName;
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

    public void rename(String newName) {
        this.taskName = newName;
    }

    /**
     * Returns a new name to edit if num of names to edit is exactly 1.
     * <p>The new name returned can be an empty string.</p>
     * @throws RocketException When more than one name to edit is found
     */
    public static String getNewNameFromChanges(String[] changes) throws RocketException {
        int numOfNameEdit = 0;
        String newName = null;
        for (int i = 2; i < changes.length; i++) {
            // Checks for prefix ("/n")
            if (changes[i].startsWith("/n")) {
                numOfNameEdit ++;
                newName = changes[i].substring(2).trim();
            }
        }
        if (numOfNameEdit > 1) {
            throw new RocketException("Invalid number of names to edit");
        }
        if (newName != null && newName.isBlank()) {
            throw new RocketException("Blank name given for edit");
        }
        return newName;
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
