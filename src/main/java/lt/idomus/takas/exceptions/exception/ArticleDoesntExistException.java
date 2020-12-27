package lt.idomus.takas.exceptions.exception;

public class ArticleDoesntExistException extends RuntimeException {

    public ArticleDoesntExistException(String message) {
        super(message);
    }
}
