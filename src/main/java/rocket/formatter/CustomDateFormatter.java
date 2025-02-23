package rocket.formatter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a custom date formatter that formats dates in a specific way.
 */
public class CustomDateFormatter {
    private static final DateTimeFormatter FORMAT_INPUT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter FORMAT_OUTPUT = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Returns a LocalDate object from a string in input format("yyyy-MM-dd").
     * @throws DateTimeParseException when the string is not of correct format.
     */
    public static LocalDate dateFromInputFormat(String input) throws DateTimeParseException {
        return LocalDate.parse(input, FORMAT_INPUT);
    }

    /**
     * Returns a string in output format("MMM dd yyyy") from a LocalDate object.
     */
    public static String formatOutput(LocalDate inputDateTime) {
        return inputDateTime.format(FORMAT_OUTPUT);
    }

    /**
     * Returns a LocalDate object from a string in output format("MMM dd yyyy").
     * @throws DateTimeParseException when the string is not of correct format.
     */
    public static LocalDate dateFromOutputFormat(String text) throws DateTimeParseException {
        return LocalDate.parse(text, FORMAT_OUTPUT);
    }
}
