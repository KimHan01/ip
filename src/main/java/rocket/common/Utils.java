package rocket.common;

/**
 * Contains utility methods.
 */
public class Utils {
    public static boolean isInteger(String x) {
        try {
            int intVal = Integer.parseInt(x);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
