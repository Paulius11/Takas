package lt.idomus.takas.enums;

import java.util.List;

import static lt.idomus.takas.constant.AuthoritiesConstant.*;

public enum Role {

    ROLE_USER(USER_ROLES),
    MODERATOR(MODERATOR_ROLES),
    ROLE_ADMIN(ADMIN_ROLES);

    private List<String> authorities;

    Role(List<String> authority) {
        this.authorities = authority;
    }

    public String[] getAuthorities() {
        return authorities.toArray(new String[0]);
    }

}
