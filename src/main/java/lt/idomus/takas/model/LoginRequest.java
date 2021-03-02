package lt.idomus.takas.model;

import lombok.Data;
import lt.idomus.takas.constant.NameConstants;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Data
public class LoginRequest {
    @NotEmpty(message = NameConstants.EMAIL_EMPTY_MESSAGE)
    @Email(message = NameConstants.EMAIL_VIOLATION_MESSAGE)
    private String email;
    @NotEmpty(message = NameConstants.PASSWORD_EMPTY_MESSAGE)
    @Size(min = NameConstants.MIN_PASSWORD_LEN, message = NameConstants.PASSWORD_LEN_VIOLATION_MESSAGE)
    private String password;
}
