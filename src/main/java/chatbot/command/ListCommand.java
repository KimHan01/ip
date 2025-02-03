package chatbot.command;

import chatbot.TaskList;
import chatbot.Ui;
import chatbot.chatbot.storage.Storage;

public class ListCommand extends Command {
    public ListCommand() {
        super(false);
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        storage.updateStorage(list);
        if (list.getSize() == 0) {
            ui.readEmptyList();
        } else {
            ui.readListItems(list.getTasks());
        }
    }
}
