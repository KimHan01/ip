import java.util.Scanner;

public class Rocket {
    public static void main(String[] args) {
        // Rocket's introduction. Used chatGPT to help generate a response that Rocket Raccoon
        // from 'Guardians of the galaxy' would say
        String introduction = "Oh, great. Another day, another gig as a chatbot. Alright listen up, kid.\n" +
                "Name's Rocket. Rocket Raccoon. Yeah, the genius, weapons expert, and the best pilot you'll ever meet.\n" +
                "Now, I'm stuck here tracking your tasks instead of blasting through the galaxy with Groot.\n" +
                "So, what do ya want? Make it quick —I’ve got… virtual raccoon stuff to do.\n";

        // Rocket's farewell. Used chatGPT as well.
        String bye = "\nAlright, fine. You're leaving already? Whatever. Just don’t blow anything up without me. Rocket out!\n" +
                "mutters Stupid chatbot job…\n";

        String line = "--------------------------------------------------------------------------------------------------------\n";
        // Prints Introduction
        System.out.println(line + introduction + line);

        // Takes input from console and echoes the input.
        // Unless the input is "bye"(caps insensitive), program will keep waiting for input.
        Scanner s = new Scanner(System.in);
        while (true) {
            String input = s.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                System.out.println(line + bye + "\n" + line);
                break;
            }
            System.out.println(line + input + "\n" + line);
        }
    }
}
