package lt.idomus.takas.controllers;

import lombok.AllArgsConstructor;
import lt.idomus.takas.dto.CreateUserDTO;
import lt.idomus.takas.exceptions.exception.EmptyFormException;
import lt.idomus.takas.model.LoginRequest;
import lt.idomus.takas.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;


    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody CreateUserDTO userForm) {

        if (userForm == null) {
            throw new EmptyFormException("Cannot pass empty form!");
        }

        CreateUserDTO user = userService.createUser(userForm);
        return new ResponseEntity<CreateUserDTO>(user, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest request) {

        if (request == null) return new ResponseEntity<String>("Empty body cannot be passed", HttpStatus.BAD_REQUEST);

        return ResponseEntity.ok(userService.loginAttempt(request));
    }

}
