package ugb.sat.madsi.Filter;

import org.springframework.stereotype.Service;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {
    private static final long EXPIRATION_TIME = 86400000; // 24h
    private static final Key SECRET_KEY = Keys.hmacShaKeyFor(
        "ma_clé_secrète_super_longue_32_caractères".getBytes()
    );

    // Génère un token JWT
    public String generateToken(String username) {
        return Jwts.builder()
                .claim("sub", username) // Utilisation de "claim" au lieu de setSubject
                .claim("exp", new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Utilisation de "claim" pour l'expiration
                .signWith(SECRET_KEY) // Suppression de SignatureAlgorithm.HS256
                .compact();
    }

    // Extrait l'utilisateur depuis un token
    public String getAuthUser(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.replace("Bearer ", "");
            return ((JwtParser) Jwts.parser() // Utilisation de parser() au lieu de parserBuilder()
                    .setSigningKey(SECRET_KEY) // Clé de signature
)
                    .parseClaimsJws(token) // Analyse du token
                    .getBody()
                    .getSubject(); // Récupère le "subject" (username)
        }
        return null;
    }
}