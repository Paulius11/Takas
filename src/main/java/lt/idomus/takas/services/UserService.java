package lt.idomus.takas.services;

import lombok.AllArgsConstructor;
import lt.idomus.takas.doa.RoleRepository;
import lt.idomus.takas.doa.UserRepository;
import lt.idomus.takas.dto.CreateUserDTO;
import lt.idomus.takas.exceptions.exception.PasswordDontMatchException;
import lt.idomus.takas.exceptions.exception.UserAlreadyExistsException;
import lt.idomus.takas.model.ArticleUser;
import lt.idomus.takas.model.Role;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class UserService {

    private final RoleRepository repository;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    //Later cache this role so it would save load on server
    private final Role userRole = repository.findByRole("USER");

    public CreateUserDTO createUser(CreateUserDTO userForm) {

        ArticleUser user = new ArticleUser();

        try {
            if (!userForm.getPassword().equals(userForm.getConfirmPassword()))
                throw new PasswordDontMatchException("Password's doesn't match!");

            user.setUsername(userForm.getUsername());
            user.setFullName(userForm.getFullname());
            user.setPassword(encoder.encode(userForm.getPassword()));
            user.setRoles(Set.of(userRole));

            userRepository.save(user);
        } catch (Exception e) {
            throw new UserAlreadyExistsException("User already exists with that username!");
        }
        //Hashing password return Dto with masked password
        userForm.setPassword(encoder.encode(userForm.getPassword()));
        userForm.setConfirmPassword(encoder.encode(userForm.getPassword()));

        return userForm;
    }
}
