package lt.idomus.takas.doa;

import lt.idomus.takas.model.ArticleUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<ArticleUser, Long> {
    Optional<ArticleUser> findByUsername(String username);

    ArticleUser getById(Long id);
}
