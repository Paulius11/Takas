package lt.idomus.takas.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lt.idomus.takas.enums.Role;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class ArticleUser {

    public ArticleUser(ArticleUser articleUser) {
        this.id = articleUser.getId();
        this.username = articleUser.getUsername();
        this.email = articleUser.getEmail();
        this.password = articleUser.getPassword();
        this.roles = articleUser.getRoles();
        this.authority = articleUser.getRoles().getAuthorities();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String email;
    private String password;
    private HashSet<Integer> favorites;

    @CreationTimestamp
    private Date created_at;

    @UpdateTimestamp
    private Date updated_at;


    // Oauth2 data
    @Column(name = "OAuth2")
    private boolean OAuth;
    @Column(name = "OAuth2_ID", unique = true)
    private String OAuthID;

    @Enumerated(EnumType.STRING)
    private Role roles;
    private String[] authority;


    public void addToFavorites(Integer articleId) {
        favorites.add(articleId);
    }

    public void removeFromFavorites(Integer articleId) {
        favorites.remove(articleId);
    }
}
