package lt.idomus.takas.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.HashSet;

@Data
public class ArticleUserPublic {
    private String username;
    private HashSet<Integer> favorites;

    @CreationTimestamp
    private Date created_at;
}
