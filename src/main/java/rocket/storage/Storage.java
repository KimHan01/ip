package rocket.storage;

import rocket.task.Deadline;
import rocket.task.Event;
import rocket.task.Task;
import rocket.task.TaskList;
import rocket.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the storage of tasks.
 * Handles creating, reading and writing from storage file.
 */
public class Storage {
    private final File file;
    private final File dir;

    /**
     * Creates a {@code Storage} object with a given file path.
     * Creates file at file path if it does not exist.
     */
    public Storage(String filePath) {
        this.file = new File(filePath);
        this.dir = file.getParentFile();
        createFilePath(); // Creates file if it does not exist
        createFilePath(); // Creates file if it does not exist
        assert dir.isDirectory(): "Directory is not created";
        assert file.isFile(): "File is not created";
    }

    /**
     * Creates directory and file if they do not exist at given file path.
     */
    public void createFilePath() {
        if (!dir.isDirectory()) {
            if (dir.mkdir()) {
                System.out.println("Created directory (" + dir.getPath() + ")");
            } else {
                System.out.println("Failed to create directory");
            }
        }

        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("Created data file (" + file.getPath() + ")");
                } else {
                    System.out.println("Failed to create storage file");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Loads tasks from storage file into an ArrayList.
     * @return ArrayList of tasks
     * @throws FileNotFoundException if file is not found
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner sc = new Scanner(file);
        while (sc.hasNext()) {
            String[] parts = sc.nextLine().split("\\|", 2);
            String header = parts[0];
            String body = parts[1];
            try {
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
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Error loading a task from storage file due to incorrect format");
            }
        }
        sc.close();
        return tasks;
    }

    /**
     * Rewrites storage file with tasks from TaskList.
     * @param list TaskList to rewrite storage with
     */
    public void updateStorage(TaskList list) {
        File temp = writeToTemp(list);
        replaceFile(temp, file);
    }

    private void writeToStorage(TaskList list, String filePath) {
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
        writeToStorage(list, tempPath);
        return temp;
    }

    private void replaceFile(File source, File dest) {
        try {
            Files.move(source.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
