package lt.idomus.takas.constant;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AuthoritiesConstant {


public static final List<String> USER_ROLES = Arrays.asList("authenticated", "user", "article:read", "article:offer");
public static final List<String> MODERATOR_ROLES = Stream.concat(USER_ROLES.stream(),
        Stream.of( "moderator", "article:create", "article:update", "article:approve"))
        .collect(Collectors.toUnmodifiableList());
public static final List<String> ADMIN_ROLES = Stream.concat(MODERATOR_ROLES.stream(),
        Stream.of("admin", "article:delete"))
        .collect(Collectors.toUnmodifiableList());

    public static void main ( String[] args )
    {
        System.out.println( "USER_ROLES = " + USER_ROLES );
        System.out.println( "MODERATOR_ROLES = " + MODERATOR_ROLES );
        System.out.println( "ADMIN_ROLES = " + ADMIN_ROLES );
    }
}