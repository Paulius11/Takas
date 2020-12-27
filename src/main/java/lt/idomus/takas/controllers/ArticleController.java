package lt.idomus.takas.controllers;

import lombok.AllArgsConstructor;
import lt.idomus.takas.doa.ArticleRepository;
import lt.idomus.takas.model.Article;
import lt.idomus.takas.services.ArticleServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api/article")
@AllArgsConstructor
public class ArticleController {

    /**
     * Returns demo data
     */


    private final ArticleServices articleServices;

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping
    public List<Article> articleList() {

        return articleServices.getAllArticles();
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getAllArticleById(@PathVariable Long id) {

        return new ResponseEntity<Article>(articleServices.getArticleById(id), HttpStatus.OK);
    }


    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> createArticle(@RequestBody Article article) {

        return new ResponseEntity<Article>(articleServices.createArticle(article), HttpStatus.CREATED);
    }


    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArticleArticle(@PathVariable Long id) {
        articleServices.deleteArticle(id);
        return new ResponseEntity<String>("Article with ID: '" + "' has been deleted", HttpStatus.OK);
    }
    // TODO:Add update mapping, DTOS, validation

    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> updateArticle(@PathVariable Long id,@RequestBody @Valid Article article) {

        return new ResponseEntity<Article>(articleServices.updateArticle(id,article), HttpStatus.OK);
    }
}
