package lt.idomus.takas.oauth;

import lombok.Builder;
import lombok.Getter;
import lt.idomus.takas.enums.Role;
import lt.idomus.takas.model.ArticleUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;

import java.util.Map;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String id;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;
    private String className;
    private String roles;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey,
                           String name, String email, String picture, String className, String sub) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.className = className;
        this.id = sub;
    }

    // User information returned by OAuth2User is Map, so each value must be converted
    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        return ofGoogle(userNameAttributeName, attributes);
    }

    public static OAuthAttributes of(Map<String, Object> attributes) {
        return ofGoogle(attributes);
    }

    public static OAuthAttributes of(Authentication authentication) {
        return ofAuthentication(authentication);
    }

    /*  Usage
        Authentication authentication = SecurityContextHolder.getContext()
        OAuthAttributes attributes = OAuthAttributes.of(authentication);
        ;*/
    private static OAuthAttributes ofAuthentication(Authentication authentication) {
        if (authentication == null) {
            return null;
        }
        Map<String, Object> attributes = ((DefaultOidcUser) authentication.getPrincipal()).getAttributes();
        return OAuthAttributes.builder()
                .className(authentication.getClass().getSimpleName())
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .attributes(attributes)
                .build();
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    /*
       Usage:
       var userDetails = ((DefaultOidcUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAttributes();
       OAuthAttributes attributes = OAuthAttributes.of(userDetails);
    */
    private static OAuthAttributes ofGoogle(Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .sub((String) attributes.get("sub"))
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .attributes(attributes)
                .build();
    }


    public ArticleUser toEntity() {
        return ArticleUser.builder()
                .OAuth(true)
                .OAuthID(id)
                .username(name)
                .email(email)
                .roles(Role.ROLE_USER)
                .authority(Role.ROLE_USER.getAuthorities())
                .build();
    }


}