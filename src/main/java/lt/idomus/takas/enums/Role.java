package lt.idomus.takas.enums;

import static lt.idomus.takas.constant.AuthoritiesConstant.*;

public enum Role {

    ROLE_USER(USER_ROLES),
    ROLE_ADMIN(ADMIN_ROLES),
    ROLE_SUPER_ADMIN(SUPER_ADMIN_ROLES);

    private String[] authorities;

    Role(String... authority) {
        this.authorities = authority;
    }

    public String[] getAuthorities() {
        return authorities;
    }

}
