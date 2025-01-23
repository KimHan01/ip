package chatbot;

import java.util.ArrayList;

public class Response {
    // Line between each response
    static String line = "-----------------------------------------------------------------------------------" +
            "------------------------\n";

    // Rocket's introduction. Used chatGPT to help generate a response that Chatbot.Rocket Raccoon
    // from 'Guardians of the galaxy' would say
    static String introduction = line + "Oh, great. Another day, another gig as a chatbot. " +
            "Alright listen up, kid.\nName's Rocket. Rocket Raccoon. Yeah, the genius, weapons expert, " +
            "and the best pilot you'll ever meet.\nNow, I'm stuck here tracking your tasks instead of " +
            "blasting through the galaxy with Groot.\nSo, what do ya want? " +
            "Make it quick—I’ve got… virtual raccoon stuff to do.\n" + line;

    // Rocket's farewell. Used chatGPT as well.
    static String bye = line + "Alright, fine. You're leaving already? Whatever. " +
            "Just don’t blow anything up without me. Rocket out!\nmutters Stupid chatbot job…\n"
            + line;

    // Response when user asks to look at an empty list
    static String emptyList = line + "Oh, look at that—your list of tasks is empty as a vacuum in space.\n" +
            "Guess that means you're either super efficient or just really good at procrastinating." +
            " Probably the latter.\nWant me to add \"Make a to-do list\" to your list of tasks?\n" + line;

    // Response when user adds an empty task
    static String addEmptyTask = line + "Seriously? An empty task? What, are you trying to add \"nothing\" " +
            "to your to-do list?\n" +
            "Hate to break it to you, but that’s not how this works.\n" +
            "Try adding something—even if it’s \"stare at the wall dramatically.\"\n" +
            "At least give me something to work with here!\n" + line;

    // Response when user tries to add to a full list
    static String fullList = line + "Whoa, whoa, whoa—pump the brakes! Your to-do list is already " +
            "stuffed fuller than my weapons stash.\n" +
            "You wanna add more? Maybe finish a task or two first, huh? Or we’ll need to invent a bigger list.\n" +
            "Your call, but don’t blame me when the list explodes.\n" + line;

    // Confirmation response to successfully adding a task into the list
    public static String addTaskResponse(String task) {
        return line + "added: " + task + "\n" + line;
    }

    // Response to "list" which prints out all items inside the list to the console
    public static void printListItems(ArrayList<String> list) {
        System.out.print(line);
        for (String task : list) {
            System.out.println(task);
        }
        System.out.println(line);
    }
}
