package pl.urban.authservice.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.urban.authservice.entity.User;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {
    @Value("${jwt.expiration}")
    private Long expiration;
    private Key secretKey;
    public void rotateKey() {
        secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }

    public String createToken(Map<String, Object> claims, String subject, Boolean isShortExpiration) {
        long expirationTime = isShortExpiration ? expiration : expiration * 3000;

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();
    }

    public String generateToken(@NotNull User user, Boolean isShortExpiration) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRole().name());
        claims.put("purpose", isShortExpiration ? "password_reset" : "auth");
        return createToken(claims, user.getEmail(), isShortExpiration);
    }

    public String extractSubjectFromToken(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, @NotNull Function<Claims, T> claimsResolver) {
        final Claims claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Boolean isValidateToken(String token, Boolean isPasswordReset) {
        try {
            Claims claims = extractAllClaims(token);
            boolean isExpired = claims.getExpiration().before(new Date());
            String purpose = claims.get("purpose", String.class);
            return !isExpired && purpose.equals(isPasswordReset ? "password_reset" : "auth");
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @PostConstruct
    private void init() {
        rotateKey();
    }
}
