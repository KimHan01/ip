package rocket.command;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import rocket.formatter.CustomDateFormatter;
import rocket.storage.Storage;
import rocket.task.Task;
import rocket.task.TaskList;
import rocket.ui.Ui;

/**
 * Represents a command to edit the date of a task
 */
public class EditDateCommand extends EditCommand {
    private final String fieldToChange;
    private final int index;

    /**
     * Creates a command to edit the date of a task
     * @param fieldToChange Includes field specifier (Format: [Field specifier][New value of field])
     * @param taskNum The task number of the task
     */
    public EditDateCommand(String fieldToChange, int taskNum) {
        this.fieldToChange = fieldToChange;
        this.index = taskNum - 1;
    }

    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        return getUpdateDateMessage(list, ui, storage, fieldToChange);
    }

    private String getUpdateDateMessage(TaskList list, Ui ui, Storage storage, String inputDate) {
        // inputDate will start with either "/by", "/from" or "to".
        try {
            Task[] oldAndNewTask = updateDate(list, storage, inputDate);
            storage.updateStorage(list);
            ui.read(getEditMessage(oldAndNewTask[0], oldAndNewTask[1]));
            return getEditMessage(oldAndNewTask[0], oldAndNewTask[1]);
        } catch (DateTimeParseException e) {
            ui.read(getInvalidDateMessage());
            return getInvalidDateMessage();
        } catch (IndexOutOfBoundsException e) {
            ui.read(getIndexOutOfBoundsMessage());
            return getIndexOutOfBoundsMessage();
        } catch (ClassCastException e) {
            ui.read(getClassCastExceptionMessage());
            return getClassCastExceptionMessage();
        }
    }

    private Task[] updateDate(TaskList list, Storage storage, String inputDate) {
        String date;
        if (inputDate.startsWith("/by")) {
            date = inputDate.substring(3).trim();
        } else if (inputDate.startsWith("/from")) {
            date = inputDate.substring(5).trim();
        } else {
            // inputDate starts with "/to"
            date = inputDate.substring(3).trim();
        }

        LocalDate newDate = CustomDateFormatter.dateFromInputFormat(date);
        Task oldTask = list.get(index);
        if (inputDate.startsWith("/by")) {
            list.updateDeadlineDate(index, newDate);
        } else if (inputDate.startsWith("/from")) {
            list.updateEventStartDate(index, newDate);
        } else {
            // inputDate starts with "/to"
            list.updateEventEndDate(index, newDate);
        }
        storage.updateStorage(list);
        Task newTask = list.get(index);
        return new Task[] {oldTask, newTask};
    }

    private String getInvalidDateMessage() {
        return "Oh come on, we've been through this! Date's gotta be in yyyy-mm-dd format. Try again!";
    }
}
