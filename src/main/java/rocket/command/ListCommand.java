package rocket.command;

import rocket.task.Task;
import rocket.task.TaskList;
import rocket.ui.Ui;
import rocket.storage.Storage;

import java.util.ArrayList;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Creates a new {@code ListCommand}.
     */
    public ListCommand() {
        super(false);
    }

    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        if (list.isEmpty()) {
            ui.readEmptyList();
            return getEmptyListResponse();
        } else {
            ui.readListItems(list.getTasks());
            return getListResponse(list.getTasks());
        }
    }

    /**
     * Response to querying an empty list.
     */
    public String getEmptyListResponse() {
        return "Oh, look at that, your list of tasks is empty as a vacuum in space.\n" +
                "Guess that means you're either super efficient or just really good at procrastinating." +
                " Probably the latter.\nWant me to add \"Make a to-do list\" to your list of tasks?";
    }

    /**
     * Reads all tasks' description in the given list.
     */
    public String getListResponse(ArrayList<Task> list) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            int taskNum = i + 1;
            res.append(taskNum).append(".").append(list.get(i).toString()).append("\n");
        }
        return res.toString();
    }

}
