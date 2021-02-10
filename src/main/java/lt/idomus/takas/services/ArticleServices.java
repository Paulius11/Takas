package lt.idomus.takas.services;

import lombok.AllArgsConstructor;
import lt.idomus.takas.exceptions.exception.ArticleCreateException;
import lt.idomus.takas.exceptions.exception.ArticleDoesntExistException;
import lt.idomus.takas.model.Article;
import lt.idomus.takas.model.ArticlePost;
import lt.idomus.takas.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Principal;
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

            throw new ArticleCreateException("Article was not found with ID: '" + id + "'");
        } else
            return article.get();
    }

    public Article createArticle(ArticlePost postArticle, Authentication authentication ) {

        try {

            // Creating article when data is fetched from http  POST method
            // To avoid displaying id in swagger
            System.out.println(authentication);

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
                .build();

            return articleRepository.save(newArticle);

        } catch (Exception e) {

            throw new ArticleCreateException("Cant create new article: " + postArticle);
        }
    }

    public Article createSuggestion(ArticlePost article) {
        // Here articles are saved when users are created, they are not displayed in main page
        // without approval of admin
        //TODO
        return null;
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
        articleToBeUpdated.setRegion(article.getRegion());

        return articleRepository.save(articleToBeUpdated);

    }


}