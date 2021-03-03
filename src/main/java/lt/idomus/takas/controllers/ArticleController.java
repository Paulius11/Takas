package lt.idomus.takas.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lt.idomus.takas.constant.NameConstants;
import lt.idomus.takas.model.Article;
import lt.idomus.takas.model.ArticlePost;
import lt.idomus.takas.services.ArticleServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api/article")
@AllArgsConstructor
public class ArticleController {


    private final ArticleServices articleServices;

    @GetMapping
    @ApiOperation(value = "Get all unpublished articles", notes = "See all articles, accessible for moderators.")
    public List<Article> articleList() {
        return articleServices.getPublishedArticles();
    }

    @PostMapping("/createSuggestion")
    @PreAuthorize("hasAnyAuthority('article:offer')")
    public ResponseEntity<?> createSuggestion(@RequestBody ArticlePost article, Authentication authentication,
                                              @RequestHeader(value = NameConstants.AUTHORIZATION_HEADER) String headerStr) {
        return new ResponseEntity<Article>(articleServices.createSuggestion(article, authentication), HttpStatus.CREATED);
    }

    //    TODO: bet kas gali matyti id, net ir unpublished data
    @GetMapping("/get/{id}")
    @PreAuthorize("hasAnyAuthority('article:read')")
    public ResponseEntity<?> getAllArticleById(@PathVariable Long id) {

        return new ResponseEntity<Article>(articleServices.getArticleById(id), HttpStatus.OK);
    }

    @GetMapping("/private/all")
    @ApiOperation(value = "Get all articles", notes = "See all articles, accessible for moderators.")
    @PreAuthorize("hasAnyAuthority('moderator')")
    public List<Article> articleListAll() {
        return articleServices.getAllArticles();
    }


    @GetMapping("/private/unpublished")
    @ApiOperation(value = "Get unpublished articles", notes = "See unpublished articles, accessible for moderators.")
    @PreAuthorize("hasAnyAuthority('moderator')")
    public List<Article> getUnpublishedArticles() {
        return articleServices.getNotPublishedArticles();
    }


    @PostMapping("/private/create")
    @PreAuthorize("hasAnyAuthority('moderator')")
    public ResponseEntity<?> createArticle(@RequestBody ArticlePost article, Authentication authentication,
                                           @RequestHeader(value = NameConstants.AUTHORIZATION_HEADER) String headerStr) {
        return new ResponseEntity<Article>(articleServices.createArticle(article, authentication), HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('moderator')")
    public ResponseEntity<?> deleteArticleArticle(@PathVariable Long id,
                                                  @RequestHeader(value = NameConstants.AUTHORIZATION_HEADER) String headerStr) {
        articleServices.deleteArticle(id);
        return new ResponseEntity<String>("Article with ID: '" + "' has been deleted", HttpStatus.OK);
    }
    // TODO:Add update mapping, DTOS, validation

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('moderator')")
    public ResponseEntity<?> updateArticle(@PathVariable Long id, @RequestBody @Valid Article article) {
        return new ResponseEntity<Article>(articleServices.updateArticle(id, article), HttpStatus.OK);
    }
}
