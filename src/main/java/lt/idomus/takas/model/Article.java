package lt.idomus.takas.model;

import javax.persistence.*;

@Entity
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

    public Article() {
    }

    public Article(String title, String description, boolean featured, int rating, String difficulty, String region, double length, String image) {
        super();
        this.title = title;
        this.description = description;
        this.featured = featured;
        this.rating = rating;
        this.difficulty = difficulty;
        this.region = region;
        this.length = length;
        this.image = image;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isFeatured() {
        return featured;
    }

    public int getRating() {
        return rating;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getRegion() {
        return region;
    }

    public double getLength() {
        return length;
    }

    public String getImage() {
        return image;
    }



    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }

}

