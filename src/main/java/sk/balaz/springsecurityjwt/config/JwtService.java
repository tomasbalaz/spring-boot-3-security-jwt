package sk.balaz.springsecurityjwt.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

  // https://www.allkeysgenerator.com/Random/Security-Encryption-Key-Generator.aspx
  private static final String SECRET_KEY =
      "67566B5970337336763979244226452948404D6351665468576D5A7134743777";

  public String extractUserName(String token) {
    return null;
  }

  private Claims extractAllClaims(String token) {
    return Jwts
        .parserBuilder()
        .setSigningKey(getSignInKey())
        .build()
        .parseClaimsJwt(token)
        .getBody();
  }

  private Key getSignInKey() {
    byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
    return Keys.hmacShaKeyFor(keyBytes);
  }

}
