package com.haltebogen.gittalk.config.jwt;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private static final int ACCESS_TOKEN_EXPIRATION_TIME = 7200000; // 2 hour
    private static final int REFRESH_TOKEN_EXPIRATION_TIME = 1209600000; // 14 * 24 * 60 * 60 * 1000 = 2 weeks
    private final Environment env;

    public String generateAccessToken(Authentication authentication) {
        return Jwts.builder()
                .setSubject((String) authentication.getPrincipal())
                .setIssuedAt(new Date())
                .setExpiration(getExpirationDate(ACCESS_TOKEN_EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, getJwtSecretKey())
                .compact();
    }

    public String generateRefreshToken(Authentication authentication) {
        return Jwts.builder()
                .setSubject((String) authentication.getPrincipal())
                .setIssuedAt(new Date())
                .setExpiration(getExpirationDate(REFRESH_TOKEN_EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, getJwtSecretKey())
                .compact();
    }

    public String getUserFromJwt(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(getJwtSecretKey())
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean isValidToken(String token) {
        try {
            Jwts.parser().setSigningKey(getJwtSecretKey()).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }

    private String getJwtSecretKey() {
        return env.getProperty("jwt.secret-key");
    }

    private Date getExpirationDate(int tokenExpirationTime) {
        Date currentDate = new Date();
        return new Date(currentDate.getTime() + tokenExpirationTime);
    }

}
