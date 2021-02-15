package lt.idomus.takas.model;

import lombok.*;

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
    private int rating;
    private String difficulty;
    private String region;
    private double length;
    private String image;
    private String username;
    private boolean published;


}

