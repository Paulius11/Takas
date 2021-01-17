package lt.idomus.takas.services;

import lombok.AllArgsConstructor;
import lt.idomus.takas.repository.UserRepository;
import lt.idomus.takas.dto.CreateUserDTO;
import lt.idomus.takas.exceptions.exception.PasswordDontMatchException;
import lt.idomus.takas.exceptions.exception.UserAlreadyExistsException;
import lt.idomus.takas.model.ArticleUser;
import lt.idomus.takas.model.JwtLoginSuccessResponse;
import lt.idomus.takas.model.LoginRequest;
import lt.idomus.takas.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
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


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider provider;


    public JwtLoginSuccessResponse loginAttempt(LoginRequest request) {


        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));


        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = provider.generateToken(authentication);

        return new JwtLoginSuccessResponse(jwt);
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


    public Optional<ArticleUser> getUserInfo(Authentication authentication) {
        Optional<ArticleUser> userData = userRepository.findByUsername(authentication.getName());
        // hide password field
        userData.ifPresent(articleUser -> articleUser.setPassword("hidden"));
        return userData;
    }

    public Optional<ArticleUser> getUserInfo(String OauthId) {
        Optional<ArticleUser> userData = userRepository.findByOAuthID(OauthId);
        // hide password field
        userData.ifPresent(articleUser -> articleUser.setPassword("hidden"));
        return userData;
    }
}
