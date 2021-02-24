package lt.idomus.takas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateUserDTO {

    public static final int MIN_PASSWORD_LEN = 6;
    public static final String PASSWORD_LEN_VIOLATION_MESSAGE = "Min password length: " + MIN_PASSWORD_LEN + " chars";
    public static final String USERNAME_EMPTY_VIOLATION_MESSAGE = "Username should not be empty";
    public static final String EMAIL_VIOLATION_MESSAGE = "Email must be a valid email address";

    @NotEmpty(message = USERNAME_EMPTY_VIOLATION_MESSAGE)
    private String username;
    @NotEmpty(message = "Email should not be empty")
    @Email(message = EMAIL_VIOLATION_MESSAGE)
    private String email;
    @NotEmpty(message = "Password should not be empty")
    @Size(min = MIN_PASSWORD_LEN, message = PASSWORD_LEN_VIOLATION_MESSAGE)
    private String password;
    @NotEmpty(message = "Password should not be empty")
    @Size(min = MIN_PASSWORD_LEN, message = PASSWORD_LEN_VIOLATION_MESSAGE)
    private String confirmPassword;


}
