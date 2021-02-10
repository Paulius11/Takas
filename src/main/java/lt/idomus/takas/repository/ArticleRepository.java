package lt.idomus.takas.repository;

import lt.idomus.takas.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query(value = "SELECT * FROM ARTICLE WHERE PUBLISHED = TRUE", nativeQuery = true)
    List<Article> findPublished();

    @Query(value = "SELECT * FROM ARTICLE WHERE PUBLISHED = FALSE", nativeQuery = true  )
    List<Article> findUnpublished();
}
