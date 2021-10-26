package persistence;

import model.*;
import org.json.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    //EFFECTS: reads tasks from file and return it;
    //throws IOException if an error occurs reading data from file
    public Week read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
       // return parseDays(jsonObject);
        return parseWeek(jsonObject);
    }

    //EFFECTS reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

//    //EFFECTS: parses tasks from JSON object and returns it
//    private Day parseDays(JSONObject jsonObject) {
//        Integer days = jsonObject.getInt("days");
//        Day d = new Day(days);
//        addTasks(d, jsonObject);
//        return d;
//    }

    //EFFECTS: parses tasks from JSON object and returns it
    private Week parseWeek(JSONObject jsonObject) {
       // Integer week = jsonObject.getInt("week");
        Week d = new Week();
        addDays(d, jsonObject);

        return d;
    }

    // MODIFIES: w
    // EFFECTS: parses days from JSON object and adds it to week
    private void addDays(Week w, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("days");
        for (Object json : jsonArray) {
            JSONObject nextDay = (JSONObject) json;
            addDay(w, nextDay);
        }
    }

    // MODIFIES: w
    // EFFECTS: parses day from JSON object and adds it to week
    private void addDay(Week w, JSONObject jsonObject) {
        int dayNumber = jsonObject.getInt("dayNumber");
        Day day = new Day(dayNumber);
        w.addDay(day);
        addTasks(day, jsonObject);
    }



    //MODIFIES: tt
    //EFFECTS: parses tasks from JSON object and adds them to day
    private void addTasks(Day d, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("works");
        for (Object json : jsonArray) {
            JSONObject nextTask = (JSONObject) json;
            addTask(d, nextTask);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses task from JSON object and adds it to day
    private void addTask(Day d, JSONObject jsonObject) {
        String name = jsonObject.getString("work");
        Task task = new Task(name);
        d.addTask(task);
    }
}
