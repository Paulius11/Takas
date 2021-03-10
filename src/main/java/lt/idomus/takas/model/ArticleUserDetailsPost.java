package lt.idomus.takas.model;

import lombok.Builder;
import lombok.Data;
import lt.idomus.takas.constant.NameConstants;
import lt.idomus.takas.enums.Role;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import java.util.HashSet;

@Builder
@Data
public class ArticleUserDetailsPost {

    private String username;
    @Email(message = NameConstants.EMAIL_VIOLATION_MESSAGE)
    private String email;
    private String password;

    private HashSet<Integer> favorites;
    @Enumerated(EnumType.STRING)
    private Role roles;
}
