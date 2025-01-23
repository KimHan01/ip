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
        System.out.println(line + introduction + line + bye + line);
    }
}
