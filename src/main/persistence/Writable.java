package persistence;


import org.json.JSONObject;

//reference to JsonSerializationDemo.jar.Writable
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
