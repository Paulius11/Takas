package lt.idomus.takas.services;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserPassword {
    private String username;
    private String password;

public String json() {
        return new Gson().toJson(this);
    }
}
