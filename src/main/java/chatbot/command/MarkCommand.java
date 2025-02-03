package chatbot.command;

import chatbot.task.Task;
import chatbot.TaskList;
import chatbot.Ui;
import chatbot.chatbot.storage.Storage;

public class MarkCommand extends Command {
    private final int indexToMark;

    public MarkCommand(int taskNum) {
        super(false);
        this.indexToMark = taskNum - 1;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        try {
            Task markedTask = list.mark(indexToMark);
            //storage.updateStorage(list);
            ui.readMarkTask(markedTask);
        } catch (IndexOutOfBoundsException e) {
            ui.readInvalidMarkRequest();
        }
    }
}
