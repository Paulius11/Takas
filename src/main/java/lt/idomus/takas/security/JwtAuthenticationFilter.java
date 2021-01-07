package lt.idomus.takas.security;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lt.idomus.takas.model.ArticleUser;
import lt.idomus.takas.model.CustomUserDetails;
import lt.idomus.takas.services.CustomUserDetailsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider tokenProvider;
    @Autowired
    private CustomUserDetailsService userDetailsService;

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {


        String jwt = getJwtFromRequest(request);
        String token = StringUtils.removeStart(Optional.ofNullable(jwt).orElse(""), "Bearer").trim();
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();

        try {
            Map<String , Object> userDetails = ((DefaultOidcUser)authentication.getPrincipal()).getAttributes();
            String name = (String) userDetails.get("name");
            String email = (String) userDetails.get("email");
            String givenName = (String) userDetails.get("given_name");
            String familyName = (String) userDetails.get("family_name");
            System.out.println(name);
            System.out.println(email);
            System.out.println(givenName);
            System.out.println(familyName);

            String authorizedClientRegistrationId = ((OAuth2AuthenticationToken) authentication).getAuthorizedClientRegistrationId();
            if (authorizedClientRegistrationId.equals("google")) {
                System.out.println("Logging with google account");

                ArticleUser userDetailss = (ArticleUser) userDetailsService.loadUserByEmail(email);
                CustomUserDetails user = new CustomUserDetails(userDetailss);

                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null,
                        user.getAuthorities());

                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(auth);

            }
        } catch (Exception e) {

            if (!authentication.isAuthenticated()) {
                System.out.println("Logging jwt usual user");
                if (org.springframework.util.StringUtils.hasText(token) && tokenProvider.validateToken(token)) {

                    Long userId = tokenProvider.getUserIdFromJwt(jwt);
                    ArticleUser userDetails = userDetailsService.loadUserById(userId);
                    CustomUserDetails user = new CustomUserDetails(userDetails);

                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null,
                            user.getAuthorities());

                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
            filterChain.doFilter(request, response);
        }
        }


    private String getJwtFromRequest(HttpServletRequest request) {

        String header = request.getHeader("Authorization");

        if (org.springframework.util.StringUtils.hasText(header) && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }
}
