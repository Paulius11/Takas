package lt.idomus.takas.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lt.idomus.takas.exceptions.exception.CustomMessage;
import lt.idomus.takas.model.ArticleUser;
import lt.idomus.takas.model.ArticleUserDetailsPost;
import lt.idomus.takas.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/admin")
public class AdminController {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    private final UserService userService;

    /**
     * Change user data
     *
     * @param userDetailsPost
     * @param userId          user ID
     * @return user response message if change is successful
     */

    @ApiOperation(value = "Change user data", notes = "Change user data based on userID.")
    @PreAuthorize("hasAnyAuthority('admin')")
    @PostMapping("/user/{userId}")
    public ResponseEntity<?> updateUser(@Valid @RequestBody ArticleUserDetailsPost userDetailsPost,
                                        @PathVariable Long userId) {
        CustomMessage<?> response = userService.updateUser(userDetailsPost, userId);
        if (response.isStatus()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    /**
     * Get custom user data
     *
     * @param userId    user id
     * @return user data
     */

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAnyAuthority('admin')")
    public ResponseEntity<?> getUser(@PathVariable Long userId) {
        CustomMessage<Object> response = userService.getUser(userId);
        if (response.isStatus()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }


    /**
     * Delete user
     *
     * @param userId    user id
     * @return message
     */

    @DeleteMapping("/delete/{userId}")
    @PreAuthorize("hasAnyAuthority('admin')")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId, Authentication authentication) {
        CustomMessage<Object> response = userService.deleteUser(userId, authentication);
        if (response.isStatus()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }


    //    TODO: rodyti tik reikalingus laukelius
    @PreAuthorize("hasAnyAuthority('admin')")
    @GetMapping("/all")
    public List<ArticleUser> getAllUserData() {
        return userService.getAllUsers();
    }


}
