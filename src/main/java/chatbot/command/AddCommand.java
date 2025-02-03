package chatbot.command;

import chatbot.task.Task;
import chatbot.TaskList;
import chatbot.Ui;
import chatbot.chatbot.storage.Storage;

public class AddCommand extends Command {
    private final Task taskToAdd;

    public AddCommand(Task taskToAdd, boolean isEmpty) {
        super(false);
        this.taskToAdd = taskToAdd;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        list.add(taskToAdd);
        //storage.updateStorage(list);
        ui.readAddTask(taskToAdd, list.getSize());
    }
}
