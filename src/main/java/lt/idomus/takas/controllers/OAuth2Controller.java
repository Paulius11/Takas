package lt.idomus.takas.controllers;

import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
public class OAuth2Controller {

    @GetMapping("/loginUnsuccessful")
    public String unsuccessful(){
        return "Please try again...";
    }


    @GetMapping("/loginSuccess")
    public String index() {
        OAuth2User user = getCurrentUser();
        log.info(user);
        return "Hello " + user.getAttributes().get("name") + ". Your email is " + user.getAttributes().get("email")
                + " and your profile picture is <img src='"+user.getAttributes().get("picture")+"' /> <br />"
                + "<a href='/logout'>logout</a>";
    }


    public OAuth2User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return ((OAuth2AuthenticationToken)auth).getPrincipal();
    }
}
