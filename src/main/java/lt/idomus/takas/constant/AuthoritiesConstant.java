package lt.idomus.takas.constant;

public class AuthoritiesConstant {

    public static final String[] USER_ROLES =  {"authenticated", "user", "article:read", "article:offer"};
    public static final String[] MODERATOR =   {"authenticated", "user", "moderator", "article:read", "article:create", "article:update", "article:approve", "article:offer" };
    public static final String[] ADMIN_ROLES = {"authenticated", "user", "moderator","admin", "article:read", "article:create", "article:update", "article:delete", "article:offer"};

}
