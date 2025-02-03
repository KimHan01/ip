package chatbot.command;

import chatbot.task.Task;
import chatbot.TaskList;
import chatbot.Ui;
import chatbot.chatbot.storage.Storage;

public class UnmarkCommand extends Command {
    private final int indexToUnmark;

    public UnmarkCommand(int taskNum) {
        super(false);
        this.indexToUnmark = taskNum - 1;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        try {
            Task unmarkedTask = list.unmark(indexToUnmark);
            //storage.updateStorage(list);
            ui.readUnmarkTask(unmarkedTask);
        } catch (IndexOutOfBoundsException e) {
            ui.readInvalidUnmarkRequest();
        }
    }
}
