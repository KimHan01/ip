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
        System.out.println(introduction); // Prints Introduction

        // List to store input text entered by user
        ArrayList<String> tasks = new ArrayList<>();
        final int tasksLimit = 100;

        // Takes input from console and echoes the input.
        // Unless the input is "bye"(caps insensitive), program will keep waiting for input.
        Scanner s = new Scanner(System.in);
        while (true) {
            String input = s.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                System.out.print(bye);
                break;
            } else if (input.equalsIgnoreCase("list")) {
                if (tasks.isEmpty()) {
                    System.out.println(emptyList);
                } else {
                    Response.printListItems(tasks);
                }
            } else if (input.isBlank()) {
                System.out.println(addEmptyTask);
            } else if (tasks.size() == tasksLimit) {
                System.out.println(fullList);
            } else {
                int taskNum = tasks.size() + 1;
                tasks.add(taskNum + ". " + input);
                System.out.println(Response.addTaskResponse(input));
            }
        }
    }
}
