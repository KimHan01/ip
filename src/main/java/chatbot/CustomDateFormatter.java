package chatbot;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CustomDateFormatter {
    private final static DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final static DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");

    // Given a string of input format("yyyy-MM-dd"), outputs LocalDateTime
    public static LocalDate dateFromInputFormat(String input) throws DateTimeParseException{
        return LocalDate.parse(input, inputFormat);
    }

    // Given a LocalDateTime, returns a String in outputFormat
    public static String formatOutput(LocalDate inputDateTime) {
        return inputDateTime.format(outputFormat);
    }

    // Given a string of output format("MMM dd yyyy"), outputs LocalDateTime
    public static LocalDate dateFromOutputFormat(String text) throws DateTimeParseException {
        return LocalDate.parse(text, outputFormat);
    }
}
