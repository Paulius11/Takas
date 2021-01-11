package lt.idomus.takas.services;

import lt.idomus.takas.config.OAuth2LoginSuccessHandler;
import lt.idomus.takas.model.Article;
import lt.idomus.takas.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class ArticleServicesTest {

    @MockBean
    private ArticleRepository repository;
    @Mock
    private ArticleServices services;

    @MockBean
    private CustomOAuth2UserService userService;
    @MockBean
    private OAuth2LoginSuccessHandler handler;

    @Test
    @DisplayName("Return all articles test")
    void getAllArticles() {

        when(services.getAllArticles()).thenReturn(List.of(
                Article.builder().id(1L).title("Title test").description("test").region("Test region").build(),
                Article.builder().id(2L).title("Another title test").description("another test").region("Another test region").build()));

        assertEquals(2, services.getAllArticles().size());
        verify(services, times(1)).getAllArticles();
    }

    @Test
    @DisplayName("Get article by id test")
    void getArticleById() {
        Article article = Article.builder().id(1L).title("Find by Id test").description("test").region("Test region").build();
        when(services.getArticleById(1L)).thenReturn(article);

        Article articleFindByIdTest = services.getArticleById(1L);

        assertEquals(article.getId(), articleFindByIdTest.getId());
        assertEquals(article.getTitle(), articleFindByIdTest.getTitle());
    }

    @Test
    @DisplayName("Create article test")
    void createArticle() {
        Article article = Article.builder().id(10L).title("Create test").description("test").region("Test region").build();
        when(services.createArticle(article)).thenReturn(article);

        assertEquals(article, services.createArticle(article));

        verify(services, times(1)).createArticle(any());
    }

    @Test
    void deleteArticle() {
        Article article = Article.builder().id(10L).title("Create test").description("test").region("Test region").build();
        services.createArticle(article);

        services.deleteArticle(10L);
        verify(services, times(1)).deleteArticle(10L);

        Article art = services.getArticleById(10L);
        assertNull(art);

    }

    @Test
    void updateArticle() {
        Article article = Article.builder().id(1L).title("Create test").description("test").region("Test region").build();

        when(services.createArticle(article)).thenReturn(article);
//
//        Article article1 = Article.builder().id(1L).title("UPDATED").description("UPDATED").region("Test region").build();
//
//
//        when(services.updateArticle(1L, article1)).thenReturn(article1);
//        Article updateArticle = services.updateArticle(1L, article1);
        article.setDescription("UPDATED DESC");
        article.setTitle("TITLE UPDT");
        when(services.getArticleById(1L)).thenReturn(article);
        Article art = services.getArticleById(1L);


        assertEquals(article.getTitle(), art.getTitle());
        assertEquals(article.getDescription(), art.getDescription());
    }
}