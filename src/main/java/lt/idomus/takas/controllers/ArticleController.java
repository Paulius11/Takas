package lt.idomus.takas.controllers;

import lombok.AllArgsConstructor;
import lt.idomus.takas.model.Article;
import lt.idomus.takas.model.ArticlePost;
import lt.idomus.takas.services.ArticleServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.PrimitiveIterator;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api/article")
@AllArgsConstructor
public class ArticleController {


    private final ArticleServices articleServices;



    @GetMapping
    public List<Article> articleList() {
        return articleServices.getAllArticles();
    }


    @PreAuthorize("hasAnyAuthority('article:read')")
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getAllArticleById(@PathVariable Long id) {

        return new ResponseEntity<Article>(articleServices.getArticleById(id), HttpStatus.OK);
    }


    @PreAuthorize("hasAnyAuthority('article:create')")
    @PostMapping("/create" )
    public ResponseEntity<?> createArticle(@RequestBody ArticlePost article, Authentication authentication) {
        return new ResponseEntity<Article>(articleServices.createArticle(article, authentication), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('article:offer')")
    @PostMapping("/createSuggestion")
    public ResponseEntity<?> createSuggestion(@RequestBody ArticlePost article) {
        return new ResponseEntity<Article>(articleServices.createSuggestion(article), HttpStatus.CREATED);
    }


    @PreAuthorize("hasAnyAuthority('article:delete')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArticleArticle(@PathVariable Long id) {
        articleServices.deleteArticle(id);
        return new ResponseEntity<String>("Article with ID: '" + "' has been deleted", HttpStatus.OK);
    }
    // TODO:Add update mapping, DTOS, validation

    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('article:update')")
    public ResponseEntity<?> updateArticle(@PathVariable Long id, @RequestBody @Valid Article article) {

        return new ResponseEntity<Article>(articleServices.updateArticle(id, article), HttpStatus.OK);
    }
}
