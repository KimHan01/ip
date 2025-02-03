package chatbot.task;

import chatbot.CustomDateFormatter;
import chatbot.TaskType;

import java.time.LocalDate;

public class Event extends Task {
    private LocalDate from;
    private LocalDate to;
    private final String fromOutput; // Formatted output string of from
    private final String toOutput; // Formatted output string of to

    public Event(String taskName, boolean mark, LocalDate from, LocalDate to) {
        super(taskName, mark);
        this.from = from;
        this.to = to;
        this.fromOutput = CustomDateFormatter.formatOutput(from);
        this.toOutput = CustomDateFormatter.formatOutput(to);
    }

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

    // Format: 0/1|NAME|FROM|TO
    public static Event fromTxt(String body) throws ArrayIndexOutOfBoundsException{
        String[] parts = body.split("\\|");
        boolean mark = parts[0].equals("1");
        String name = parts[1];
        String from = parts[2];
        String to = parts[3];
        return new Event(name, mark, CustomDateFormatter.dateFromOutputFormat(from),
                CustomDateFormatter.dateFromOutputFormat(to));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + fromOutput + " to: " + toOutput + ")";
    }
}
