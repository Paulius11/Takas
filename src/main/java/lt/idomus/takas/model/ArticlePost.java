package lt.idomus.takas.model;

import lombok.Builder;
import lombok.Data;
import lt.idomus.takas.enums.Difficulty;
import lt.idomus.takas.enums.Region;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Builder
@Data
public class ArticlePost {
    private String title;
    private String description;
    private boolean featured;
    private int rating;
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;
    @Enumerated(EnumType.STRING)
    private Region region;
    private double length;
    private String image;
    private String username;
    boolean published;

}
