package chatbot.chatbot.data;

import chatbot.Deadline;
import chatbot.Event;
import chatbot.Task;
import chatbot.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
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
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Writes a list of tasks into a txt file
    private static void writeToTxt(String filePath, ArrayList<Task> list) {
        try {
            FileWriter writer = new FileWriter(filePath, true);
            for (Task task : list) {
                writer.write(task.toTxt());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static File writeToTemp(ArrayList<Task> list) {
        String tempPath = directory + "/temp.txt";
        File temp = new File(tempPath);
        try {
            if (!temp.createNewFile()) {
                System.out.println("Failed to create temp file");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        dataHandler.writeToTxt(tempPath, list);
        return temp;
    }

    private static void replaceFile(File source, File dest) {
        try {
            Files.move(source.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Add task of any type -> write
    public static void addTask(Task task) {
        try {
            FileWriter writer = new FileWriter(filePath, true);
            writer.write(task.toTxt());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Updates list.txt with task list. Writes task list into temp.txt file and replaces list.txt with temp.txt
    public static void updateList(ArrayList<Task> list) {
        File temp = writeToTemp(list);
        dataHandler.replaceFile(temp, file);
    }
}
