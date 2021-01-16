package lt.idomus.takas.repository;

import lt.idomus.takas.model.ArticleUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<ArticleUser, Long> {
    Optional<ArticleUser> findByUsername(String username);
    Optional<ArticleUser> findByEmail(String email);

    ArticleUser getById(Long id);
}
