package rocket.task;

import rocket.formatter.CustomDateFormatter;

import java.time.LocalDate;

public class Deadline extends Task {
    private final LocalDate by;
    private final String DateTimeOutput;

    // Input format is deadline <TaskName> /by <Date>,
    // <Date> format from argument should be "yyyy-MM-dd HHmm"
    public Deadline(String taskName, boolean mark, LocalDate by) {
        super(taskName, mark);
        this.by = by;
        this.DateTimeOutput = CustomDateFormatter.formatOutput(by);
    }

    // Format: D|0/1|NAME|BY, converting BY into output format
    @Override
    public String toTxt() {
        String mark = super.getMark() ? "1" : "0";
        return "D|" + mark + "|" + super.getName() + "|" + DateTimeOutput + "\n";
    }

    @Override
    public TaskType getType() {
        return TaskType.DEADLINE;
    }

    @Override
    public LocalDate getBy() {
        return this.by;
    }

    // Format: 0/1|NAME|BY, converts BY from output format into LocalDateTime
    public static Deadline fromTxt(String body) throws ArrayIndexOutOfBoundsException {
        String[] parts = body.split("\\|", 2);
        boolean mark = parts[0].equals("1");
        String nameAndBy = parts[1];
        int index = nameAndBy.lastIndexOf("|");
        String name = nameAndBy.substring(0, index);
        String by = nameAndBy.substring(index + 1);
        return new Deadline(name, mark, CustomDateFormatter.dateFromOutputFormat(by));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateTimeOutput + ")";
    }
}
