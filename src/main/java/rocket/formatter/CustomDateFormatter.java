package rocket.formatter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a custom date formatter that formats dates in a specific way.
 */
public class CustomDateFormatter {
    private final static DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final static DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Returns a LocalDate object from a string in input format("yyyy-MM-dd").
     * @throws DateTimeParseException when the string is not of correct format.
     */
    public static LocalDate dateFromInputFormat(String input) throws DateTimeParseException{
        return LocalDate.parse(input, inputFormat);
    }

    /**
     * Returns a string in output format("MMM dd yyyy") from a LocalDate object.
     */
    public static String formatOutput(LocalDate inputDateTime) {
        return inputDateTime.format(outputFormat);
    }

    /**
     * Returns a LocalDate object from a string in output format("MMM dd yyyy").
     * @throws DateTimeParseException when the string is not of correct format.
     */
    public static LocalDate dateFromOutputFormat(String text) throws DateTimeParseException {
        return LocalDate.parse(text, outputFormat);
    }
}
