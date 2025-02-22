package rocket.command;

import rocket.exception.EmptyTaskNameException;
import rocket.storage.Storage;
import rocket.task.Task;
import rocket.task.TaskList;
import rocket.ui.Ui;

/**
 * Represents a command to edit the name of a task
 */
public class EditNameCommand extends EditCommand {
    private final String newName;
    private final int index;

    /**
     * Creates a command to edit the name of a task
     * @param newName The new name of the task
     * @param taskNum The task number of the task
     */
    public EditNameCommand(String newName, int taskNum) {
        this.newName = newName;
        this.index = taskNum - 1;
    }

    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        return getUpdateNameMessage(list, ui, storage, newName, index);
    }

    private String getUpdateNameMessage(TaskList list, Ui ui, Storage storage, String newName, int index) {
        try {
            Task[] oldAndNewTask = updateName(list, newName, index);
            storage.updateStorage(list);
            ui.read(getEditMessage(oldAndNewTask[0], oldAndNewTask[1]));
            return getEditMessage(oldAndNewTask[0], oldAndNewTask[1]);
        } catch (EmptyTaskNameException e) {
            ui.read(getEmptyNameMessage());
            return getEmptyNameMessage();
        } catch (IndexOutOfBoundsException e) {
            ui.read(getIndexOutOfBoundsMessage());
            return getIndexOutOfBoundsMessage();
        }
    }

    private Task[] updateName(TaskList list, String newName, int index)
            throws EmptyTaskNameException, IndexOutOfBoundsException {
        if (newName.isBlank()) {
            throw new EmptyTaskNameException("No name given");
        }
        Task oldTask = list.get(index);
        list.updateTaskName(index, newName);
        Task newTask = list.get(index);
        return new Task[]{oldTask, newTask};
    }
}
