package ar.edu.itba.paw.webapp.auth;

import ar.edu.itba.paw.models.User;
import ar.edu.itba.paw.webapp.config.WebAuthConfig;
import io.jsonwebtoken.*;

import java.util.Date;

/**
 * Created by alebian on 12/8/16.
 */
public class PasswordRecoveryToken {
    private final static Long TOKEN_DURATION = 86400000L; // 1 Day
    private final static String KEY = "62a29605651a4fa756b267688dc98ae61ec020a1946d270695e50d4cec93b76700b55069bc6bd969e6d8787559dacfc708eb69c9f152cb73a54c143586c3212c";

    public static String create(final String email) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, KEY)
                .setIssuedAt(currentDate())
                .setExpiration(expirationDate())
                .setSubject(email)
                .compact();
    }

    public static String decode(final String token) {
        try {
            final Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(KEY)
                    .parseClaimsJws(token);

            final Claims body = claims.getBody();
            if (body.getExpiration().before(currentDate())) {
                throw new SignatureException("");
            }
            return body.getSubject();

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
