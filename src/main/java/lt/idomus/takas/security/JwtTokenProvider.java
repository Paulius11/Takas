package lt.idomus.takas.security;

import io.jsonwebtoken.*;
import lt.idomus.takas.exceptions.exception.InvalidTokenException;
import lt.idomus.takas.model.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

import static lt.idomus.takas.security.SecurityConstant.SECRET;

@Component
public class JwtTokenProvider {

    final long expirationInMillisecs = 3600000;

    public String generateToken(Authentication authentication) {

        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

        Date now = new Date(System.currentTimeMillis());

        Date expiry = new Date(now.getTime() + expirationInMillisecs);

//        Map<String, Object> claims = new HashMap<>();
//        claims.put("id", (Long.toString(user.getId())));
//        claims.put("roles", user.getRoles());

        return Jwts.builder()
                .setSubject(user.getUsername())
//                .setClaims(claims)
                .setAudience(user.getRoles().toString())
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

    public Long getUserIdFromJwt(String token) {
        Claims claim = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        String id = (String) claim.get("id");
        return Long.parseLong(id);
    }
}
