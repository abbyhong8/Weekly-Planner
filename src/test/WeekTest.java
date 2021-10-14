
import static org.junit.jupiter.api.Assertions.*;


import static org.junit.jupiter.api.Assertions.assertEquals;


import model.Week;
import model.Day;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class WeekTest {
    Week week;

    @BeforeEach
    void setUp() {
        week = new Week();
    }

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
        week.isEmpty();
        Day day1 = new Day(6);
        Day day2 = new Day(5);
        Day day3 = new Day(2);
        week.addDay(day1);
        week.addDay(day2);
        week.addDay(day3);
        assertNotNull(week);
        week.removeAll();
        week.isEmpty();

    }
}
