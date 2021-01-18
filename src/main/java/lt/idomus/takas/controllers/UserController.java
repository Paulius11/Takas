package lt.idomus.takas.controllers;

import lombok.AllArgsConstructor;
import lt.idomus.takas.dto.CreateUserDTO;
import lt.idomus.takas.exceptions.exception.EmptyFormException;
import lt.idomus.takas.model.LoginRequest;
import lt.idomus.takas.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;


    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody CreateUserDTO userForm) {

        if (userForm == null) {
            throw new EmptyFormException("Cannot pass empty form!");
        }

        CreateUserDTO user = userService.createUser(userForm, false);
        return new ResponseEntity<CreateUserDTO>(user, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest request) {

        if (request == null) return new ResponseEntity<String>("Empty body cannot be passed", HttpStatus.BAD_REQUEST);

        return ResponseEntity.ok(userService.loginAttempt(request));
    }

    @GetMapping("/details")
    public ResponseEntity<?> getUserInfo(Authentication auth) {
        if (auth == null) return new ResponseEntity<String>("No authentication detected", HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(userService.getUserInfo(auth));
    }

}
