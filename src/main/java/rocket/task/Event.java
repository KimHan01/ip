package rocket.task;

import rocket.formatter.CustomDateFormatter;

import java.time.LocalDate;

/**
 * Represents an Event task with a start date(from) and an end date(to).
 */
public class Event extends Task {
    private LocalDate from;
    private LocalDate to;
    private final String fromOutput; // Formatted output string of from
    private final String toOutput; // Formatted output string of to

    /**
     * Creates an {@code Event} object with a name, mark, start date(from) and end date(to).
     */
    public Event(String taskName, boolean mark, LocalDate from, LocalDate to) {
        super(taskName, mark);
        this.from = from;
        this.to = to;
        this.fromOutput = CustomDateFormatter.formatOutput(from);
        this.toOutput = CustomDateFormatter.formatOutput(to);
    }

    /**
     * Returns formatted String representation for storage file of {@code Event} object.
     * Format to be returned is "E|MARK|NAME|FROM|TO".
     */
    // Format: E|0/1|NAME|FROM|TO
    @Override
    public String toTxt() {
        String mark = super.getMark() ? "1" : "0";
        return "E|" + mark + "|" + super.getName() + "|" + fromOutput + "|" + toOutput + "\n";
    }

    @Override
    public TaskType getType() {
        return TaskType.EVENT;
    }

    @Override
    public LocalDate getFrom() {
        return this.from;
    }

    @Override
    public LocalDate getTo() {
        return this.to;
    }

    /**
     * Returns an {@code Event} object from a formatted String without its header("E|").
     * @throws ArrayIndexOutOfBoundsException if the input String is not formatted correctly
     */
    public static Event fromTxt(String body) throws ArrayIndexOutOfBoundsException{
        String[] parts = body.split("\\|");
        boolean mark = parts[0].equals("1");
        String name = parts[1];
        String from = parts[2];
        String to = parts[3];
        return new Event(name, mark, CustomDateFormatter.dateFromOutputFormat(from),
                CustomDateFormatter.dateFromOutputFormat(to));
    }

    /**
     * Returns task description of {@code Event} object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + fromOutput + " to: " + toOutput + ")";
    }
}
