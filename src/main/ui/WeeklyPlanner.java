package ui;

import model.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import persistence.JsonReader;
import persistence.JsonWriter;

// runner application for weekly planner
public class WeeklyPlanner {
    private static final String JSON_STORE = "./data/week.json";
    private Week ww;
    private Scanner scan;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;





    //EFFECTS: run the weekly planner.
    public WeeklyPlanner() {
        display();

        runPlanner();

    }

    //MODIFY: this
    //EFFECTS: process the user input
    public void runPlanner() {
        String choice = null;





        scan = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        choice = scan.nextLine();
        Day day1 = new Day(0);
        Day day2 = new Day(1);
        Day day3 = new Day(2);
        Day day4 = new Day(3);
        Day day5 = new Day(4);
        Day day6 = new Day(5);
        Day day7 = new Day(6);
        ww.addDay(day1);
        ww.addDay(day2);
        ww.addDay(day3);
        ww.addDay(day4);
        ww.addDay(day5);
        ww.addDay(day6);
        ww.addDay(day7);
       // display();
        processChoice(choice);





    }

    //EFFECTS: display the menu
    public void display() {
        System.out.println("Choose your operation: (choose 1 to start a new week!)");
        System.out.println("1. add a event");
        System.out.println("2. Visit one day (please enter an integer between 0-6)");
        System.out.println("3. Start a new week");
        System.out.println("4. Show the number of incomplete tasks");
        System.out.println("5. Save the week");
        System.out.println("6. load a week");
        System.out.println("0. quit");
    }

    //MODIFY: this
    //EFFECTS: process the user choice
    public void processChoice(String choice) {
        if (choice.equals("1")) {
            doAddEvent();
        } else if (choice.equals("2")) {
            doVisitDay();
        } else if (choice.equals("3")) {
            doClearWeek();
        } else if (choice.equals("4")) {
            doCheckAllTasks();
        } else if (choice.equals("5")) {
            doSaveAll();
            backMenu();
        } else if (choice.equals("6")) {
            doShowAll();
            backMenu();
        } else if (choice.equals("0")) {
            System.exit(0);
        } else {
            System.out.println("invalid choice...");
            backMenu();
        }
    }

    //REQUIRES: the day number must between 0 and 6
    //MODIFIES: this
    //EFFECTS: create a new task
    public void doAddEvent() {
        System.out.println("Which day in a week is the event on? please enter a number between 0-6");
        String dayNum = scan.nextLine();
        int realDayNum = Integer.parseInt(dayNum);
        Day day = ww.getDays()[realDayNum];
        System.out.println("What's the name of the event?");
        String eventName = scan.nextLine();
        Task task = new Task(eventName);
        day.addTask(task);
       // ww.addDay(day);
        backMenu();
    }

    //REQUIRES: the input number of the day must be added to the planner
    //MODIFIES: this
    //EFFECTS: visit all events day
    public void doVisitDay() {
        System.out.println("Which day in a week is the event on? please enter an integer between 0-6");
        String choice = scan.nextLine();

        int realDayN = Integer.parseInt(choice);
        Day thisDay = ww.getDays()[realDayN];
        for (int i = 0; i < thisDay.checkNumber(); i++) {
            System.out.println((thisDay.getTask(i)).getWork());
        }
        System.out.println("1. choose an event (1 for the first event, 2 for the second event...)");
        System.out.println("2. back to the menu");
        String choice2 = scan.nextLine();
        if (choice2.equals("1")) {
            checkTask(thisDay);
        } else if (choice2.equals("2")) {
            backMenu();
        }
    }

    //EFFECTS: back to the menu
    public void backMenu() {
        display();
        String choice = scan.nextLine();

        processChoice(choice);
    }

    //MODIFY: this
    //EFFECTS: check the task in this day.
    public void checkTask(Day thisDay) {
        System.out.println("Enter the task you want to check");
        String choice = scan.nextLine();
        int taskN = Integer.parseInt(choice);
        Task taskChoose;
        taskChoose = thisDay.getTask(taskN - 1);
        System.out.println("1. Check the status of the task");
        System.out.println("2. Mark as completed ");
        System.out.println("3. Remove the task");
        System.out.println("4. Back to the menu");
        String choiceTask = scan.nextLine();
        if (choiceTask.equals("1")) {
            System.out.println(taskChoose.isComplete());
            backMenu();
        } else if (choiceTask.equals("2")) {
            taskChoose.markComplete();
            backMenu();
        } else if (choiceTask.equals("3")) {
            thisDay.removeTask(taskChoose.getWork());
            backMenu();
        } else {
            System.out.println("invalid choice");
            backMenu();
        }

    }     //////

    //MODIFY: this
    //EFFECT: clear the entire week
    public void doClearWeek() {
        ww.removeAll();
        display();
        runPlanner();
    }

    //EFFECT: check all the incomplete tasks
    public void doCheckAllTasks() {
        System.out.println(ww.returnAllIncomplete());
        backMenu();
    }

    //EFFECTS: save all the tasks, days in the week
    public void doSaveAll() {
        try {
            jsonWriter.open();
            jsonWriter.write(ww);
            jsonWriter.close();
            System.out.println("Saved the week to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads the week from file
    private void doShowAll() {
        try {
            ww = jsonReader.read();
            System.out.println("Loaded the week from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}





