package lt.idomus.takas.constant;

public class NameConstants {
    public static final String LOGIN_UNAUTHENTICATED = "Username or Password is invalid!";
    public static final String LOGIN_AUTHENTICATED = "Authentication successful!";
    public static final String REGISTRATION_SUCCESSFUL = "Registration successful";

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final int MIN_PASSWORD_LEN = 6;

    public static final String PASSWORD_LEN_VIOLATION_MESSAGE = "Min password length: " + MIN_PASSWORD_LEN + " chars";
    public static final String PASSWORD_EMPTY_MESSAGE = "Password should not be empty";
    public static final String USERNAME_EMPTY_VIOLATION_MESSAGE = "Username should not be empty";
    public static final String EMAIL_VIOLATION_MESSAGE = "Email must be a valid email address";
    public static final String EMAIL_EMPTY_MESSAGE = "Email should not be empty";
}
