package lt.idomus.takas.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "images")
public class ImageUpload {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageName;

    private String imageType;

    // TODO: Add article id

    @Lob
    @JsonIgnore
    private byte[] data;

    public ImageUpload(String imageName, String contentType, byte[] bytes) {
        this.imageName = imageName;
        this.imageType = contentType;
        this.data = bytes;
    }
}