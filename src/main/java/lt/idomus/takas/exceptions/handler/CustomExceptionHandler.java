package lt.idomus.takas.exceptions.handler;


import lt.idomus.takas.exceptions.exception.*;
import lt.idomus.takas.exceptions.response.*;
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
    public ResponseEntity<?> notFoundByIdException(ArticleCreateException exception, WebRequest request) {

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
    public ResponseEntity<?> userAlreadyExistsException(UserCreationError exception, WebRequest request) {

        UserAlreadyExistsResponse response = new UserAlreadyExistsResponse(exception.getMessage());

        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<?> articleDoesntExistException(ArticleDoesntExistException exception, WebRequest request) {

        ArticleDoesntExistResponse response = new ArticleDoesntExistResponse(exception.getMessage());

        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

}
