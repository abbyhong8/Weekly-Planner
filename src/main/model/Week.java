package model;

import org.json.JSONArray;
import org.json.JSONObject;

//Week contains a list of seven days that included tasks in each day
public class Week implements persistence.Writable {
    private Day[] days; // is a integer from [0-6] seven days in a week. 0 is Sunday, 1 is Monday, 2 is Tuesday,
    // 3 is Wednesday, 4 is Thursday, 5 is Friday, 6 is Saturday.

    //EFFECTS: Create a Week that contains 7 days.
    public Week() {
        days = new Day[7];
    }

    //EFFECTS: return the days in the week.
    public Day[] getDays() {
        return days;
    }

    //REQUIRES: The day should be never added to the week.
    //MODIFY: this
    //EFFECTS: add a day to the week with the corresponding day number.
    public void addDay(Day day) {
        days[day.getDayNumInWeek()] = day;
    }

    //EFFECTS: get the length of the days
    public int size() {
        return days.length;
    }


    //REQUIRES: The Day must be added before being removed
    //MODIFY: this
    //EFFECTS: remove a day from the week with the corresponding day number.
    public void removeDay(int dn) {
        days[dn] = null;
    }

//    //EFFECTS: check if the week is empty?
//    public boolean isEmpty() {
//        return this.isEmpty();      /////////
//        //return false;
//    }

    //REQUIRES: There must be at least one day added for the week
    //MODIFY: week
    //EFFECTS: remove all the days from the week and make a new week
    public void removeAll() {
        days = new Day[7];
    }

    //EFFECTS： return the number of all the task
    public int returnAllIncomplete() {
        int a = 0;
        for (int i = 0; i < 7; i++) {
            if (!(isDayAdded(i))) {
                a += (getDays()[i]).checkIncompleteWork();
            }
        }
        return a;
    }

    //EFFECTS: check if the day is added
    public boolean isDayAdded(int i) {
        return getDays()[i] == null;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("days", daysToJson());
        return json;
    }

    public JSONArray daysToJson() {
        JSONArray jsonArray = new JSONArray();

        for (int i = 0; i < days.length; i++) {
            if (days[i] != null) {
                jsonArray.put(days[i].toJson());
            }
        }
        return jsonArray;
    }
}



//    public JSONArray daysToJson() {
//        JSONArray jsonArray = new JSONArray();
//
//        for (Day w : days) {
//            jsonArray.put(w.toJson());
//        }
//
//        return jsonArray;
//    }


