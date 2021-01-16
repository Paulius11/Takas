package lt.idomus.takas.oauth;

import lombok.extern.slf4j.Slf4j;
import lt.idomus.takas.model.ArticleUser;
import lt.idomus.takas.security.JwtTokenProvider;
import lt.idomus.takas.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Map;
import java.util.Optional;


@Slf4j
@Service
/**
 * This file is loaded after successful oauth2 logging
 **/
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private JwtTokenProvider provider;

    @Autowired
    private OAuth2UserService customOAuth2UserService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        log.info("Running " + this.getClass().getSimpleName());

        Map<String, Object> userDetails = null;
        try {
            userDetails = ((DefaultOidcUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAttributes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        OAuthAttributes attributes = OAuthAttributes.of(userDetails);
        logger.info(attributes.getName());

        ArticleUser loadedUser = customOAuth2UserService.manageUser(attributes);


        String generatedJwtToken = provider.generateOauth2Token(authentication);
        int maxAge = 7 * 24 * 60 * 60; // maxAge - 7 days


        CookieUtils.addCookie(response, "jwt", generatedJwtToken, maxAge);

        /* Get cookie from frontend
         * "successfulLoginRedirectUrl" cookie name set from frontend
         * this is url to be redirected   */
        Optional<Cookie> redirectUrl = CookieUtils.getCookie(request, "successfulLoginRedirectUrl");

        if (redirectUrl.isPresent()) {
            //TODO: use Base64?
            String url = URLDecoder.decode(redirectUrl.get().getValue(), "utf-8");
            log.debug("Getting redirect url");
            log.debug("redirectUrl: " + url);
            log.debug("redirecting");
            setDefaultTargetUrl(url);
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }


}
