package lt.idomus.takas.services;

import lombok.AllArgsConstructor;
import lt.idomus.takas.exceptions.exception.ArticleCreateException;
import lt.idomus.takas.exceptions.exception.ArticleDoesntExistException;
import lt.idomus.takas.model.Article;
import lt.idomus.takas.model.ArticlePost;
import lt.idomus.takas.repository.ArticleRepository;
import org.springframework.security.core.Authentication;
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

    public List<Article> getPublishedArticles() {
        return articleRepository.findPublished();
    }

    public List<Article> getNotPublishedArticles() {
        return articleRepository.findUnpublished();
    }

    public Article getArticleById(Long id) {

        Optional<Article> article = articleRepository.findById(id);

        if (article.isEmpty()) {

            throw new ArticleCreateException("Article was not found with ID: '" + id + "'");
        } else
            return article.get();
    }

    /**
     * This method creates new articles
     *
     * @param postArticle    post method data from frontend
     * @param authentication authentication data passed from controller
     * @return json data as response
     */
    public Article createArticle(ArticlePost postArticle, Authentication authentication) {

        try {
            // Creating article when data is fetched from http  POST method
            // To avoid displaying id in swagger
            Article newArticle =
                    Article.builder()
                            .title(postArticle.getTitle())
                            .description(postArticle.getDescription())
                            .featured(postArticle.isFeatured())
                            .rating(postArticle.getRating())
                            .difficulty(postArticle.getDifficulty())
                            .region(postArticle.getRegion())
                            .length(postArticle.getLength())
                            .image(postArticle.getImage())
                            .username(authentication.getName())
                            .published(true)
                            .build();

            return articleRepository.save(newArticle);

        } catch (Exception e) {

            throw new ArticleCreateException("Cant create new article: " + postArticle);
        }
    }

    /**
     * Create draft article.
     * this article won't be displayed  before approving by user with required rights
     *
     * @param postArticle    post method data from frontend
     * @param authentication authentication data passed from controller
     * @return json data as response
     */
    public Article createSuggestion(ArticlePost postArticle, Authentication authentication) {
        try {
            Article newArticle =
                    Article.builder()
                            .title(postArticle.getTitle())
                            .description(postArticle.getDescription())
                            .featured(postArticle.isFeatured())
                            .rating(postArticle.getRating())
                            .difficulty(postArticle.getDifficulty())
                            .region(postArticle.getRegion())
                            .length(postArticle.getLength())
                            .image(postArticle.getImage())
                            .username(authentication.getName())
                            .published(false)
                            .build();
            return articleRepository.save(newArticle);
        } catch (Exception e) {
            throw new ArticleCreateException("Cant create new article: " + postArticle);
        }
    }


    public void deleteArticle(Long articleId) {
        try {
            Article articleById = getArticleById(articleId);

            if (articleById == null) {
                throw new ArticleCreateException("Cannot delete Article with ID: '" + articleId + "' Article doesn't exist");
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
        articleToBeUpdated.setPublished(article.isPublished());

        return articleRepository.save(articleToBeUpdated);

    }


}