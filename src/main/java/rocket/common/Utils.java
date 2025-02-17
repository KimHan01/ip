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
        try {
            int intVal = Integer.parseInt(x);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
