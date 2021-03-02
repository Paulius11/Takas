package lt.idomus.takas.constant;

public class SecurityConstant {

    public static final String HOME_PATH = "/";
    public static final String USER_PATH = "/api/user/**";
    public static final String GET_ALL_ARTICLE_LIST = "/api/article/**";
    public static final String SECRET = "E13023670F1DD064E7A5280916B36F1CFD34F2D3386F4944635F536720D4995E";
    public static final Integer EXPIRATION_IN_DAYS = 3; //
    public static final Long EXPIRATION_IN_MILLISECONDS = 3600000L * 24 * EXPIRATION_IN_DAYS; // 3600000ms = 1h

    // Enable Swagger
    public static final String[] SWAGGER_PATH = {"/v2/api-docs",
            "/configuration/ui",
            "/swagger-resources/**",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**"};
    public static final String H2_PATH = "/h2-console/**"; // this is required for h2 to work
}
