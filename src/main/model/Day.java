package model;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

//Day is a list of Tasks for every day.
public class Day implements Writable {
    private ArrayList<Task> workToDo;  //a day with a list of task
    private int dayNumInWeek;          // the day number

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


    //EFFECTS: check how many works are incomplete
    public int checkIncompleteWork() {
        int c = 0;
        if (workToDo.isEmpty()) {
            return 0;
        } else {
            for (int i = 0; i < workToDo.size(); i++) {
                if (!(getTask(i).isComplete())) {
                    c = c + 1;
                }
            }
            return c;
        }
    }

    //EFFECTS: check if the day is no work
    public boolean isEmpty() {
        boolean empty = workToDo.isEmpty();
        return empty;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("dayNumber", dayNumInWeek);
        json.put("works", worksToJson());
        return json;
    }

    // EFFECT:
    public JSONArray worksToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Task t: workToDo) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }



}

