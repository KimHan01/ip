package rocket.task;

public class Todo extends Task {
    public Todo(String taskName, boolean mark) {
        super(taskName, mark);
    }

    // Format: T|0/1|NAME, returns String representation in txt file
    @Override
    public String toTxt() {
        String mark = super.getMark() ? "1" : "0";
        return "T|" + mark + "|" + super.getName() + "\n";
    }

    @Override
    public TaskType getType() {
        return TaskType.TODO;
    }

    // Returns Todo object from txt line, takes in txt String body without header (Format: 0/1|NAME)
    public static Todo fromTxt(String body) {
        try {
            String[] parts = body.split("\\|");
            boolean mark = parts[0].equals("1");
            String name = parts[1];
            return new Todo(name, mark);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ArrayIndexOutOfBoundsException when calling fromTxt for Todo class, " +
                    "returning todo with name: invalid");
            return new Todo("invalid", false);
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
