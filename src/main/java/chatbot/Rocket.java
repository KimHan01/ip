package chatbot;

import java.util.Scanner;
import java.util.ArrayList;

import static chatbot.Response.introduction;
import static chatbot.Response.bye;
import static chatbot.Response.addEmptyTask;
import static chatbot.Response.emptyList;
import static chatbot.Response.fullList;

public class Rocket {
    public static void main(String[] args) {
        Response.introduction(); // Prints Introduction

        // List to store input text entered by user
        ArrayList<Task> tasks = new ArrayList<>();
        final int tasksLimit = 100;

        // Takes input from console and echoes the input.
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
                if (tasks.isEmpty()) {
                    Response.emptyList();
                } else {
                    Response.printListItems(tasks);
                }
            // When input is blank
            } else if (input.isBlank()) {
                Response.addEmptyTask();
            // When trying to add task into full list
            } else if (tasks.size() == tasksLimit) {
                Response.fullList();
            // Marks task if valid
            } else if (isMark(input)) {
                int taskNumToMark = Integer.parseInt(input.substring(5));
                if (taskNumToMark > tasks.size() || taskNumToMark < 1) {
                    Response.unexistingTaskToMark();
                } else {
                    Task taskToMark = tasks.get(taskNumToMark - 1);
                    taskToMark.markTask();
                    Response.printMarkedTask(taskToMark);
                }
            // Unmarks task if valid
            } else if (isUnmark(input)) {
                int taskNumToUnmark = Integer.parseInt(input.substring(7));
                if (taskNumToUnmark > tasks.size() || taskNumToUnmark < 1) {
                    Response.unexistingTaskToUnmark();
                } else {
                    Task taskToUnmark = tasks.get(taskNumToUnmark - 1);
                    taskToUnmark.unmarkTask();
                    Response.printUnmarkedTask(taskToUnmark);
                }
            // Else, create task with input as name and add into the list
            } else {
                int taskNum = tasks.size() + 1;
                Task task = new Task(taskNum, input);
                tasks.add(task);
                Response.addTaskResponse(input);
            }
        }
    }

    private static boolean isMark(String input) {
        return input.length() > 5
                && input.substring(0, 4).equalsIgnoreCase("mark")
                && input.substring(4, 5).isBlank()
                && isInteger(input.substring(5));
    }

    private static boolean isUnmark(String input) {
        return input.length() > 7
                && input.substring(0, 6).equalsIgnoreCase("unmark")
                && input.substring(6, 7).isBlank()
                && isInteger(input.substring(7));
    }

    private static boolean isInteger(String x) {
        try {
            int intVal = Integer.parseInt(x);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
