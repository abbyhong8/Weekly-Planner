package model;

public class Task {
    private String work;  // the work to do
    private boolean completion; //to mark whether the work is complete or not

    // REQUIRES: Task has a non-zero length
    // EFFECTS: create a work to do

    public Task(String taskName) {
        this.work = taskName;
        this.completion = false;
    }

    // REQURIES: the work should be a non-zero length
    // EFFECTS: return the work
    public String getWork() {
        return this.work;
    }

    //MODIFY: this
    //EFFECTS: to mark the work is done
    public boolean markComplete() {
        this.completion = true;
        return true;
    }

    //EFFECTS: to check if the work is complete
    public boolean isComplete() {
        return this.completion;
    }




}



