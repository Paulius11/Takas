package lt.idomus.takas.services;

import lombok.AllArgsConstructor;
import lt.idomus.takas.model.ImageUpload;
import lt.idomus.takas.repository.ImageRepository;
import org.apache.http.entity.ContentType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    public void saveFile(MultipartFile file) throws Exception {
        isEmpty(file);
        isImageType(file);
        String imageName = file.getOriginalFilename();
        try {
            ImageUpload image = new ImageUpload(imageName, file.getContentType(), file.getBytes());
            imageRepository.save(image);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Optional<ImageUpload> getFileById(Long imageId) {
        return imageRepository.findById(imageId);
    }

    public List<ImageUpload> getAll() {
        return imageRepository.findAll();
    }

    private void isEmpty(MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new IllegalAccessException("Cannot pass empty file!");
        }
    }

    private void isImageType(MultipartFile file) {
        if (!Arrays.asList(ContentType.IMAGE_JPEG.getMimeType(),
                ContentType.IMAGE_PNG.getMimeType(),
                ContentType.IMAGE_GIF.getMimeType()).contains(file.getContentType())) {
            throw new IllegalStateException("File must be an image type [" + file.getContentType() + "]");
        }
    }

    public void delete(Long imageId) {
        imageRepository.deleteById(imageId);
    }
}
