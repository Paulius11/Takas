package lt.idomus.takas.model;

import lombok.*;
import lt.idomus.takas.enums.Difficulty;
import lt.idomus.takas.enums.Region;

import javax.persistence.*;

@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private boolean featured;
    private double rating;
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;
    @Enumerated(EnumType.STRING)
    private Region region;
    private double length;
    private String image;
    private String username;
    private boolean published;


}

