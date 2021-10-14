package model;

import java.util.ArrayList;
import java.util.List;

public class Day {
    private ArrayList<Task> workToDo;
    private int dayNumInWeek;

    //EFFECTS: set is empty
    public Day(int dn) {
        workToDo = new ArrayList<>();
        this.dayNumInWeek = dn;
    }

    public int getDayNumInWeek() {
        return dayNumInWeek;
    }

    //MODIFIES: this
    //EFFECTS: add the work you need to complete
    public void addTask(Task work) {
        workToDo.add(work);
    }

    //REQURIES: The day must include at least one task
    //EFFECTS： get the task
    public Task getTask(int n) {
        return workToDo.get(n);
    }


    //REQUIRES:the list of task should not be empty, the entered work must be in the list
    //MODIFIES: this
    //EFFECTS: remove the work
    public void removeTask(String s) {
        workToDo.removeIf(t -> t.getWork().equals(s));
    }


    //EFFECTS: check how many works on the list
    public int checkNumber() {
        return workToDo.size();
    }





}
