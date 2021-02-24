package lt.idomus.takas.model;

import lombok.Builder;
import lombok.Data;

import java.util.HashSet;

@Builder
@Data
public class ArticleUserDetailsPost {
    private HashSet<Integer> favorites;

}
