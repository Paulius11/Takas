package lt.idomus.takas.security;

import io.jsonwebtoken.*;
import lt.idomus.takas.exceptions.exception.InvalidTokenException;
import lt.idomus.takas.model.ArticleUser;
import lt.idomus.takas.model.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static lt.idomus.takas.security.SecurityConstant.SECRET;
import static lt.idomus.takas.security.SecurityConstant.expirationInMillisecs;

@Component
public class JwtTokenProvider {

    public String generateOauth2Token(ArticleUser articleUser) {
        Date now = new Date(System.currentTimeMillis());
        Date expiry = new Date(now.getTime() + expirationInMillisecs);

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", (articleUser.getId()));


        return Jwts.builder()
                .setSubject(String.valueOf(articleUser.getId()))
                .setClaims(claims)
                .setAudience(String.valueOf(articleUser.getRoles()))
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public String generateToken(Authentication authentication) {

        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

        Date now = new Date(System.currentTimeMillis());

        Date expiry = new Date(now.getTime() + expirationInMillisecs);

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", (user.getId()));


        return Jwts.builder()
                .setSubject(user.getId().toString())
                .setClaims(claims)
                .setAudience(user.getRoles().toString())
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public boolean validateToken(String token) throws InvalidTokenException {
        try {
            //Parse Jwt claims and check if expiration date is not before now
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return !claimsJws.getBody().getExpiration().before(new Date());
        } catch (ExpiredJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
            throw new InvalidTokenException("Invalid token", e);
        }
    }

    public Long getUserIdFromJwt(String token) {
        Claims claim = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        String id = (String) claim.get("id");
        return Long.parseLong(id);
    }

}
