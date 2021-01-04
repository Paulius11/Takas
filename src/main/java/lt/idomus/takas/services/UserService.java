package lt.idomus.takas.services;

import lombok.AllArgsConstructor;
import lt.idomus.takas.doa.UserRepository;
import lt.idomus.takas.dto.CreateUserDTO;
import lt.idomus.takas.exceptions.exception.PasswordDontMatchException;
import lt.idomus.takas.exceptions.exception.UserAlreadyExistsException;
import lt.idomus.takas.model.ArticleUser;
import lt.idomus.takas.model.JwtLoginSuccessResponse;
import lt.idomus.takas.model.LoginRequest;
import lt.idomus.takas.security.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

        return new JwtLoginSuccessResponse(jwt);
    }


    public CreateUserDTO createUser(CreateUserDTO userForm) {

        try {
            ArticleUser user = new ArticleUser();


            if (!userForm.getPassword().equals(userForm.getConfirmPassword())) {
                throw new PasswordDontMatchException("Password's doesn't match!");
            }
            //Hashing passwords

            user.setUsername(userForm.getUsername());
            user.setEmail(userForm.getEmail());
            user.setPassword(encoder.encode(userForm.getPassword()));
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
}
