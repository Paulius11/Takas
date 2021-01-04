package lt.idomus.takas.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lt.idomus.takas.enums.Role;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ArticleUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @Column(unique = true)
    private String username;
    private String email;
    private String password;


    private Role roles;
    private String[] authority;


    @CreatedDate
    private Date created_at;

    @LastModifiedDate
    private Date updated_at;

    public ArticleUser(ArticleUser articleUser) {
        this.id = articleUser.getId();
        this.username = articleUser.getUsername();
        this.email = articleUser.getEmail();
        this.password = articleUser.getPassword();
        this.roles = articleUser.getRoles();
        this.authority = articleUser.getRoles().getAuthorities();
    }
}
