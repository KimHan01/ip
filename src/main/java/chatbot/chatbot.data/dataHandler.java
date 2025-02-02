package chatbot.chatbot.data;

import chatbot.Deadline;
import chatbot.Event;
import chatbot.Task;
import chatbot.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class dataHandler {
    final static String directory = "src/main/java/chatbot/chatbot.data";
    final static String filePath = directory + "/list.txt";
    static File dir = new File(directory);
    static File file = new File(filePath);

    public static void createListFile() {
        if (!dir.isDirectory()) {
            if (dir.mkdir()) {
                System.out.println("Created directory");
            } else {
                System.out.println("Failed to create directory");
            }
        }

        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("Created list file");
                } else {
                    System.out.println("Failed to create list file");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    // Read tasks from list.txt
    public static void readList(ArrayList<Task> list) {
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                // Updates the list with the list of tasks
                String[] parts = sc.nextLine().split("\\|", 2);
                String header = parts[0];
                String body = parts[1];
                switch (header) {
                    case "T":
                        Todo todo = Todo.fromTxt(body);
                        list.add(todo);
                        break;
                    case "D":
                        Deadline deadline = Deadline.fromTxt(body);
                        list.add(deadline);
                        break;
                    case "E":
                        Event event = Event.fromTxt(body);
                        list.add(event);
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Add task (Todo) -> Write to txt file
    public static void addTodo(Todo todo) {
        try {
            FileWriter writer = new FileWriter(filePath, true);
            writer.write(todo.toTxt());
            writer.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    // Add task (Deadline) -> Write
    public static void addDeadline(Deadline deadline) {
        try {
            FileWriter writer = new FileWriter(filePath, true);
            writer.write(deadline.toTxt());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Add task (Event) -> Write
    public static void addEvent(Event event) {
        try {
            FileWriter writer = new FileWriter(filePath, true);
            writer.write(event.toTxt());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Delete task -> Delete from txt file

    // Edit task -> Edit the txt file

    // Mark task -> Mark the task as done in txt file
}
