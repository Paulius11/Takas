package lt.idomus.takas.doa;

import lt.idomus.takas.model.ArticleUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<ArticleUser, Long> {
    ArticleUser findByUsername(String username);

    ArticleUser getById(Long id);
}
