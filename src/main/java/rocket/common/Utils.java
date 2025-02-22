package rocket.common;

/**
 * Contains static utility methods.
 */
public class Utils {
    private Utils() {} // Prevent instantiation
    /**
     * Checks if the given string is an integer
     */
    public static boolean isInteger(String x) {
        // @@author KimHan01-reused
        // Reused from https://stackoverflow.com/questions/5439529/determine-if-a-string-is-an-integer-in-java
        // with minor modifications
        try {
            int intVal = Integer.parseInt(x);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
        // @@author
    }
}
