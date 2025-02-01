package chatbot.chatbot.data;

import java.io.File;
import java.io.IOException;

public class dataHandler {
    final static String directory = "src/main/java/chatbot/chatbot.data";
    final static String filePath = directory + "/storage.txt";
    public static void createStorageFile() {
        File dir = new File(directory);
        File file = new File(filePath);

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
                    System.out.println("Created storage file");
                } else {
                    System.out.println("Failed to create storage file");
                }
            } catch (IOException e) {
                System.out.println("Failed to create file due to IOException");
            }
        }
    }
    
}
