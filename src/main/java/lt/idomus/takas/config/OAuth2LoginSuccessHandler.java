package lt.idomus.takas.config;

import lt.idomus.takas.services.CustomOAuth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Map<String , Object> userDetails = ((DefaultOidcUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAttributes();

        String name = (String) userDetails.get("name");
        String email = (String) userDetails.get("email");
        String givenName = (String) userDetails.get("given_name");
        String familyName = (String) userDetails.get("family_name");
        System.out.println(name);
        System.out.println(email);
        System.out.println(givenName);
        System.out.println(familyName);

        customOAuth2UserService.manageUser(userDetails);

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
