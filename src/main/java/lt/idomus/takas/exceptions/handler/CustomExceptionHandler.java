package lt.idomus.takas.exceptions.handler;


import lt.idomus.takas.exceptions.ArticleIdNotFoundException;
import lt.idomus.takas.exceptions.ArticleIdNotFoundResponse;
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
}
