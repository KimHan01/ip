package chatbot.chatbot.storage;

import chatbot.*;
import chatbot.task.Deadline;
import chatbot.task.Event;
import chatbot.task.Task;
import chatbot.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;
    private final File file;
    private final File dir;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.file = new File(filePath);
        this.dir = file.getParentFile();
        createFilePath(filePath); // Creates file if it does not exist
    }

    public void createFilePath(String filePath) {
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

    // Returns ArrayList from storage file,
    // used as argument for constructor of TaskList class
    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner sc = new Scanner(file);
        while (sc.hasNext()) {
            String[] parts = sc.nextLine().split("\\|", 2);
            String header = parts[0];
            String body = parts[1];
            switch (header) {
                case "T":
                    Todo todo = Todo.fromTxt(body);
                    tasks.add(todo);
                    break;
                case "D":
                    Deadline deadline = Deadline.fromTxt(body);
                    tasks.add(deadline);
                    break;
                case "E":
                    Event event = Event.fromTxt(body);
                    tasks.add(event);
                    break;
            }
        }
        sc.close();
        return tasks;
    }

    // Writes a list of tasks into a txt file
    private void writeToStorage(TaskList list) {
        try {
            FileWriter writer = new FileWriter(filePath, true);
            for (Task task : list.getTasks()) {
                writer.write(task.toTxt());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File writeToTemp(TaskList list) {
        String tempPath = dir.getPath() + "/temp.txt";
        File temp = new File(tempPath);
        try {
            if (!temp.createNewFile()) {
                System.out.println("Failed to create temp file");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        writeToStorage(list);
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
    public void addTask(Task task) {
        try {
            FileWriter writer = new FileWriter(filePath, true);
            writer.write(task.toTxt());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Updates storage file with task list.
    public void updateStorage(TaskList list) {
        File temp = writeToTemp(list);
        Storage.replaceFile(temp, file);
    }
}
