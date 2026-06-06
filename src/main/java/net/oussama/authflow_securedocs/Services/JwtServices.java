package net.oussama.authflow_securedocs.Services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtServices {
    @Value("${secret.key}")
    private  String screctKey ;

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(screctKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public String generateToken(Authentication authentication) {
        Map<String,Object> claims = new HashMap<>();
        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(authentication.getName())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+60*60*30))
                .and()
                .signWith(getSigningKey())
                .compact();
    }
    public String DecodeToken(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) getSigningKey()).build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();

    }
    private Key getKey(){
      byte[] keyBytes = Decoders.BASE64.decode(screctKey);
      return Keys.hmacShaKeyFor(keyBytes);
    }

}
