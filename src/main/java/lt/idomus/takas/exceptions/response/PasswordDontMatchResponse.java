package lt.idomus.takas.exceptions.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PasswordDontMatchResponse {
    private String response;
}
