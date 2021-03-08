package lt.idomus.takas.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lt.idomus.takas.model.ImageUpload;
import lt.idomus.takas.services.ImageService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;


@RestController
@AllArgsConstructor
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/image/")
@Slf4j
public class ImageRestController {
    private final ImageService imageService;

    // Returns all the files
    @GetMapping("/files")
    public List<ImageUpload> getListOfFiles() {
        return imageService.getAll();
    }

    // Uploads multiple files
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity uploadFile(@RequestPart(value = "files") MultipartFile[] files) {
        for (int i = 0; i < files.length; i++) {
            log.info(String.format("File name '%s' uploaded successfully.", files[i].getOriginalFilename()));
        }

        Arrays.asList(files).stream().forEach(file -> {
            try {
                imageService.saveFile(file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return ResponseEntity.ok().build();
    }

    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Long fileId) {
        ImageUpload image = imageService.getFileById(fileId).get();
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(image.getImageType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment:filename=\"" + image.getImageName() + "\"")
                .body(new ByteArrayResource(image.getData()));
    }

    @DeleteMapping("files/{fileId}")
    public void deleteImage(@PathVariable Long fileId) {
        imageService.delete(fileId);
        log.info(String.format("File by id '%s' deleted successfully.", fileId));
    }
}
