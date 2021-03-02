package lt.idomus.takas.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lt.idomus.takas.exceptions.exception.CustomMessage;
import lt.idomus.takas.model.ArticleUserDetailsPost;
import lt.idomus.takas.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/admin")
public class AdminController {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    private final UserService userService;

    /**
     * Change user data
     * @param userDetailsPost
     * @param userId user ID
     * @param headerStr Authentication string
     * @return user response message if change is successful
     */

    @ApiOperation(value = "Change user data", notes = "Change user data based on userID.")
    @PreAuthorize("hasAnyAuthority('admin')")
    @PostMapping("/user/{userId}")
    public ResponseEntity<?> updateUser(@Valid @RequestBody ArticleUserDetailsPost userDetailsPost,
                                        @PathVariable Long userId,
                                        @RequestHeader(value = AUTHORIZATION_HEADER) String headerStr) {
        CustomMessage<?> response = userService.updateUser(userDetailsPost, userId);
        if (response.isStatus()){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAnyAuthority('admin')")
    public ResponseEntity<?> getUser(@PathVariable Long userId,
                                        @RequestHeader(value = AUTHORIZATION_HEADER) String headerStr) {
        CustomMessage<Object> response = userService.getUser(userId);
        if (response.isStatus()){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }


}
