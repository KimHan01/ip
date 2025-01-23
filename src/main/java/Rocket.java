import java.util.Scanner;
import java.util.ArrayList;

public class Rocket {
    public static void main(String[] args) {
        // Rocket's introduction. Used chatGPT to help generate a response that Rocket Raccoon
        // from 'Guardians of the galaxy' would say
        String introduction = "Oh, great. Another day, another gig as a chatbot. Alright listen up, kid.\n" +
                "Name's Rocket. Rocket Raccoon. Yeah, the genius, weapons expert, and the best pilot you'll ever meet.\n" +
                "Now, I'm stuck here tracking your tasks instead of blasting through the galaxy with Groot.\n" +
                "So, what do ya want? Make it quick —I’ve got… virtual raccoon stuff to do.\n";

        // Rocket's farewell. Used chatGPT as well.
        String bye = "Alright, fine. You're leaving already? Whatever. Just don’t blow anything up without me. Rocket out!\n" +
                "mutters Stupid chatbot job…";

        String emptyList = "Oh, look at that—your list of tasks is empty as a vacuum in space.\n" +
                "Guess that means you're either super efficient or just really good at procrastinating." +
                " Probably the latter.\nWant me to add \"Make a to-do list\" to your list?\n";

        String addEmptyTask = "Seriously? An empty task? What, are you trying to add \"nothing\" to your to-do list?\n" +
                "Hate to break it to you, but that’s not how this works.\n" +
                "Try adding something—even if it’s \"stare at the wall dramatically.\"\n" +
                "At least give me something to work with here!\n";

        String fullList = "Whoa, whoa, whoa—pump the brakes! Your to-do list is already stuffed fuller than my weapons stash.\n" +
                "You wanna add more? Maybe finish a task or two first, huh? Or we’ll need to invent a bigger list.\n" +
                "Your call, but don’t blame me when the list explodes.\n";

        String line = "--------------------------------------------------------------------------------------------------------\n";

        System.out.println(line + introduction + line); // Prints Introduction

        // List to store input text entered by user
        ArrayList<String> tasks = new ArrayList<>();
        int tasksLimit = 100;

        // Takes input from console and echoes the input.
        // Unless the input is "bye"(caps insensitive), program will keep waiting for input.
        Scanner s = new Scanner(System.in);
        while (true) {
            String input = s.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                System.out.print(line + bye + "\n" + line);
                break;
            } else if (input.equalsIgnoreCase("list")) {
                if (tasks.isEmpty()) {
                    System.out.println(line + emptyList + line);
                }
                else {
                    System.out.print(line);
                    for (String task : tasks) {
                        System.out.println(task);
                    }
                    System.out.println(line);
                }
            } else if (input.isBlank()) {
                System.out.println(line + addEmptyTask + line);
            } else if (tasks.size() == tasksLimit) {
                System.out.println(line + fullList + line);
            } else {
                int taskNum = tasks.size() + 1;
                tasks.add(taskNum + ". " + input);
                System.out.println(line + "added: " + input + "\n" + line);
            }
        }
    }
}
