package rocket.ui;
import java.util.Scanner;

import rocket.Rocket;

/**
 * User Interface class which handles the interaction between user and {@link Rocket}.
 * Contains methods to read input from user and display messages to user.
 */
public class Ui {
    private final Scanner sc;

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

    /**
     * Prints to the console with the given message as the output
     */
    public void read(String message) {
        String line = "-----------------------------------------------------------------------------------"
                + "------------------------";
        System.out.println(line);
        System.out.println(message);
        System.out.println(line);
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
