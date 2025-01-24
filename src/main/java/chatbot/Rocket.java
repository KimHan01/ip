package chatbot;

import java.util.Scanner;
import java.util.ArrayList;

import static chatbot.Deadline.isDeadline;
import static chatbot.Event.isEvent;
import static chatbot.Task.isMark;
import static chatbot.Task.isUnmark;
import static chatbot.ToDo.isTodo;

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
                ToDo todo = new ToDo(tasks.size() + 1, input.trim());
                tasks.add(todo);
                Response.todoAdded(todo);
            // When input is add deadline task
            } else if (isDeadline(input)) {
                String[] split = input.substring(9).split("/by", 2);
                Deadline deadline = new Deadline(tasks.size() + 1,
                        split[0].trim(), split[1].trim());
                tasks.add(deadline);
                Response.deadlineAdded(deadline);
            // When input is add event task
            } else if (isEvent(input)) {
                String[] splitBy = input.substring(6).split("/from", 2);
                String[] splitTo = splitBy[1].split("/to", 2);
                Event event = new Event(tasks.size() + 1,
                        splitBy[0].trim(), splitTo[0].trim(), splitTo[1].trim());
                tasks.add(event);
                Response.eventAdded(event);
            // Else, create task with input as name and add into the list
            } else {
                Task task = new Task(tasks.size() + 1, input.trim());
                tasks.add(task);
                Response.addTaskResponse(input.trim());
            }
        }
    }
}
