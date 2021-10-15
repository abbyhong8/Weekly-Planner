package ui;

import model.*;

import java.util.Scanner;

public class WeeklyPlanner {
    private Week ww;
    private Scanner scan;
    boolean keepGoing = true;




    //EFFECTS: run the weekly planner.
    public WeeklyPlanner() {
        display();

        runPlanner();

    }

    //MODIFY: this
    //EFFECTS: process the user input
    public void runPlanner() {
        String choice = null;
        ww = new Week();
        scan = new Scanner(System.in);
        choice = scan.nextLine();

       // display();
        processChoice(choice);





    }

    //EFFECTS: display the menu
    public void display() {
        System.out.println("Choose your operation:");
        System.out.println("1. add a event");
        System.out.println("2. Visit one day");
        System.out.println("3. Start a new week");
        System.out.println("4. Check all incomplete tasks");
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
        } else if (choice.equals("0")) {
            System.exit(0);
        } else {
            System.out.println("invalid choice...");
        }
    }

    //MODIFIES: this
    //EFFECTS: create a new task
    public void doAddEvent() {
        System.out.println("Which day in a week is the event on? from(0-6)");
        String dayNum = scan.nextLine();
        System.out.println("What's the name of the event?");
        String eventName = scan.nextLine();
        int realDayNum = Integer.parseInt(dayNum);
        Task task = new Task(eventName);
        Day day = new Day(realDayNum);
        ww.addDay(day);
        day.addTask(task);
       // ww.addDay(day);
        backMenu();
    }

    //REQUIRES: the day must be added to the planner
    //MODIFIES: this
    //EFFECTS: visit all events day
    public void doVisitDay() {
        System.out.println("Which day in a week is the event on?");
        String choice = scan.nextLine();

        int realDayN = Integer.parseInt(choice);
        Day thisDay = ww.getDays()[realDayN];
        for (int i = 0; i < thisDay.checkNumber(); i++) {
            System.out.println((thisDay.getTask(i)).getWork());
        }
        System.out.println("1. choose an event");
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
        System.out.println("2. Mark as completed");
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
        backMenu();
    }

    //EFFECT: check all the incomplete tasks
    public int doCheckAllTasks() {
        return ww.returnAllIncomplete();
    }
}





