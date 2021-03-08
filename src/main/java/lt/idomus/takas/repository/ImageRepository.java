package lt.idomus.takas.repository;

import lt.idomus.takas.model.ImageUpload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<ImageUpload, Long> {
}
