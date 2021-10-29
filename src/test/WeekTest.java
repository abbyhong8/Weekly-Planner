
import static org.junit.jupiter.api.Assertions.*;


import static org.junit.jupiter.api.Assertions.assertEquals;


import model.Task;
import model.Week;
import model.Day;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class WeekTest {
    Week week;

    @BeforeEach
    void setUp() {
        week = new Week();
    }

//    @Test
//    public void testIsEMpty() {
//       week.isEmpty();
//       Day day1 = new Day(6);
//       week.addDay(day1);
//       assertFalse(week.isEmpty());
//
//    }

    @Test
    public void testAdd() {
        Day day1 = new Day(6);
        Day day2 = new Day(5);
        Day day3 = new Day(2);
        week.addDay(day1);
        week.addDay(day2);
        week.addDay(day3);
        assertNotNull(week.getDays()[6]);
        assertNotNull(week.getDays()[5]);
        assertNull(week.getDays()[4]);
        assertNull(week.getDays()[3]);
        assertNotNull(week.getDays()[2]);
        assertNull(week.getDays()[1]);
        assertNull(week.getDays()[0]);
    }

    @Test
    public void testRemoveDay() {
        Day day1 = new Day(6);
        Day day2 = new Day(5);
        Day day3 = new Day(2);
        week.addDay(day1);
        week.addDay(day2);
        week.addDay(day3);
        assertNotNull(week.getDays()[6]);
        assertNotNull(week.getDays()[5]);
        assertNotNull(week.getDays()[2]);
        week.removeDay(6);
        week.removeDay(5);
        week.removeDay(2);
        assertNull(week.getDays()[6]);
        assertNull(week.getDays()[5]);
        assertNull(week.getDays()[2]);
        assertNull(week.getDays()[1]);
        assertNull(week.getDays()[3]);
        assertNull(week.getDays()[4]);
    }

    @Test
    public void testRemoveAll(){
        assertNull(week.getDays()[6]);
        assertNull(week.getDays()[5]);
        assertNull(week.getDays()[2]);
        Day day1 = new Day(6);
        Day day2 = new Day(5);
        Day day3 = new Day(2);
        week.addDay(day1);
        week.addDay(day2);
        week.addDay(day3);
        assertNotNull(week);
        week.removeAll();
        assertNull(week.getDays()[6]);
        assertNull(week.getDays()[5]);
        assertNull(week.getDays()[2]);
        //

    }

    @Test
    public void testReturnAllIncomplete() {
        Day day1 = new Day(6);
        Day day2 = new Day(5);
        Day day3 = new Day(2);
        day1.addTask(new Task("eat"));
        day2.addTask(new Task("eat"));
        day3.addTask(new Task("eat dinner"));
        week.addDay(day1);
        week.addDay(day2);
        week.addDay(day3);
        assertEquals(3, week.returnAllIncomplete());
        ( day1.getTask(0)).markComplete();
        assertEquals(2, week.returnAllIncomplete());
    }

    @Test
    public void testSize() {
        assertEquals(7,week.size());
    }

    @Test
    public void testIsDayAdded() {
        Day day1 = new Day(6);
        Day day2 = new Day(5);
        Day day3 = new Day(2);
        week.addDay(day1);
        week.addDay(day2);
        week.addDay(day3);
        assertFalse(week.isDayAdded(6));
        assertFalse(week.isDayAdded(5));
        assertFalse(week.isDayAdded(2));
        assertTrue(week.isDayAdded(1));

    }

    @Test
    public void testToJson() {
        Task task1 = new Task("Eat");
        Day day1 = new Day(6);
        day1.addTask(task1);
        week.addDay(day1);
        JSONObject json = week.toJson();
       JSONArray a1 = json.getJSONArray("days");
       assertEquals(1,a1.length());
        JSONObject d1 = a1.getJSONObject(0);
        JSONArray dw = d1.getJSONArray("works");
        JSONObject w1 = dw.getJSONObject(0);
        assertFalse(w1.getBoolean("completion"));
        assertEquals("Eat",
        w1.getString("work"));
        task1.markComplete();
        day1.addTask(task1);
        JSONObject json2 = week.toJson();
        JSONArray a2 = json.getJSONArray("days");
        assertEquals(1,a2.length());
        JSONObject d2 = a2.getJSONObject(0);
        JSONArray dw1 = d2.getJSONArray("works");
        JSONObject w2 = dw1.getJSONObject(0);
        assertFalse(w2.getBoolean("completion"));
    }

    @Test
    public void testDaysToJson() {
        Task task1 = new Task("Eat");
        Day day1 = new Day(6);
        day1.addTask(task1);
        week.addDay(day1);
        JSONArray json = week.daysToJson();
        JSONObject a1 = json.getJSONObject(0);
        JSONArray b1 = a1.getJSONArray("works");
        JSONObject w1 = b1.getJSONObject(0);
        assertEquals("Eat",
                w1.getString("work"));
        assertFalse(w1.getBoolean("completion"));
        task1.markComplete();
        JSONArray json2 = week.daysToJson();
        JSONObject a = json2.getJSONObject(0);
        JSONArray b = a.getJSONArray("works");
        JSONObject w = b.getJSONObject(0);
        assertTrue(w.getBoolean("completion"));

    }

}
