package rocket.task;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    private final LocalDate date = LocalDate.of(2030, 12, 30);
    private final String dateStr = "Dec 30 2030";
    @Test
    public void toTxt_markDone_success() {
        assertEquals("D|1|test1|" + dateStr + "\n",
                new Deadline("test1", true, date).toTxt());
    }

    @Test
    public void toTxt_markNotDone_success() {
        assertEquals("D|0|test1|" + dateStr + "\n",
                new Deadline("test1", false, date).toTxt());
    }

    @Test
    public void toTxt_alphanumericTodoName_success() {
        assertEquals("D|0|0|" + dateStr + "\n",
                new Deadline("0", false, date).toTxt());
        assertEquals("D|0|a|" + dateStr + "\n",
                new Deadline("a", false, date).toTxt());
        assertEquals("D|0|test1|" + dateStr + "\n",
                new Deadline("test1", false, date).toTxt());
        assertEquals("D|0|1Test|" + dateStr + "\n",
                new Deadline("1Test", false, date).toTxt());
    }

    @Test
    public void toTxt_specialCharactersTodoName_success() {
        assertEquals("D|0|!@#$%^&*()-_=+`~{[}]:;',<.>?/||" + dateStr + "\n",
                new Deadline("!@#$%^&*()-_=+`~{[}]:;',<.>?/|", false, date).toTxt());
        assertEquals("D|0|\"\\|" + dateStr + "\n",
                new Deadline("\"\\", false, date).toTxt());
    }

    @Test
    public void toTxt_dividerInMiddle_success() {
        assertEquals("D|0|test1|test2|" + dateStr + "\n",
                new Deadline("test1|test2", false, date).toTxt());
    }

    @Test
    public void fromTxt_correctFormatInput_success() {
        assertEquals(new Deadline("test1", false, date).toString(),
                Deadline.fromTxt("0|test1|" + dateStr).toString());
        assertEquals(new Deadline("1Test", true, date).toString(),
                Deadline.fromTxt("1|1Test|" + dateStr).toString());
    }

    @Test
    public void fromTxt_specialCharactersDeadlineName_success() {
        assertEquals(new Deadline("!@#$%^&*()-_=+`~{[}]:;',<.>?/|", false, date).toString(),
                Deadline.fromTxt("0|!@#$%^&*()-_=+`~{[}]:;',<.>?/||" + dateStr).toString());
        assertEquals(new Deadline("\"\\", false, date).toString(),
                Deadline.fromTxt("0|\"\\|" + dateStr).toString());
    }

    @Test
    public void fromTxt_dividerInMiddle_success() {
        assertEquals(new Deadline("test1|test2", false, date).toString(),
                Deadline.fromTxt("0|test1|test2|" + dateStr).toString());
    }
}
