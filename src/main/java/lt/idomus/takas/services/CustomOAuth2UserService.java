package lt.idomus.takas.services;

import lombok.AllArgsConstructor;
import lt.idomus.takas.repository.UserRepository;
import lt.idomus.takas.dto.CreateUserDTO;
import lt.idomus.takas.model.ArticleUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final CustomUserDetailsService customUserDetailsService;


    public void manageUser(Map<String, Object> userDetails) {
        String email = (String) userDetails.get("email");
        Optional<ArticleUser> userEmailInDB = userRepository.findByEmail(email);
        if (userEmailInDB.isPresent()) {
            System.out.println("Loading user from database");
            var auth = SecurityContextHolder.getContext().getAuthentication();
            System.out.println(auth);
            customUserDetailsService.loadUserByEmail(email);
        }
        if (userEmailInDB.isEmpty()) {
            System.out.println("Writing user to database");
            CreateUserDTO userForm = new CreateUserDTO();
            userForm.setEmail(email);
            userForm.setUsername((String) userDetails.get("name"));
            CreateUserDTO user = userService.createUser(userForm, true);
            System.out.println(user);
            // load user
        }
    }
}
