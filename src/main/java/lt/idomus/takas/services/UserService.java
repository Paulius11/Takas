package lt.idomus.takas.services;

import lombok.AllArgsConstructor;
import lt.idomus.takas.dto.CreateUserDTO;
import lt.idomus.takas.exceptions.exception.PasswordDontMatchException;
import lt.idomus.takas.exceptions.exception.UserAlreadyExistsException;
import lt.idomus.takas.model.ArticleUser;
import lt.idomus.takas.model.ArticleUserDetailsPost;
import lt.idomus.takas.model.JwtLoginSuccessResponse;
import lt.idomus.takas.model.LoginRequest;
import lt.idomus.takas.repository.UserRepository;
import lt.idomus.takas.security.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static lt.idomus.takas.enums.Role.ROLE_USER;

@Service
@AllArgsConstructor
public class UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider provider;


    public JwtLoginSuccessResponse loginAttempt(LoginRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = provider.generateToken(authentication);

        Optional<ArticleUser> user = userRepository.findByUsername(authentication.getName());
        // hide password field
        user.ifPresent(articleUser -> articleUser.setPassword("hidden"));

        return new JwtLoginSuccessResponse(jwt, "Authentication successful!", user);
    }


    public CreateUserDTO createUser(CreateUserDTO userForm, boolean oauth2User) {

        try {
            ArticleUser user = new ArticleUser();
            if (!oauth2User) {
                //Hashing passwords
                if (!userForm.getPassword().equals(userForm.getConfirmPassword())) {
                    throw new PasswordDontMatchException("Password's doesn't match!");
                }
                user.setPassword(encoder.encode(userForm.getPassword()));
            }
            user.setUsername(userForm.getUsername());
            user.setEmail(userForm.getEmail());
            user.setRoles(ROLE_USER);
            user.setAuthority(ROLE_USER.getAuthorities());

            userRepository.save(user);

            userForm.setPassword("");
            userForm.setConfirmPassword("");

            return userForm;
        } catch (Exception e) {
            throw new UserAlreadyExistsException("Username is already taken");
        }
    }


    public Optional<ArticleUser> getUserDetails(Authentication authentication) {
        Optional<ArticleUser> userData = userRepository.findByUsername(authentication.getName());
        userData.ifPresent(articleUser -> articleUser.setPassword("hidden")); // hide password field
        return userData;
    }

    public Optional<ArticleUser> getUserInfo(String OauthId) {
        Optional<ArticleUser> userData = userRepository.findByOAuthID(OauthId);
        userData.ifPresent(articleUser -> articleUser.setPassword("hidden"));    // hide password field

        return userData;
    }

    public ArticleUser updateUser(ArticleUserDetailsPost editedArticleUser, Authentication authentication) {
        Optional<ArticleUser> userData = userRepository.findByUsername(authentication.getName());
        if (userData.isPresent()){
            userData.get().setFavorites(editedArticleUser.getFavorites());
            return userRepository.save(userData.get());
        }
        // TODO: update, kad paimtu tik t1 userį, kuris prisijungęs
        // TODO: user/id atnaudiną userį tik adminas
        return null;
    }
}
