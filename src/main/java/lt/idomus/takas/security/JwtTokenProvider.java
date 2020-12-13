package lt.idomus.takas.security;

import io.jsonwebtoken.*;
import lt.idomus.takas.exceptions.exception.InvalidTokenException;
import lt.idomus.takas.model.ArticleUser;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static lt.idomus.takas.security.SecurityConstant.SECRET;

@Component
public class JwtTokenProvider {

    private long expirationInMillisecs = 3600000;

    public String generateToken(Authentication authentication) {

        ArticleUser user = (ArticleUser) authentication.getPrincipal();

        Date now = new Date(System.currentTimeMillis());

        Date expiry = new Date(now.getTime() + expirationInMillisecs);

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", (Long.toString(user.getId())));
        claims.put("roles", user.getRoles());

        return Jwts.builder()
                .setSubject(user.getId().toString())
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public boolean validateToken(String token) throws InvalidTokenException {
        try {
            Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
            throw new InvalidTokenException("Invalid token", e);
        }
    }
}
