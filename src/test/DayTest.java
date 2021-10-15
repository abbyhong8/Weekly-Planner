
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import model.Day;
import model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DayTest {
    public Day set;
    @BeforeEach
    public void runbefore() {
        set = new Day(0);
    }

    @Test
    public void testAddTask() {
        assertEquals(0,set.checkNumber());
        set.addTask(new Task("Math test"));
        assertEquals(1, set.checkNumber());
        set.addTask(new Task("CPSC210 test"));
        assertEquals(2,set.checkNumber());

    }

    @Test
    public void testRemoveTask() {
        set.addTask(new Task("Math test"));
        set.removeTask("Math test");
        assertEquals(0, set.checkNumber());
        set.addTask(new Task("Math quiz"));
        set.addTask(new Task("CPSC test"));
        set.removeTask("CPSC test");
        assertEquals(1, set.checkNumber());
    }

    @Test
    public void testCheckNumber() {
        assertEquals(0, set.checkNumber());
        set.addTask(new Task("Math quiz"));
        set.addTask(new Task("CPSC test"));
        assertEquals(2, set.checkNumber());

    }

    @Test
    public void testGetTask() {
        set.addTask(new Task("Math quiz"));
        set.addTask(new Task("CPSC test"));
        (set.getTask(0)).equals("Math quiz");
        (set.getTask(1)).equals("CPSC test");
    }

    @Test
    public void testIncompleteWork() {
        assertEquals(0,set.checkIncompleteWork());
        set.addTask(new Task("Math quiz"));
        set.addTask(new Task("CPSC test"));
        assertEquals(2,set.checkIncompleteWork());
        ( set.getTask(1)).markComplete();
        assertEquals(1,set.checkIncompleteWork());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(set.isEmpty());
        set.addTask(new Task("Math quiz"));
        set.addTask(new Task("CPSC test"));
        assertFalse(set.isEmpty());

    }



}






