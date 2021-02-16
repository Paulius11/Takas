package lt.idomus.takas.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.Optional;

@AllArgsConstructor
@Data
@ToString
public class JwtLoginSuccessResponse {

    private String jwt;
    private String message;
    private Optional<ArticleUser> user;
}
