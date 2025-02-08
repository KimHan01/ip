package rocket.parser;

import rocket.formatter.CustomDateFormatter;
import rocket.command.*;
import rocket.common.Utils;
import rocket.exception.EmptyTaskNameException;
import rocket.task.Deadline;
import rocket.task.Event;
import rocket.task.Todo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents a parser that handles parsing user input into commands.
 */
public class Parser {
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    private static final String DELETE = "delete";
    private static final String MARK = "mark";
    private static final String UNMARK = "unmark";
    private static final String EXIT = "bye";
    private static final String LIST = "list";
    private static final String FIND = "find";

    /**
     * Parses user input into a command.
     *
     * @param input User input.
     * @return Command object.
     */
    public static Command parse(String input) {
        String commandType = getCommandType(input);
        switch (commandType) {
            case TODO:
                try {
                    Todo todo = createTodoFromInput(input);
                    return new AddCommand(todo, false);
                } catch (EmptyTaskNameException e) {
                    return new EmptyTaskNameCommand();
                }
            case DEADLINE:
                try {
                    Deadline deadline = createDeadlineFromInput(input);
                    return new AddCommand(deadline, false);
                } catch (EmptyTaskNameException e) {
                    return new EmptyTaskNameCommand();
                } catch (IllegalArgumentException e) {
                    return new InvalidFormatCommand();
                } catch (DateTimeParseException e) {
                    return new InvalidDateCommand();
                }
            case EVENT:
                try {
                    Event event = createEventFromInput(input);
                    return new AddCommand(event, false);
                } catch (EmptyTaskNameException e) {
                    return new EmptyTaskNameCommand();
                } catch (IllegalArgumentException e) {
                    return new InvalidFormatCommand();
                } catch (DateTimeParseException e) {
                    return new InvalidDateCommand();
                }
            case DELETE:
                try {
                    int taskNum = getTaskNumToDelete(input);
                    return new DeleteCommand(taskNum);
                } catch (NumberFormatException e) {
                    return new InvalidFormatCommand();
                }
            case MARK:
                try {
                    int taskNum = getTaskNumToMark(input);
                    return new MarkCommand(taskNum);
                } catch (NumberFormatException e) {
                    return new InvalidFormatCommand();
                }
            case UNMARK:
                try {
                    int taskNum = getTaskNumToUnmark(input);
                    return new UnmarkCommand(taskNum);
                } catch (NumberFormatException e) {
                    return new InvalidFormatCommand();
                }
            case EXIT:
                return new ExitCommand();
            case LIST:
                return new ListCommand();
            case FIND:
                String keyword = input.substring(5);
                return new FindCommand(keyword);
            default:
                return new InvalidFormatCommand();
        }
    }

    private static String getCommandType(String input) {
        if (isTodo(input)) {
            return TODO;
        } else if (isDeadline(input)) {
            return DEADLINE;
        } else if (isEvent(input)) {
            return EVENT;
        } else if (isDelete(input)) {
            return DELETE;
        } else if (isMark(input)) {
            return MARK;
        } else if (isUnmark(input)) {
            return UNMARK;
        } else if (isExit(input)) {
            return EXIT;
        } else if (isList(input)) {
            return LIST;
        } else if (isFind(input)) {
            return FIND;
        } else {
            return "invalid";
        }
    }

    // format: todo NAME
    private static boolean isTodo(String input) {
        return input.length() > 5
                && input.substring(0, 4).equalsIgnoreCase(TODO)
                && input.substring(4, 5).isBlank();
    }

    private static boolean isDeadline(String input) {
        return input.length() > 9
                && input.substring(0, 8).equalsIgnoreCase(DEADLINE)
                && input.substring(8, 9).isBlank();
    }

    private static boolean isEvent(String input) {
        return input.length() > 6
                && input.substring(0, 5).equalsIgnoreCase(EVENT)
                && input.substring(5, 6).isBlank();
    }

    private static boolean isDelete(String input) {
        return (input.length() > 7 && input.substring(0, 6).equalsIgnoreCase("delete"));
    }

    private static boolean isMark(String input) {
        return input.length() > 5
                && input.substring(0, 4).equalsIgnoreCase(MARK)
                && input.substring(4, 5).isBlank()
                && Utils.isInteger(input.substring(5));
    }

    private static boolean isUnmark(String input) {
        return input.length() > 7
                && input.substring(0, 6).equalsIgnoreCase(UNMARK)
                && input.substring(6, 7).isBlank()
                && Utils.isInteger(input.substring(7));
    }

    private static boolean isFind(String input) {
        return input.length() > 5
                && input.substring(0, 4).equalsIgnoreCase(FIND)
                && input.substring(4, 5).isBlank();
    }

    private static boolean isExit(String input) {
        return input.equalsIgnoreCase(EXIT);
    }

    private static boolean isList(String input) {
        return input.equalsIgnoreCase(LIST);
    }

    private static Todo createTodoFromInput(String input) throws EmptyTaskNameException {
        String name = input.substring(5);
        if (name.isBlank()) {
            throw new EmptyTaskNameException("Blank Todo name given");
        }
        return new Todo(name.trim(), false);
    }

    private static Deadline createDeadlineFromInput(String input)
            throws EmptyTaskNameException, IllegalArgumentException, DateTimeParseException {
        String[] parts = input.substring(9).split("/by", 0);
        String name = parts[0].trim();
        if (name.isBlank()) { // If name is blank
            throw new EmptyTaskNameException("Blank Deadline name given");
        }
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid deadline format");
        }
        LocalDate dateTime = CustomDateFormatter.dateFromInputFormat(parts[1].trim());
        return new Deadline(name, false, dateTime);
    }

    private static Event createEventFromInput(String input)
            throws EmptyTaskNameException, IllegalArgumentException, DateTimeParseException {
        String[] splitFrom = input.substring(6).split("/from", 0);
        String name = splitFrom[0].trim();
        if (name.isBlank()) {
            throw new EmptyTaskNameException("Blank Event name given");
        }
        if (splitFrom.length != 2) {
            throw new IllegalArgumentException("Invalid Event format");
        }
        String[] splitTo = splitFrom[1].split("/to", 0);
        if (splitTo.length != 2) {
            throw new IllegalArgumentException("Invalid Event format");
        }
        LocalDate to = CustomDateFormatter.dateFromInputFormat(splitTo[0].trim());
        LocalDate from = CustomDateFormatter.dateFromInputFormat(splitTo[1].trim());
        return new Event(name, false, to, from);
    }

    private static int getTaskNumToDelete(String input) throws NumberFormatException {
        String taskNum = input.substring(7);
        return Integer.parseInt(taskNum);
    }

    private static int getTaskNumToMark(String input) throws NumberFormatException {
        String taskNum = input.substring(5);
        return Integer.parseInt(taskNum);
    }

    private static int getTaskNumToUnmark(String input) throws NumberFormatException {
        String taskNum = input.substring(7);
        return Integer.parseInt(taskNum);
    }
}
