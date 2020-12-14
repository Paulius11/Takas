package lt.idomus.takas.services;

import lombok.AllArgsConstructor;
import lt.idomus.takas.doa.ArticleRepository;
import lt.idomus.takas.exceptions.exception.ArticleIdNotFoundException;
import lt.idomus.takas.model.Article;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ArticleServices {

    private final ArticleRepository articleRepository;


    public List<Article> getAllArticles() {

        return articleRepository.findAll();
    }

    public Article getArticleById(Long id) {

        Optional<Article> article = articleRepository.findById(id);

        if (article.isEmpty()) {

            throw new ArticleIdNotFoundException("Article was not found with ID: '" + id + "'");
        } else
            return article.get();
    }

    public Article createArticle(Article article) {

        try {

            return articleRepository.save(article);

        } catch (Exception e) {

            throw new ArticleIdNotFoundException("Article exists with ID: '" + article.getId() + "'");
        }


    }

    public void deleteArticle(Long articleId) {

        Article article = getArticleById(articleId);

        if (article == null) {
            throw new ArticleIdNotFoundException("Cannot delete Article with ID: '" + article.getId() + "' Article doesn't exist");
        }
    }

}