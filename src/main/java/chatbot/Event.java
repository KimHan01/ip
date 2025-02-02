package chatbot;

public class Event extends Task{
    private String from;
    private String to;

    public Event(String taskName, boolean mark, String from, String to) {
        super(taskName, mark);
        this.from = from;
        this.to = to;
    }


    public String getTo() {
        return this.to;
    }

    // Format: E|0/1|NAME|FROM|TO
    @Override
    public String toTxt() {
        String mark = super.getMark() ? "1" : "0";
        return "E|" + mark + "|" + super.getName() + "|" + this.from + "|" + this.to + "\n";
    }

    // Format: 0/1|NAME|FROM|TO
    public static Event fromTxt(String body) {
        try {
            String[] parts = body.split("\\|");
            boolean mark = parts[0].equals("1");
            String name = parts[1];
            String from = parts[2];
            String to = parts[3];
            return new Event(name, mark, from, to);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ArrayIndexOutOfBoundsException when calling fromTxt for Event class, " +
                    "returning Event with name: invalid");
            return new Event("invalid", false, "-", "-");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
