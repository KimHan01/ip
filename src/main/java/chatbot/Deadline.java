package chatbot;

public class Deadline extends Task{
    private String by;

    // Input format is deadline <TaskName> /by <Date>
    public Deadline(String taskName, boolean mark, String by) {
        super(taskName, mark);
        this.by = by;
    }

    public static boolean isDeadline(String input) {
        return input.length() > 9
                && input.substring(0, 8).equalsIgnoreCase("deadline")
                && input.substring(8, 9).isBlank();
    }

    // Format: D|0/1|NAME|BY
    @Override
    public String toTxt() {
        String mark = super.getMark() ? "1" : "0";
        return "D|" + mark + "|" + super.getName() + "|" + this.by + "\n";
    }

    // Format: 0/1|NAME|BY
    public static Deadline fromTxt(String body) {
        try {
            String[] parts = body.split("\\|");
            boolean mark = parts[0].equals("1");
            String name = parts[1];
            String by = parts[2];
            return new Deadline(name, mark, by);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ArrayIndexOutOfBoundsException when calling fromTxt for Deadline class, " +
                    "returning Deadline with name: invalid");
            return new Deadline("invalid", false, "-");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
