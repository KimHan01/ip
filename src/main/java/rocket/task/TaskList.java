package rocket.task;

import rocket.exception.RocketRuntimeException;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Creates a {@code TaskList} object with an empty list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a {@code TaskList} object with a list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns a {@code Task} object from the {@code TaskList}.
     * @param index Index of the task to be returned.
     */
    public Task get(int index) {
        if (index < 0 || index >= tasks.size()) {
            throw new ArrayIndexOutOfBoundsException("Index: " + index + ", Size: " + tasks.size());
        }
        Task task = tasks.get(index);
        String name = task.getName();
        boolean mark = task.getMark();
        TaskType taskType = task.getType();
        switch (taskType) {
        case TODO:
            return new Todo(name, mark);
        case DEADLINE:
            LocalDate by = task.getBy();
            return new Deadline(name, mark, by);
        case EVENT:
            LocalDate from = task.getFrom();
            LocalDate to = task.getTo();
            return new Event(name, mark, from, to);
        default:
            throw new RocketRuntimeException("Failed to get Task from TaskList");
            // Fallthrough: throws RocketRuntimeException, but this statement should not occur
        }
    }

    /**
     * Returns the size of the task list
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns an arraylist containing all tasks in the task list
     */
    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    /**
     * Checks if the task list is empty
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Removes a task from the task list based on the given index
     * @param index the zero-based index of the task to be removed
     * @return the removed task
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 or index >= size)
     */
    public Task remove(int index) throws IndexOutOfBoundsException {
        return tasks.remove(index);
    }

    /**
     * Adds the given task into the task list
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Marks a task in the task list based on the given index
     * @param index the zero-based index of the task to be removed
     * @return the marked task
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 or index >= size)
     */
    public Task mark(int index) throws IndexOutOfBoundsException {
        tasks.get(index).markTask();
        return tasks.get(index);
    }

    /**
     * Unmarks a task in the task list based on the given index
     * @param index the zero-based index of the task to be removed
     * @return the unmarked task
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 or index >= size)
     */
    public Task unmark(int index) throws IndexOutOfBoundsException {
        tasks.get(index).unmarkTask();
        return tasks.get(index);
    }
}
