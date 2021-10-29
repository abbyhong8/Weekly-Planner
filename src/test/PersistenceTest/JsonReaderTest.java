package PersistenceTest;

import model.*;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/donotexist.json");
        try {
            Week w = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderemptyWeek() {
        JsonReader reader = new JsonReader("./data/testReaderWeekWithNoTasks.json");
        try {
            Week ww = reader.read();
            assertEquals(7, ww.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWeek() {
        JsonReader reader = new JsonReader("./data/testReaderWithTasks.json");
        try {
            Week ww = reader.read();
            Day day = ww.getDays()[0];
            assertEquals(7, ww.size());
            assertEquals("abb", day.getTask(0).getWork());
            assertFalse(day.getTask(0).isComplete());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderWeekCompleted() {
        JsonReader reader = new JsonReader("./data/testReaderWithCompletedTasks.json");
        try {
            Week ww = reader.read();
            Day day = ww.getDays()[0];
            assertEquals(7, ww.size());
            assertEquals("abb", day.getTask(0).getWork());
            assertTrue(day.getTask(0).isComplete());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }

    }

}
