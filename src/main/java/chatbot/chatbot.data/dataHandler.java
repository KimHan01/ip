package chatbot.chatbot.data;

import chatbot.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
    public static void readList() {
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                System.out.println(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Add task (Todo) -> Write to txt file
    public static void addTodo(Task todo) {
        try {
            FileWriter writer = new FileWriter(filePath, true);
            writer.write("T|0|" + todo.getName() + "\n");
            writer.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    // Delete task -> Delete from txt file

    // Edit task -> Edit the txt file

    // Mark task -> Mark the task as done in txt file
}
