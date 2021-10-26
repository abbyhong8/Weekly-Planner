package PersistenceTest;

import model.*;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class JsonTest {
    protected void checkTask(String name, Boolean completion, Task task) {
        assertEquals(name, task.getWork());
        assertEquals(completion, task.isComplete());
    }
}
