package rocket.command;

import rocket.storage.Storage;
import rocket.task.Task;
import rocket.task.TaskList;
import rocket.ui.Ui;

public class FindCommand extends Command {
    String keyword;

    public FindCommand(String keyword) {
        super(false);
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        TaskList found = new TaskList();
        for (Task task : list.getTasks()) {
            if (task.getName().contains(keyword)) {
                found.add(task);
            }
        }
        ui.readFind(found);
    }
}
