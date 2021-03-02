package lt.idomus.takas.model;

import lombok.Builder;
import lombok.Data;
import lt.idomus.takas.enums.Role;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.HashSet;

@Builder
@Data
public class ArticleUserDetailsPost {
    private String username;
    private String email;
    private String password;
    private HashSet<Integer> favorites;
    @Enumerated(EnumType.STRING)
    private Role role;
}
