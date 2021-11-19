package ui;

import model.*;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Week week = new Week();
        Day day1 = new Day(0);
        Day day2 = new Day(1);
        Day day3 = new Day(2);
        Day day4 = new Day(3);
        Day day5 = new Day(4);
        Day day6 = new Day(5);
        Day day7 = new Day(6);
        week.addDay(day1);
        week.addDay(day2);
        week.addDay(day3);
        week.addDay(day4);
        week.addDay(day5);
        week.addDay(day6);
        week.addDay(day7);
        //new WeeklyPlanner();

        new WeeklyPlannerMainGui(week);
    }
}
