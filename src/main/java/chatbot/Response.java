package chatbot;

import java.util.ArrayList;

public class Response {
    // Line between each response
    static String line = "-----------------------------------------------------------------------------------" +
            "------------------------\n";

    // Rocket's introduction. Used chatGPT to help generate a response that Chatbot.Rocket Raccoon
    // from 'Guardians of the galaxy' would say
    public static void introduction() {
        System.out.println(line + "Oh, great. Another day, another gig as a chatbot. " +
                "Alright listen up, kid.\nName's Rocket. Rocket Raccoon. Yeah, the genius, weapons expert, " +
                "and the best pilot you'll ever meet.\nNow, I'm stuck here tracking your tasks instead of " +
                "blasting through the galaxy with Groot.\nSo, what do ya want? " +
                "Make it quick, I've got virtual raccoon stuff to do.\n" + line);
    }

    // Rocket's farewell. Used chatGPT as well.
    public static void bye() {
        System.out.print(line + "Alright, fine. You're leaving already? Whatever. " +
                "Just don't blow anything up without me. Rocket out!\nmutters Stupid chatbot job...\n"
                + line);
    }

    // Response when user asks to look at an empty list
    public static void emptyList() {
        System.out.println(line + "Oh, look at that, your list of tasks is empty as a vacuum in space.\n" +
                "Guess that means you're either super efficient or just really good at procrastinating." +
                " Probably the latter.\nWant me to add \"Make a to-do list\" to your list of tasks?\n" + line);
    }

    // Response when user adds an empty task
    public static void addEmptyTask() {
        System.out.println(line + "Seriously? An empty task? What, are you trying to add \"nothing\" " +
                "to your to-do list?\n" +
                "Hate to break it to you, but that’s not how this works.\n" +
                "Try adding something—even if it’s \"stare at the wall dramatically.\"\n" +
                "At least give me something to work with here!\n" + line);
    }

    // Response when user tries to add to a full list
    public static void fullList() {
        System.out.println(line + "Whoa, whoa, whoa—pump the brakes! Your to-do list is already " +
                "stuffed fuller than my weapons stash.\n" +
                "You wanna add more? Maybe finish a task or two first, huh? Or we’ll need to invent a bigger list.\n" +
                "Your call, but don’t blame me when the list explodes.\n" + line);
    }

    // Confirmation response to successfully adding a task into the list
    public static void addTaskResponse(String taskName) {
        System.out.println(line + "added: " + taskName + "\n" + line);
    }

    // Response to "list" which prints out all items inside the list to the console
    public static void printListItems(ArrayList<Task> list) {
        System.out.print(line);
        for (int i = 0; i < list.size(); i++) {
            int taskNum = i + 1;
            System.out.println(taskNum + "." + list.get(i).toString());
        }
        System.out.println(line);
    }

    // Response to successfully marking a task
    public static void printMarkedTask(Task task) {
        System.out.println(line + "Successfully marked:\n" + task.toString() + "\n" + line);
    }

    // Response to successfully unmarking a task
    public static void printUnmarkedTask(Task task) {
        System.out.println(line + "Successfully unmarked:\n" + task.toString() + "\n" + line);
    }
    // Response to trying to mark a task that does not exist
    public static void unexistingTaskToMark() {
        System.out.println(line + "Oh, you're trying to mark off a task that doesn't even exist? \n" +
                "If it's not on the list, it can't be marked as done. \n" +
                "Check again, maybe the task is hiding in a parallel dimension or something.\n" + line);
    }

    // Response to trying to unmark a task that does not exist
    public static void unexistingTaskToUnmark() {
        System.out.println(line + "You're trying to unmark a task that doesn't even exist? \n" +
                "That's some next-level overthinking right there. \n" +
                "Look, if it's not on the list, there's nothing to unmark. \n" +
                "Double-check your list, alright?\n" + line);
    }

    // Response to successfully adding a todo
    public static void todoAdded(ToDo todo, int listSize) {
        System.out.println(line + "Successfully added ToDo:\n"
                + todo.toString() + "\n"
                + "Now you have " + listSize + " tasks in the list\n" + line);
    }

    // Response to successfully adding a deadline
    public static void deadlineAdded(Deadline deadline, int listSize) {
        System.out.println(line + "Successfully added Deadline:\n"
                + deadline.toString() + "\n"
                + "Now you have " + listSize + " tasks in the list\n" + line);
    }

    // Response to successfully adding an event
    public static void eventAdded(Event event, int listSize) {
        System.out.println(line + "Successfully added Event:\n"
                + event.toString() + "\n"
                + "Now you have " + listSize + " tasks in the list\n" + line);
    }
}
