package ar.edu.itba.paw.webapp.auth;

import ar.edu.itba.paw.models.User;
import ar.edu.itba.paw.webapp.config.WebAuthConfig;
import io.jsonwebtoken.*;

import java.util.Date;

/**
 * Created by alebian on 12/8/16.
 */
public class Token {
    private final static Long TOKEN_DURATION = 86400000L; // 1 Day
    private final static String SUBJECT = "USER_SESSION";

    public static String create(final User user) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, WebAuthConfig.APP_KEY)
                .setSubject(SUBJECT)
                .setIssuedAt(currentDate())
                .setExpiration(expirationDate())
                .setId(user.getId().toString())
                .setIssuer(user.getUsername())
                .compact();
    }

    public static String decode(String token) {
        try {
            Jws<Claims> claims = Jwts.parser()
                    .requireSubject(SUBJECT)
                    .setSigningKey(WebAuthConfig.APP_KEY)
                    .parseClaimsJws(token);

            final Claims body = claims.getBody();
            if (body.getExpiration().before(currentDate())) {
                throw new SignatureException("");
            }
            return body.getIssuer();

        } catch (Exception e) {
            return null;
        }
    }

    private static Date currentDate() {
        return new Date(System.currentTimeMillis());
    }

    private static Date expirationDate() {
        return new Date(System.currentTimeMillis() + TOKEN_DURATION);
    }
}
