import model.Day;
import model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {
    public Task task;

    @BeforeEach
    public void runbefore() {
        task = new Task("a");
    }

    @Test
    public void testIsComplete() {
        task = new Task("Math test");
        assertFalse(task.isComplete());
        task.markComplete();
        assertTrue(task.isComplete());

    }




}
