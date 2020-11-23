package lt.idomus.takas.exceptions.handler;


import lt.idomus.takas.exceptions.ArticleIdNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
@ControllerAdvice
public class CustomExceptionHandler {


    @ExceptionHandler
    public ResponseEntity<?> notFoundByIdException(ArticleIdNotFoundException exception, WebRequest request) {

        return new ResponseEntity(exception, HttpStatus.BAD_REQUEST);
    }
}
