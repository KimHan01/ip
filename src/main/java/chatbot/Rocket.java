package chatbot;

import chatbot.chatbot.data.dataHandler;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;

public class Rocket {
    public static void main(String[] args) {
        dataHandler.createListFile(); // Creates list file if it does not exist

        Response.introduction(); // Prints Introduction

        // List to store user's list of tasks
        ArrayList<Task> tasks = new ArrayList<>();
        dataHandler.readList(tasks);
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
                // updates task list by clearing the list and then reading list from dataHandler
                tasks.clear();
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
                    dataHandler.updateList(tasks);
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
                    dataHandler.updateList(tasks);
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
                    dataHandler.addTask(todo);
                    Response.todoAdded(todo, tasks.size());
                }
            // When input is add deadline task
            } else if (isDeadline(input)) {
                String[] parts = input.substring(9).split("/by", 0);
                if (parts[0].isBlank()) {
                    Response.addEmptyTask();
                } else if (parts.length != 2) {
                    Response.invalidInput();
                } else {
                    try {
                        LocalDate dateTime = CustomDateFormatter.dateFromInputFormat(parts[1].trim());
                        Deadline deadline = new Deadline(parts[0].trim(), false, dateTime);
                        tasks.add(deadline);
                        dataHandler.addTask(deadline);
                        Response.deadlineAdded(deadline, tasks.size());
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid Date Time input format. Please use yyyy-MM-dd.");
                    }
                }
            // When input is add event task
            } else if (isEvent(input)) {
                String[] splitFrom = input.substring(6).split("/from", 0);
                if (splitFrom[0].isBlank()) {
                    Response.addEmptyTask();
                } else if (splitFrom.length != 2) {
                    Response.invalidInput();
                } else {
                    String[] splitTo = splitFrom[1].split("/to", 0);
                    if (splitTo.length != 2) {
                        Response.invalidInput();
                    } else {
                        try {
                            LocalDate to = CustomDateFormatter.dateFromInputFormat(splitTo[0].trim());
                            LocalDate from = CustomDateFormatter.dateFromInputFormat(splitTo[1].trim());
                            Event event = new Event(splitFrom[0].trim(), false, to, from);
                            tasks.add(event);
                            dataHandler.addTask(event);
                            Response.eventAdded(event, tasks.size());
                        } catch (DateTimeParseException e) {
                            System.out.println("Invalid Date Time input format. Please use yyyy-MM-dd.");
                        }
                    }
                }
            // When input is to delete a task
            } else if (isDelete(input)) {
                String index = input.substring(7);
                if (isInteger(index)) {
                    int intIndex = Integer.parseInt(index) - 1;
                    if (intIndex >= 0 && intIndex < tasks.size()) {
                        Task removedTask = tasks.remove(intIndex);
                        dataHandler.updateList(tasks);
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

    public static boolean isTodo(String input) {
        return input.length() > 5
                && input.substring(0, 4).equalsIgnoreCase("todo")
                && input.substring(4, 5).isBlank();
    }

    public static boolean isDeadline(String input) {
        return input.length() > 9
                && input.substring(0, 8).equalsIgnoreCase("deadline")
                && input.substring(8, 9).isBlank();
    }

    public static boolean isEvent(String input) {
        return input.length() > 6
                && input.substring(0, 5).equalsIgnoreCase("event")
                && input.substring(5, 6).isBlank();
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
