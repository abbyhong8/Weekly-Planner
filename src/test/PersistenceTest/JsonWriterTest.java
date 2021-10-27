package PersistenceTest;
import model.*;
import org.json.*;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest{

    @Test
    void testWriterInvalidFile() {
        try {
            Week w = new Week();
            JsonWriter writer = new JsonWriter("./data/my/illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            Week w = new Week();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWeek.json");
            writer.open();
            writer.write(w);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWeek.json");
            w = reader.read();
            assertEquals(7, w.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWeek() {
        try {
            Week w = new Week();
            Day day1 = new Day(0);
            Task task = new Task("a");
            day1.addTask(task);
            w.addDay(day1);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWeek.json");
            writer.open();
            writer.write(w);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWeek.json");
            w = reader.read();
            assertEquals(7, w.size());
            Day d = w.getDays()[0];
            assertEquals(1, d.checkNumber());
            checkTask("a",false,d.getTask(0));


        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
