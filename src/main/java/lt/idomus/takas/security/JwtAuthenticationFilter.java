package lt.idomus.takas.security;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lt.idomus.takas.model.ArticleUser;
import lt.idomus.takas.model.CustomUserDetails;
import lt.idomus.takas.oauth.OAuth2UserService;
import lt.idomus.takas.oauth.OAuthAttributes;
import lt.idomus.takas.services.CustomUserDetailsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider tokenProvider;
    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private OAuth2UserService customOAuth2UserService;     // This is needed because "beans in the application context form a cycle"


    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        log.debug("Running " + this.getClass().getSimpleName());
        String jwt = getJwtFromRequest(request);
        String token = StringUtils.removeStart(Optional.ofNullable(jwt).orElse(""), "Bearer").trim();
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        OAuthAttributes attributes = OAuthAttributes.of(authentication);
        if (!token.isEmpty()) {
            // When jwt is sent
            logger.info("user:password logging flow");
            if (org.springframework.util.StringUtils.hasText(token) && tokenProvider.validateToken(token)) {

                Long userId = tokenProvider.getUserIdFromJwt(jwt);
                ArticleUser userDetails = userDetailsService.loadUserById(userId);
                CustomUserDetails user = new CustomUserDetails(userDetails);

                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null,
                        user.getAuthorities());

                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(auth);
                filterChain.doFilter(request, response);
                return;
        }
        if (authentication == null) {
            // If user is not logged with google or signed in
            filterChain.doFilter(request, response);
            return;
        }


        }
        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {

        String header = request.getHeader("Authorization");

        if (org.springframework.util.StringUtils.hasText(header) && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }
}
