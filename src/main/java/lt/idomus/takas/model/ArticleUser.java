package lt.idomus.takas.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
    private String fullName;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    @CreatedDate
    private Date created_at;

    @LastModifiedDate
    private Date updated_at;

    public ArticleUser(ArticleUser articleUser) {
        this.id = articleUser.getId();
        this.username = articleUser.getUsername();
        this.fullName = articleUser.getFullName();
        this.password = articleUser.getPassword();
        this.roles = articleUser.getRoles();
    }
}
