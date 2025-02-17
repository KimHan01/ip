package rocket.command;

import rocket.storage.Storage;
import rocket.task.Task;
import rocket.task.TaskList;
import rocket.ui.Ui;

/**
 * Represents a command to find tasks in the task list.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Creates a new {@code FindCommand} with the given keyword to search for.
     */
    public FindCommand(String keyword) {
        super(false);
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        TaskList found = new TaskList();
        for (Task task : list.getTasks()) {
            if (task.getName().contains(keyword)) {
                found.add(task);
            }
        }
        String res = getFindResponse(found);
        ui.read(res);
        return res;
    }

    /**
     * Checks if the given input is a {@code FindCommand}.
     */
    public static boolean isFind(String input) {
        return input.length() > 5
                && input.substring(0, 4).equalsIgnoreCase(InputCommandType.FIND.name())
                && input.substring(4, 5).isBlank();
    }

    /**
     * Returns the {@code FindCommand} from the given input.
     */
    public static FindCommand getFindCommand(String input) {
        String keyword = input.substring(InputCommandType.FIND.name().length() + 1);
        return new FindCommand(keyword);
    }

    /**
     * Returns the task description of all tasks found in the task list by the Find command.
     */
    private String getFindResponse(TaskList tasks) {
        StringBuilder res = new StringBuilder();
        res.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.getSize(); i++) {
            int taskNum = i + 1;
            res.append(taskNum).append(".")
                    .append(tasks.get(i).toString()).append("\n");
        }
        return res.toString();
    }
}
