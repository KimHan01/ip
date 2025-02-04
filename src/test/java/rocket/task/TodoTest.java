package rocket.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void toTxt_markDone_success() {
        assertEquals("T|1|test1\n", new Todo("test1", true).toTxt());
    }

    @Test
    public void toTxt_markNotDone_success() {
        assertEquals("T|0|test1\n", new Todo("test1", false).toTxt());
    }

    @Test
    public void toTxt_alphanumericTodoName_success() {
        assertEquals("T|0|0\n", new Todo("0", false).toTxt());
        assertEquals("T|0|a\n", new Todo("a", false).toTxt());
        assertEquals("T|0|test1\n", new Todo("test1", false).toTxt());
        assertEquals("T|0|1Test\n", new Todo("1Test", false).toTxt());
    }

    @Test
    public void toTxt_specialCharactersTodoName_success() {
        assertEquals("T|0|!@#$%^&*()-_=+`~{[}]:;',<.>?/|\n",
                new Todo("!@#$%^&*()-_=+`~{[}]:;',<.>?/|", false).toTxt());
        assertEquals("T|0|\"\\\n", new Todo("\"\\", false).toTxt());
    }

    @Test
    public void toTxt_dividerInMiddle_success() {
        assertEquals("T|0|test1|test2\n", new Todo("test1|test2", false).toTxt());
    }

    @Test
    public void fromTxt_correctFormatInput_success() {
        assertEquals(new Todo("test1", false).toString(), Todo.fromTxt("0|test1").toString());
        assertEquals(new Todo("1Test", true).toString(), Todo.fromTxt("1|1Test").toString());
    }

    @Test
    public void fromTxt_specialCharactersTodoName_success() {
        assertEquals(new Todo("!@#$%^&*()-_=+`~{[}]:;',<.>?/|", false).toString(),
                Todo.fromTxt("0|!@#$%^&*()-_=+`~{[}]:;',<.>?/|").toString());
        assertEquals(new Todo("\"\\", false).toString(),
                Todo.fromTxt("0|\"\\").toString());
    }

    @Test
    public void fromTxt_dividerInMiddle_success() {
        assertEquals(new Todo("test1|test2", false).toString(),
                Todo.fromTxt("0|test1|test2").toString());
    }
}
