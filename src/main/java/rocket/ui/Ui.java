package rocket.ui;

import rocket.Rocket;

import java.util.Scanner;

/**
 * User Interface class which handles the interaction between user and {@link Rocket}.
 * Contains methods to read input from user and display messages to user.
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

    /**
     * Reads rocket's introduction message
     */
    public void introduction() {
        read(Rocket.rocketIntro());
    }

    /**
     * Reads file not found message
     */
    public void fileNotFound() {
        read("File not found\n");
    }
}
