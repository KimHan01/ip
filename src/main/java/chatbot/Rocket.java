package chatbot;

import chatbot.chatbot.data.dataHandler;

import java.util.Scanner;
import java.util.ArrayList;

import static chatbot.Deadline.isDeadline;
import static chatbot.Event.isEvent;
import static chatbot.Task.isMark;
import static chatbot.Task.isUnmark;
import static chatbot.Todo.isTodo;

public class Rocket {
    public static void main(String[] args) {
        dataHandler.createListFile(); // Creates list file if it does not exist

        Response.introduction(); // Prints Introduction

        // List to store input text entered by user
        ArrayList<Task> tasks = new ArrayList<>();
        final int tasksLimit = 100;

        // Takes input from console
        // Unless the input is "bye"(caps insensitive), program will keep waiting for input.
        Scanner s = new Scanner(System.in);
        while (true) {
            String input = s.nextLine();
            // When input equals "bye" (Case insensitive)
            if (input.equalsIgnoreCase("bye")) {
                Response.bye();
                break;
            // When input equals "list" (Case insensitive)
            } else if (input.equalsIgnoreCase("list")) {
                // updates task list
                tasks = new ArrayList<>();
                dataHandler.readList(tasks);
                if (tasks.isEmpty()) {
                    Response.emptyList();
                } else {
                    Response.printListItems(tasks);
                }
            // When input is blank
            } else if (input.isBlank()) {
                Response.addEmptyTask();
            // When input try to add task into full list
            } else if (tasks.size() == tasksLimit) {
                Response.fullList();
            // When input is mark task
            } else if (isMark(input)) {
                int taskNumToMark = Integer.parseInt(input.substring(5));
                if (taskNumToMark > tasks.size() || taskNumToMark < 1) {
                    Response.unexistingTaskToMark();
                } else {
                    Task taskToMark = tasks.get(taskNumToMark - 1);
                    taskToMark.markTask();
                    Response.printMarkedTask(taskToMark);
                }
            // When input is unmark task
            } else if (isUnmark(input)) {
                int taskNumToUnmark = Integer.parseInt(input.substring(7));
                if (taskNumToUnmark > tasks.size() || taskNumToUnmark < 1) {
                    Response.unexistingTaskToUnmark();
                } else {
                    Task taskToUnmark = tasks.get(taskNumToUnmark - 1);
                    taskToUnmark.unmarkTask();
                    Response.printUnmarkedTask(taskToUnmark);
                }
            // When input is add todo task
            } else if (isTodo(input)) {
                String taskName = input.substring(5);
                if (taskName.isBlank()) {
                    Response.addEmptyTask();
                } else {
                    Todo todo = new Todo(taskName.trim(), false);
                    tasks.add(todo);
                    dataHandler.addTodo(todo);
                    Response.todoAdded(todo, tasks.size());
                }
            // When input is add deadline task
            } else if (isDeadline(input)) {
                String[] split = input.substring(9).split("/by", 2);
                if (split[0].isBlank()) {
                    Response.addEmptyTask();
                } else {
                    Deadline deadline = new Deadline(split[0].trim(), false, split[1].trim());
                    tasks.add(deadline);
                    dataHandler.addDeadline(deadline);
                    Response.deadlineAdded(deadline, tasks.size());
                }
            // When input is add event task
            } else if (isEvent(input)) {
                String[] splitBy = input.substring(6).split("/from", 2);
                if (splitBy[0].isBlank()) {
                    Response.addEmptyTask();
                } else {
                    String[] splitTo = splitBy[1].split("/to", 2);
                    Event event = new Event(splitBy[0].trim(), false, splitTo[0].trim(), splitTo[1].trim());
                    tasks.add(event);
                    dataHandler.addEvent(event);
                    Response.eventAdded(event, tasks.size());
                }
            // When input is to delete a task
            } else if (isDelete(input)) {
                String index = input.substring(7);
                if (isInteger(index)) {
                    int intIndex = Integer.parseInt(index) - 1;
                    if (intIndex >= 0 && intIndex < tasks.size()) {
                        Task removedTask = tasks.remove(intIndex);
                        Response.taskRemoved(removedTask, tasks.size());
                    } else {
                        Response.removeUnexistingTask();
                    }
                } else {
                    Response.invalidInput();
                }
            // Else, input is invalid
            } else {
                if (input.trim().equalsIgnoreCase("todo")
                        || input.trim().equalsIgnoreCase("deadline")
                            || input.trim().equalsIgnoreCase("event")) {
                    Response.addEmptyTask();
                } else {
                    Response.invalidInput();
                }
            }
        }
    }

    private static boolean isDelete(String input) {
        return (input.length() > 7 && input.substring(0, 6).equalsIgnoreCase("delete"));
    }

    public static boolean isInteger(String x) {
        try {
            int intVal = Integer.parseInt(x);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
