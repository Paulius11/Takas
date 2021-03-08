package lt.idomus.takas.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lt.idomus.takas.constant.NameConstants;
import lt.idomus.takas.dto.CreateUserDTO;
import lt.idomus.takas.exceptions.exception.CustomMessage;
import lt.idomus.takas.exceptions.exception.PasswordDontMatchException;
import lt.idomus.takas.exceptions.exception.UserCreationError;
import lt.idomus.takas.model.*;
import lt.idomus.takas.repository.ArticleRepository;
import lt.idomus.takas.repository.UserRepository;
import lt.idomus.takas.security.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

import static lt.idomus.takas.enums.Role.ROLE_USER;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {


    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider provider;


    public JwtLoginSuccessResponse loginAttempt(LoginRequest request) {


        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));


            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = provider.generateToken(authentication);

            Optional<ArticleUser> user = userRepository.findByUsername(authentication.getName());

            user.ifPresent(articleUser -> articleUser.setPassword("hidden")); // hide password field

            return new JwtLoginSuccessResponse(jwt, NameConstants.LOGIN_AUTHENTICATED, user);

        } catch (AuthenticationException e) {
            return new JwtLoginSuccessResponse(NameConstants.LOGIN_UNAUTHENTICATED);
        }
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
        } catch (ConstraintViolationException e) {
            //TODO: kodėl nepagauna šitos exception'o
            throw new UserCreationError("Duplicate username");
        } catch (Exception e) {
            log.error(String.format("User creation error: %s", e.getCause()));
            throw new UserCreationError("Registration error ");
        }
    }


    public Optional<ArticleUser> getUserDetails(Authentication authentication) {
        Optional<ArticleUser> userData = Optional.ofNullable(userRepository.findByUsername(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("User not found")));
        userData.ifPresent(articleUser -> articleUser.setPassword("hidden")); // hide password field
        return userData;
    }

    public CustomMessage<Number> favoritesAdd(Long articleID, Authentication authentication, boolean remove) {
        CustomMessage<Number> response = new CustomMessage<>();

        Optional<Article> article = articleRepository.findById(articleID);
        if (article.isPresent()) {
            Optional<ArticleUser> userData = userRepository.findByUsername(authentication.getName());
            if (userData.isPresent()) {
                if (remove) {
                    userData.get().removeFromFavorites(Math.toIntExact(articleID));
                } else {
                    userData.get().addToFavorites(Math.toIntExact(articleID));
                }
                ArticleUser save = userRepository.save(userData.get());
                log.debug("Favorite list updated: " + save.getFavorites());
                response.setMessage("Favorites updated!");
                response.add("articleID", articleID);
                response.add("size", save.getFavorites().size());
                response.setStatus(true);
            } else {
                response.setMessage("User not found!");
                response.setStatus(false);
            }
            log.debug("" + response);
            return response;
        }
        response.setMessage("Article not found with provided id");
        response.setStatus(false);
        return response;
    }


    public CustomMessage<?> updateUser(ArticleUserDetailsPost userDetailsPost, Long userID) {
        Optional<ArticleUser> userDataDB_ = userRepository.findById(userID);
        CustomMessage<?> response = new CustomMessage<>();
        log.debug("" + userDetailsPost);
        if (userDataDB_.isPresent()) {
            var userDataDB = userDataDB_.get();

            if (userDetailsPost.getUsername() != null) {
                userDataDB.setUsername(userDetailsPost.getUsername());
            }

            if (userDetailsPost.getFavorites() != null) {
                userDataDB.setFavorites(userDetailsPost.getFavorites());
            }
            if (userDetailsPost.getEmail() != null) {
                userDataDB.setEmail(userDetailsPost.getEmail());
            }

            if (userDetailsPost.getRole() != null) {
                userDataDB.setRoles(userDetailsPost.getRole());
            }
            ArticleUser save = userRepository.save(userDataDB);
            save.setPassword("hidden");     //TODO: DEBUG remove in production?
            response.add("data", save); //TODO: DEBUG remove in production?
            response.setMessage("User updated!");
            response.setStatus(true);
            return response;
        }
        response.setStatus(false);
        response.setMessage("Failed to update user!");
        return response;

    }

    public CustomMessage<Object> getUser(Long userId) {
        var response = new CustomMessage<>();
        Optional<ArticleUser> user = userRepository.findById(userId);
        if (user.isPresent()) {
            response.setMessage("User found!");
            response.setStatus(true);

            user.ifPresent(x -> x.setPassword("hidden")); //TODO: DEBUG remove in production?
            response.add("data", user);               //TODO: DEBUG remove in production?
        } else {
            response.setMessage("User not found");
            response.setStatus(false);
        }
        return response;
    }

    public CustomMessage<Object> deleteUser(Long userId, Authentication authentication) {
        // get logger user id
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
        var loggedUserID = principal.getId();

        var response = new CustomMessage<>();
        Optional<ArticleUser> user = userRepository.findById(userId);
        log.debug("userID:" + userId + " : "+ user);
        if (user.isPresent()) {
            if (loggedUserID.equals(userId)) {
                response.setMessage("You can't delete yourself!");
                response.setStatus(true);
                return response;
            }
            userRepository.delete(user.get());

            response.setMessage(String.format("User '%d' deleted!", userId));
            response.setStatus(true);
        } else {
            response.setMessage(String.format("User '%d' not found!", userId));
            response.setStatus(false);
        }
        return response;
    }

    public List<ArticleUser> getAllUsers() {
        return userRepository.findAll();
    }
}
