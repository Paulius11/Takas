package lt.idomus.takas.controller;

import lt.idomus.takas.doa.ArticleRepository;
import lt.idomus.takas.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping()
public class ArticleController {

    /**
     * Returns demo data
     */

    @Autowired
    private ArticleRepository articleRepository;

    // #TODO unique model for Article
    // #TODO split code to userservice/
    @GetMapping()
    public List<Article> getDemoData() {
        List<Article> listOfArticles = new ArrayList<>();
        Iterable<Article> findAllProjects = articleRepository.findAll();
        findAllProjects.forEach(articleFromSql -> {
                    Article article = new Article();
                    article.setId(articleFromSql.getId());
                    article.setTitle(articleFromSql.getTitle());
                    article.setDescription(articleFromSql.getDescription());
                    article.setImage(articleFromSql.getImage());
                    article.setDifficulty(articleFromSql.getDifficulty());
                    article.setRegion(articleFromSql.getRegion());
                    article.setLength(articleFromSql.getLength());
                    article.setRating(articleFromSql.getRating());
                    listOfArticles.add(article);
                }
        );

        return listOfArticles;
    }
}
