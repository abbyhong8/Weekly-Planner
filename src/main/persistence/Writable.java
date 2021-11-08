package persistence;


import org.json.JSONObject;

//reference to JsonSerializationDemo.jar.Writable
//A writable class of persistence package;
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
