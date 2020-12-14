package lt.idomus.takas.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor
@Data
@ToString
public class JwtLoginSuccessResponse {

    private boolean success;
    private String token;
}
