package lt.idomus.takas.oauth;

import lombok.extern.log4j.Log4j2;
import lt.idomus.takas.model.ArticleUser;
import lt.idomus.takas.repository.UserRepository;
import lt.idomus.takas.services.CustomUserDetailsService;
import lt.idomus.takas.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
public class OAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;


    public ArticleUser manageUser(OAuthAttributes auth) {
        log.debug("Running " + this.getClass().getSimpleName());
        Optional<ArticleUser> userEmailInDB = userRepository.findByEmail(auth.getEmail());
        if (userEmailInDB.isPresent()) {
            log.info("Loading user from database");
            ArticleUser articleUser = userEmailInDB.get();
            log.debug(articleUser);
            return articleUser;
        }
        log.info("Writing user to database");
        ArticleUser articleUser = auth.toEntity();
        log.debug(articleUser);
        return userRepository.save(articleUser);
    }
}
