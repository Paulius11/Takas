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
    private final RoleRepository roleRepository;
    //Later cache this role so it would save load on server

    public CreateUserDTO createUser(CreateUserDTO userForm) {

        ArticleUser user = new ArticleUser();




            if (!userForm.getPassword().equals(userForm.getConfirmPassword())) {
                throw new PasswordDontMatchException("Password's doesn't match!");
            }
            //Hashing passwords


            Role userRole = repository.findByRole("USER");
            if (userRole == null) {
                Role role = new Role();
                role.setRole("USER");
                userRole = role;
                roleRepository.save(role);
            }
            user.setUsername(userForm.getUsername());
            user.setFullName(userForm.getFullName());
            user.setPassword(encoder.encode(userForm.getPassword()));
            user.setRoles(Set.of(userRole));

            userRepository.save(user);

        userForm.setPassword("");
        userForm.setConfirmPassword("");


        return userForm;
    }
}
