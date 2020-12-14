package lt.idomus.takas.exceptions.exception;

import lt.idomus.takas.dto.CreateUserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PasswordDontMatchException extends RuntimeException {
    public PasswordDontMatchException(String message) {
        super(message);
    }
}
