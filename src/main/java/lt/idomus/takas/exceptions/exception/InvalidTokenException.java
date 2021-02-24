package lt.idomus.takas.exceptions.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class InvalidTokenException extends Exception {
    public InvalidTokenException(String message, Throwable cause) {
        super(message, cause);
    }
    public InvalidTokenException(String message) {
        super(message);
    }
}
