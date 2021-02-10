package lt.idomus.takas.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ArticlePost {
    private String title;
    private String description;
    private boolean featured;
    private int rating;
    private String difficulty;
    private String region;
    private double length;
    private String image;
    private String username;
    boolean published;

}
