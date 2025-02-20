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
 * Represents a Deadline, containing a single due date to complete the task by.
 */
public class Deadline extends Task {
    private LocalDate by;
    private String dateTimeOutput;

    /**
     * Creates a {@code Deadline} object with a name, mark and due date(by).
     * Format of date should be "yyyy-MM-dd HHmm".
     */
    public Deadline(String taskName, boolean mark, LocalDate by) {
        super(taskName, mark);
        this.by = by;
        this.dateTimeOutput = CustomDateFormatter.formatOutput(by);
    }

    /**
     * Returns formatted String representation for storage file of {@code Deadline} object.
     * Format to be returned is "D|MARK|NAME|BY".
     * @return formatted String for storage
     */
    @Override
    public String toTxt() {
        String mark = super.getMark() ? "1" : "0";
        return "D|" + mark + "|" + super.getName() + "|" + dateTimeOutput + "\n";
    }

    @Override
    public TaskType getType() {
        return TaskType.DEADLINE;
    }

    /**
     * Returns the due date of the deadline
     * @return the due date of the deadline as a LocalDate
     */
    @Override
    public LocalDate getBy() {
        return this.by;
    }

    /**
     * Returns task description of {@code Deadline} object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateTimeOutput + ")";
    }

    public void updateBy(LocalDate newBy) {
        this.by = newBy;
        this.dateTimeOutput = CustomDateFormatter.formatOutput(newBy);
    }

    /**
     * Returns a {@code Deadline} object from a formatted String without its header("D|").
     * @throws ArrayIndexOutOfBoundsException if the input String is not formatted correctly
     */
    public static Deadline fromTxt(String body) throws ArrayIndexOutOfBoundsException {
        // Format of given body should be: 0/1|NAME|BY
        String[] parts = body.split("\\|", 2);
        boolean mark = parts[0].equals("1");
        String nameAndBy = parts[1];
        int index = nameAndBy.lastIndexOf("|");
        String name = nameAndBy.substring(0, index);
        String by = nameAndBy.substring(index + 1);
        return new Deadline(name, mark, CustomDateFormatter.dateFromOutputFormat(by));
    }

    /**
     * Checks if the input is a {@code Deadline} task.
     * Deadline task format should be "deadline NAME /by yyyy-MM-dd HHmm".
     */
    public static boolean isDeadline(String input) {
        return input.length() > 9
                && input.substring(0, 8).equalsIgnoreCase(TaskType.DEADLINE.name())
                && input.substring(8, 9).isBlank();
    }

    /**
     * Returns the {@code AddCommand} for a Deadline task
     * from the given input if valid,
     * otherwise an appropriate error {@code Command} is returned.
     */
    public static Command getAddDeadlineCommand(String input) {
        try {
            Deadline deadline = Deadline.createDeadlineFromInput(input);
            return new AddCommand(deadline, false);
        } catch (EmptyTaskNameException e) {
            return new EmptyTaskNameCommand();
        } catch (IllegalArgumentException e) {
            return new InvalidFormatCommand();
        } catch (DateTimeParseException e) {
            return new InvalidDateCommand();
        }
    }

    private static Deadline createDeadlineFromInput(String input)
            throws EmptyTaskNameException, IllegalArgumentException, DateTimeParseException {
        String[] parts = input.substring(9).split("/by", 0);
        String name = parts[0].trim();
        if (name.isBlank()) {
            throw new EmptyTaskNameException("Blank Deadline name given");
        }
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid deadline format");
        }
        LocalDate dateTime = CustomDateFormatter.dateFromInputFormat(parts[1].trim());
        return new Deadline(name, false, dateTime);
    }
}
