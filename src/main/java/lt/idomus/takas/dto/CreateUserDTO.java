package lt.idomus.takas.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CreateUserDTO {

    private String username;

    private String password;

    private String fullname;

    private String confirmPassword;

}
