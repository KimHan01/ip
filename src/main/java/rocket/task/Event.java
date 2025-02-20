package rocket.task;

import rocket.command.AddCommand;
import rocket.command.Command;
import rocket.command.EmptyTaskNameCommand;
import rocket.command.InvalidDateCommand;
import rocket.command.InvalidFormatCommand;
import rocket.exception.EmptyTaskNameException;
import rocket.formatter.CustomDateFormatter;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents an Event task with a start date(from) and an end date(to).
 */
public class Event extends Task {
    private LocalDate from;
    private LocalDate to;
    private String fromOutput; // Formatted output string of from
    private String toOutput; // Formatted output string of to

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
    @Override
    public String toTxt() {
        String mark = super.getMark() ? "1" : "0";
        return "E|" + mark + "|" + super.getName() + "|" + fromOutput + "|" + toOutput + "\n";
    }

    @Override
    public TaskType getType() {
        return TaskType.EVENT;
    }

    /**
     * Returns the start date of the event
     * @return the start date of the event as a LocalDate
     */
    @Override
    public LocalDate getFrom() {
        return this.from;
    }

    /**
     * Returns the end date of the event
     * @return the end date of the event as a LocalDate
     */
    @Override
    public LocalDate getTo() {
        return this.to;
    }

    /**
     * Returns task description of {@code Event} object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + fromOutput + " to: " + toOutput + ")";
    }

    public void updateTo(LocalDate date) {
        this.to = date;
        this.toOutput = CustomDateFormatter.formatOutput(to);
    }

    public void updateFrom(LocalDate date) {
        this.from = date;
        this.fromOutput = CustomDateFormatter.formatOutput(from);
    }

    /**
     * Returns an {@code Event} object from a formatted String without its header("E|").
     * @throws ArrayIndexOutOfBoundsException if the input String is not formatted correctly
     */
    public static Event fromTxt(String body) throws ArrayIndexOutOfBoundsException {
        // Format of given body should be: 0/1|NAME|FROM|TO
        String[] parts = body.split("\\|");
        boolean mark = parts[0].equals("1");
        String name = parts[1];
        String from = parts[2];
        String to = parts[3];
        return new Event(name, mark, CustomDateFormatter.dateFromOutputFormat(from),
                CustomDateFormatter.dateFromOutputFormat(to));
    }

    /**
     * Checks if the input is an {@code Event} task.
     */
    public static boolean isEvent(String input) {
        return input.length() > 6
                && input.substring(0, 5).equalsIgnoreCase(TaskType.EVENT.name())
                && input.substring(5, 6).isBlank();
    }

    /**
     * Returns a {@code AddCommand} for an {@code Event} task
     * from the given input if valid,
     * otherwise an appropriate error {@code Command} is returned.
     */
    public static Command getAddEventCommand(String input) {
        try {
            Event event = Event.createEventFromInput(input);
            return new AddCommand(event, false);
        } catch (EmptyTaskNameException e) {
            return new EmptyTaskNameCommand();
        } catch (IllegalArgumentException e) {
            return new InvalidFormatCommand();
        } catch (DateTimeParseException e) {
            return new InvalidDateCommand();
        }
    }

    private static Event createEventFromInput(String input)
            throws EmptyTaskNameException, IllegalArgumentException, DateTimeParseException {
        String[] splitFrom = input.substring(6).split("/from", 0);
        String name = splitFrom[0].trim();
        if (name.isBlank()) {
            throw new EmptyTaskNameException("Blank Event name given");
        }
        if (splitFrom.length != 2) {
            throw new IllegalArgumentException("Invalid Event format");
        }
        String[] splitTo = splitFrom[1].split("/to", 0);
        if (splitTo.length != 2) {
            throw new IllegalArgumentException("Invalid Event format");
        }
        LocalDate to = CustomDateFormatter.dateFromInputFormat(splitTo[0].trim());
        LocalDate from = CustomDateFormatter.dateFromInputFormat(splitTo[1].trim());
        return new Event(name, false, to, from);
    }
}
