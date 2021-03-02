package lt.idomus.takas.exceptions.exception;


import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomMessage<T> {
    private String message;
    private boolean status;
    private HashMap<String, T> data = new HashMap<>();

    public CustomMessage(String message) {
        this.message = message;
    }

    public String json() {
        return new Gson().toJson(this);
    }

    public void add(String key, T t){
        this.data.put(key, t);
    }
}