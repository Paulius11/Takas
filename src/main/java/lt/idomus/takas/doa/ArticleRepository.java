package lt.idomus.takas.doa;

import lt.idomus.takas.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository  extends JpaRepository<Article, Long> {
}
