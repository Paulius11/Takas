package lt.idomus.takas.services;

import lombok.AllArgsConstructor;
import lt.idomus.takas.exceptions.exception.ArticleDoesntExistException;
import lt.idomus.takas.exceptions.exception.ArticleIdNotFoundException;
import lt.idomus.takas.model.Article;
import lt.idomus.takas.repository.ArticleRepository;
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
        try {
            Article articleById = getArticleById(articleId);

            if (articleById == null) {
                throw new ArticleIdNotFoundException("Cannot delete Article with ID: '" + articleId + "' Article doesn't exist");
            }
            articleRepository.delete(articleById);

        } catch (Exception e) {
            throw new ArticleDoesntExistException("Article with such ID doesn't exist!");
        }


    }

    public Article updateArticle(Long id, Article article) {

        Article articleToBeUpdated = getArticleById(id);

        articleToBeUpdated.setTitle(article.getTitle());
        articleToBeUpdated.setDescription(article.getDescription());
        articleToBeUpdated.setFeatured(article.isFeatured());
        articleToBeUpdated.setDifficulty(article.getDifficulty());
        articleToBeUpdated.setImage(article.getImage());
        articleToBeUpdated.setLength(article.getLength());
        articleToBeUpdated.setRating(article.getRating());
        articleToBeUpdated.setRegion(article.getRegion());

        return articleRepository.save(articleToBeUpdated);

    }
}