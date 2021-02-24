package lt.idomus.takas.exceptions.exception;


import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomMessage {
    private String message;


    public String json() {
        return new Gson().toJson(this);
    }
}