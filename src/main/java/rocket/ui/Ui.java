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

    private static final String LINE = "-----------------------------------------------------------------------------------" +
            "------------------------\n";

    private void read(String message) {
        System.out.println(message);
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

    public void bye() {
        read(LINE + "Alright, fine. You're leaving already? Whatever. " +
                "Just don't blow anything up without me. Rocket out!\nmutters Stupid chatbot job...\n"
                + LINE);
    }

    /**
     * Response to querying an empty list.
     */
    public void readEmptyList() {
        read(LINE + "Oh, look at that, your list of tasks is empty as a vacuum in space.\n" +
                "Guess that means you're either super efficient or just really good at procrastinating." +
                " Probably the latter.\nWant me to add \"Make a to-do list\" to your list of tasks?\n" + LINE);
    }

    /**
     * Response to adding a task with no name.
     */
    public void addEmptyTask() {
        read(LINE + "Seriously? An empty task? What, are you trying to add \"nothing\" " +
                "to your to-do list?\n" +
                "Hate to break it to you, but that's not how this works.\n" +
                "Try adding something, even if it's \"stare at the wall dramatically.\"\n" +
                "At least give me something to work with here!\n" + LINE);
    }

    /**
     * Response to trying to add a task to a full list.
     */
    public static void fullList() {
        System.out.println(LINE + "Whoa, whoa, whoa—pump the brakes! Your to-do list is already " +
                "stuffed fuller than my weapons stash.\n" +
                "You wanna add more? Maybe finish a task or two first, huh? Or we’ll need to invent a bigger list.\n" +
                "Your call, but don’t blame me when the list explodes.\n" + LINE);
    }

    /**
     * Reads all tasks' description in the given list.
     */
    public void readListItems(ArrayList<Task> list) {
        read(LINE);
        for (int i = 0; i < list.size(); i++) {
            int taskNum = i + 1;
            read(taskNum + "." + list.get(i).toString());
        }
        read(LINE);
    }

    /**
     * Response to successfully marking a given task, shows task description.
     */
    public void readMarkTask(Task task) {
        read(LINE + "Successfully marked:\n" + task.toString() + "\n" + LINE);
    }

    /**
     * Response to successfully unmarking a given task, shows task description.
     */
    public void readUnmarkTask(Task task) {
        read(LINE + "Successfully unmarked:\n" + task.toString() + "\n" + LINE);
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

    /**
     * Response to successfully adding a task,
     * shows task description and how many tasks are currently in the list.
     */
    public void readAddTask(Task task, int listSize) {
        read(LINE + "Successfully added ToDo:\n"
                + task.toString() + "\n"
                + "Now you have " + listSize + " tasks in the list\n" + LINE);
    }

    /**
     * Response to invalid input from user.
     */
    public void readInvalidFormat() {
        read(LINE + "Maybe you wanna try giving me something that makes sense,\n" +
                "unless you want me to invent something out of thin air,\n" +
                "which, trust me, you don't\n" + LINE);
    }

    /**
     * Response to successfully deleting a task from the list,
     * shows task description and how many tasks are currently in the list.
     */
    public void readRemoveTask(Task task, int listSize) {
        read(LINE + "I've removed this task:\n" + task.toString()
                + "\n" + "Now you have " + listSize + " tasks in the list.\n" + LINE);
    }

    /**
     * Response to trying to remove a task that does not exist.
     */
    public void readInvalidRemoveTask() {
        read(LINE + "Yeah, that's not happening. The list has limits, just like my patience.\n" +
                "Check your list, make sure the task exists, and try again\n" + LINE);
    }

    /**
     * Response to invalid date format given by user.
     */
    public void readInvalidDateFormat() {
        read("Invalid date format given. Please use yyyy-mm-dd\n");
    }

    /**
     * Response to finding tasks by keyword,
     * shows the task description of all tasks found.
     */
    public void readFind(TaskList tasks) {
        read(LINE + "Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.getSize(); i++) {
            int taskNum = i + 1;
            read(taskNum + "." + tasks.get(i).toString());
        }
        read(LINE);
    }
}
