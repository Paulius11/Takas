package lt.idomus.takas.exceptions.exception;


import lombok.Data;

@Data
public class InvalidLoginResponse {

    private String message;

    public InvalidLoginResponse() {
        this.message = "Invalid username or password";
    }


}