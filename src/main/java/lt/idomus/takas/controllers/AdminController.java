package lt.idomus.takas.controllers;

import lombok.AllArgsConstructor;
import lt.idomus.takas.services.UserService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/admin")
public class AdminController {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    private final UserService userService;

    /**
     * User can change his own information User information
     * @param userDetailsPost
     * @param authentication
     * @param headerStr
     * @return
     */

//    TODO: add admin controller
//    @PutMapping("/updateUser")
//    public ResponseEntity<?> updateUser(@RequestBody ArticleUserDetailsPost userDetailsPost, Authentication authentication,
//                                        @RequestHeader(value = AUTHORIZATION_HEADER) String headerStr) {
//        ArticleUser user = userService.favoriteAdd(userDetailsPost, authentication);
//        return new ResponseEntity<ArticleUser>(user, HttpStatus.OK);
//    }

}
