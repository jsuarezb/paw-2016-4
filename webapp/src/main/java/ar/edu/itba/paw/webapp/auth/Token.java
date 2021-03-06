package ar.edu.itba.paw.webapp.auth;

import ar.edu.itba.paw.models.User;
import ar.edu.itba.paw.models.Doctor;
import ar.edu.itba.paw.models.Patient;
import ar.edu.itba.paw.webapp.config.WebAuthConfig;
import io.jsonwebtoken.*;

import java.util.Date;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by alebian on 12/8/16.
 */
public class Token {
    private final static Long TOKEN_DURATION = 86400000L; // 1 Day

    public static String create(final User user) {
        final Map<String, Object> claims = new HashMap<>();
        final Doctor doctor = user.getDoctor();
        final Patient patient = user.getPatient();
        if (doctor != null) {
            claims.put("doctorId", doctor.getId());
        }
        if (patient != null) {
            claims.put("patientId", patient.getId());
        }
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, WebAuthConfig.APP_KEY)
                .setClaims(claims)
                .setSubject(user.getId().toString())
                .setIssuedAt(currentDate())
                .setExpiration(expirationDate())
                .setIssuer(user.getEmail())
                .compact();
    }

    public static String decode(final String token) {
        try {
            final Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(WebAuthConfig.APP_KEY)
                    .parseClaimsJws(token);

            final Claims body = claims.getBody();
            if (body.getExpiration().before(currentDate())) {
                throw new SignatureException("");
            }
            return tokenIssuer(body.getId(), body.getIssuer());

        } catch (Exception e) {
            return null;
        }
    }

    private static final String DELIMITER = ";";

    public static String tokenIssuer(final String type, final String email) {
        return String.format("%s%s%s", type, DELIMITER, email);
    }

    public static String emailFromToken(final String decodedToken) {
        return decodedToken.split(DELIMITER)[1];
    }

    public static String typeFromToken(final String decodedToken) {
        return decodedToken.split(DELIMITER)[0];
    }

    private static Date currentDate() {
        return new Date(System.currentTimeMillis());
    }

    private static Date expirationDate() {
        return new Date(System.currentTimeMillis() + TOKEN_DURATION);
    }
}
