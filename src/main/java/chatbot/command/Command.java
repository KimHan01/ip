package chatbot.command;

import chatbot.TaskList;
import chatbot.Ui;
import chatbot.chatbot.storage.Storage;

public abstract class Command {
    private final boolean isExit; // Checks the command is an exit command

    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    public boolean isExit() {
        return this.isExit;
    }

    public abstract void execute(TaskList list, Ui ui, Storage storage);
}
