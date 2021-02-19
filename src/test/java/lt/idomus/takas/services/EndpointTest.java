package lt.idomus.takas.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EndpointTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Display all articles without authorization")
    void testDisplayArticles() throws Exception {
        mockMvc.perform(get("/api/article/"))
                .andExpect(status().isOk());
    }
    @Test
    @DisplayName("Display all articles with authorization")
    @WithMockUser(username = "paul") // Skipping authorization only testing authorization
    void testDisplayArticlesAuthorized() throws Exception {
        mockMvc.perform(get("/api/article/"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test getting unpublished articles without authorization")
    void testUnpublishedArticles() throws Exception {
        mockMvc.perform(get("/api/unpublished/"))
                .andExpect(status().isUnauthorized());
    }
    @Test
    //WithMockUser when removed returns ok, else 404
    @WithMockUser(username="admin", roles={"USER","ADMIN"})
    void testUnpublishedArticlesWithAuthorization() throws Exception {
        mockMvc.perform(get("/api/unpublished/"))
                .andExpect(status().isUnauthorized());
    }
}
