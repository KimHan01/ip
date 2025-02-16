package rocket.ui;

import rocket.Rocket;
import rocket.task.Task;
import rocket.task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * User Interface class which handles the interaction between user and {@link Rocket}.
 * Contain methods to read responses back to the user.
 */
public class Ui {
    private Scanner sc;

    /**
     * Creates a {@code Ui} with an initialized {@code Scanner} which takes input from user
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Reads one input line from user
     * @return String of input line
     */
    public String readInputCommand() {
        return sc.nextLine();
    }

    private final String LINE = "-----------------------------------------------------------------------------------" +
            "------------------------";

    public void read(String message) {
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(LINE);
    }

    public void fileNotFound() {
        read("File not found\n");
    }

    public void introduction() {
        read(LINE + "Oh, great. Another day, another gig as a chatbot. " +
                "Alright listen up, kid.\nName's Rocket. Rocket Raccoon. Yeah, the genius, weapons expert, " +
                "and the best pilot you'll ever meet.\nNow, I'm stuck here tracking your tasks instead of " +
                "blasting through the galaxy with Groot.\nSo, what do ya want? " +
                "Make it quick, I've got virtual raccoon stuff to do.\n" + LINE);
    }

    /**
     * Response to trying to mark a task that does not exist.
     */
    public void readInvalidMarkRequest() {
        read(LINE + "Oh, you're trying to mark off a task that doesn't even exist? \n" +
                "If it's not on the list, it can't be marked as done. \n" +
                "Check again, maybe the task is hiding in a parallel dimension or something.\n" + LINE);
    }

    /**
     * Response to trying to unmark a task that does not exist.
     */
    public void readInvalidUnmarkRequest() {
        read(LINE + "You're trying to unmark a task that doesn't even exist? \n" +
                "That's some next-level overthinking right there. \n" +
                "Look, if it's not on the list, there's nothing to unmark. \n" +
                "Double-check your list, alright?\n" + LINE);
    }

}
