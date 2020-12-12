package lt.idomus.takas.exceptions.handler;


import lt.idomus.takas.exceptions.exception.ArticleIdNotFoundException;
import lt.idomus.takas.exceptions.exception.PasswordDontMatchException;
import lt.idomus.takas.exceptions.exception.UserAlreadyExistsException;
import lt.idomus.takas.exceptions.response.ArticleIdNotFoundResponse;
import lt.idomus.takas.exceptions.exception.EmptyFormException;
import lt.idomus.takas.exceptions.response.EmptyFormResponse;
import lt.idomus.takas.exceptions.response.PasswordDontMatchResponse;
import lt.idomus.takas.exceptions.response.UserAlreadyExistsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler
    public ResponseEntity<?> notFoundByIdException(ArticleIdNotFoundException exception, WebRequest request) {

        ArticleIdNotFoundResponse response = new ArticleIdNotFoundResponse(exception.getMessage());

        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<?> emptyFormException(EmptyFormException exception, WebRequest request) {

        EmptyFormResponse response = new EmptyFormResponse(exception.getMessage());

        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<?> passwordDoesntMatchException(PasswordDontMatchException exception, WebRequest request) {

        PasswordDontMatchResponse response = new PasswordDontMatchResponse(exception.getMessage());

        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<?> userAlreadyExistsException(UserAlreadyExistsException exception, WebRequest request) {

        UserAlreadyExistsResponse response = new UserAlreadyExistsResponse(exception.getMessage());

        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

}
