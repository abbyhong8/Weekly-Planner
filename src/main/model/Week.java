package model;

public class Week {
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


    //REQUIRES: The Day must be added before being removed
    //MODIFY: this
    //EFFECTS: remove a day from the week with the corresponding day number.
    public void removeDay(int dn) {
        days[dn] = null;
    }

//    //EFFECTS: check if the week is empty?
//    public void isEmpty() {
//        this.isEmpty();      /////////
//    }

    //REQUIRES: There must be at least one day added for the week
    //MODIFY: week
    //EFFECTS: remove all the days from the week and make a new week
    public void removeAll() {
        days = new Day[7];
    }
}
