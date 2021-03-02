package lt.idomus.takas.services;

import com.google.gson.Gson;
import lt.idomus.takas.constant.NameConstants;
import lt.idomus.takas.dto.CreateUserDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// Example testing json response and posting raw json data
// https://github.com/paulodLima/cadastro-spring/blob/be0226b05878354ff2b863b871404aac4d1e2f36/src/test/java/com/person/api/controller/PersonControllerTest.java

@SpringBootTest
@AutoConfigureMockMvc
public class EndpointTest {
    public static final String LOGIN_URL = "/api/user/login";
    @Autowired
    private MockMvc mockMvc;


//    @Before("")
//    public void setup(){
//        create users
//    }


    // @WithMockUser(username = "paul") - automatically creates new user.
    // @WithUserDetails("admin") - uses created username.

    @Test
    @DisplayName("Display all articles without authorization")
    void testDisplayArticles() throws Exception {
        mockMvc.perform(get("/api/article/"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Display all articles with authorization")
    @WithMockUser(username = "paul")
        // Skipping authentication only testing authorization
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
//
//    @Test
//    //TODO: WithMockUser when removed returns ok, else 404
//    @WithMockUser(username = "paul")
//    void testUnpublishedArticlesWithAuthorization() throws Exception {
//        mockMvc.perform(get("/api/unpublished/"))
//                .andExpect(status().isUnauthorized());
//    }

    /*
             /api/user/login
     */

    @Test
    @DisplayName("Login without with bad data/no data")
    void testLoginBadData() throws Exception {
        mockMvc.perform(post("/api/user/login"))
                .andDo(print())
                .andExpect(status().isBadRequest());

    }

    @Test
    @DisplayName("Login with correct user data")
    void testLoginCorrectUserData() throws Exception {
        mockMvc.perform(post("/api/user/login")
                .content(new UserPassword("user", "user1234").json())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.jwt").exists())
                .andExpect(jsonPath("$.user").exists())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.message", is(NameConstants.LOGIN_AUTHENTICATED)))
                .andExpect(status().isOk());
    }


    @Test
    @DisplayName("Login with incorrect user data")
    void testLoginIncorrectUserData() throws Exception {


        mockMvc.perform(post(LOGIN_URL)
                .content(new UserPassword("not_existing_user", "").json())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.message.size()", is(2)))
                .andExpect(jsonPath("$.jwt").doesNotExist())
                .andExpect(jsonPath("$.user").doesNotExist())
                .andExpect(status().isBadRequest());

    }


        /*
             /api/user/register
        */

    @Test
    @DisplayName("Test successful user registration")
    void testUserRegistration() throws Exception {

        var user = new CreateUserDTO("username", "user@user.lt", "123456", "123456");
        String jsonUserDetails = new Gson().toJson(user);

        mockMvc.perform
                (
                        post("/api/user/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(jsonUserDetails)
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.message").value(NameConstants.REGISTRATION_SUCCESSFUL))
        ;

    }


    @Test
    @DisplayName("Test user registration with user @Valid violations")
    void testUnsuccessfulRegistration() throws Exception {
        var invalidUser = "";
        var invalidEmail = "user.lt";
        var invalidPassword = "123";
        var user = new CreateUserDTO(invalidUser, invalidEmail,  invalidPassword, invalidPassword);
        String jsonUserDetails = new Gson().toJson(user);

        mockMvc.perform
                (
                        post("/api/user/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(jsonUserDetails)
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.timestamp").exists())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.message.size()", is(4)))
                .andExpect(jsonPath("$.message").isArray())
                .andExpect(jsonPath("$.message", hasItem(NameConstants.PASSWORD_LEN_VIOLATION_MESSAGE)))
                .andExpect(jsonPath("$.message", hasItem(NameConstants.EMAIL_VIOLATION_MESSAGE)))
                .andExpect(jsonPath("$.message", hasItem(NameConstants.USERNAME_EMPTY_VIOLATION_MESSAGE)))
        ;

    }

        /*
             /api/user/favorites

        TODO: add user validation
        */

        @Test
        @DisplayName("Test guest user adding items to favorites")
        void testUnsuccessfulAddingToFavorites() throws Exception {
            mockMvc.perform
                    (
                            post("/api/user/favorite/1")
                    )                .andExpect(status().isBadRequest());
            // try incorrect patch POST method
            mockMvc.perform
                    (
                            patch("/api/user/favorite/1")
                    )                .andExpect(status().isMethodNotAllowed());

            mockMvc.perform
                    (
                            delete("/api/user/favorite/a")
                    )                .andExpect(status().isBadRequest());
        }


        /*

             /api/admin/update/user/{userId}
        */

    @Test
    @DisplayName("Test guest user trying update user details")
    void testUnsuccessfulChangeUser() throws Exception {
        mockMvc.perform
                (
                        put("/api/admin/update/user/1")
                )                .andExpect(status().isUnauthorized());

    }

//    @Test
//    @DisplayName("Test demo user adding items to favorites")
//    @WithMockUser(username = "demo")
//    void testSuccessfulAddingFavorites() throws Exception {
//        mockMvc.perform
//                (
//                        put("/api/user/favorite/1")
//                ).andExpect(status().isOk());
//
//    }


        /*
             article
        */

//    @Test
//    @DisplayName("Test article creation with authorized user using jwt")
//    @WithMockUser(username = "admin")
//        // TODO: review
//    void testRegistrationWithAuthorisedUser() throws Exception {
//        var user = new CreateUserDTO("username", "user@user.lt", "pass", "pass");
//        String jsonUserDetails = new Gson().toJson(user);
//
//        String username = "user";
//        String password = "user1234";
//
//
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(LOGIN_URL)
//                .content(new UserPassword(username, password).json()))
//                .andExpect(status().isOk()).andReturn();
//
//        String response = result.getResponse().getContentAsString();
//        response = response.replace("{\"jwt\": \"", "");
//        String token = response.replace("\"}", "");
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/register")
//                .header("Authorization", "Bearer " + token))
//                .andExpect(status().isOk());
//
//        mockMvc.perform
//                (
//                        post("/api/user/register")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .accept(MediaType.APPLICATION_JSON)
//                                .content(jsonUserDetails)
//                )
//                .andExpect(status().isCreated());
//
//    }

}
