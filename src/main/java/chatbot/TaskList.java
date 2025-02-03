package chatbot;

import chatbot.exception.RocketRuntimeException;
import chatbot.task.Deadline;
import chatbot.task.Event;
import chatbot.task.Task;
import chatbot.task.Todo;

import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

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
        }
    }

    public int getSize() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    public Task remove(int index) throws IndexOutOfBoundsException {
        return tasks.remove(index);
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task mark(int index) throws IndexOutOfBoundsException {
        tasks.get(index).markTask();
        return tasks.get(index);
    }

    public Task unmark(int index) throws IndexOutOfBoundsException {
        tasks.get(index).unmarkTask();
        return tasks.get(index);
    }
}
