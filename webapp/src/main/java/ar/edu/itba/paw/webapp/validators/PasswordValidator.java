package ar.edu.itba.paw.webapp.validators;

import ar.edu.itba.paw.webapp.utils.Pair;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by alebian on 12/8/16.
 */
public class PasswordValidator {
    public static ar.edu.itba.paw.webapp.utils.Pair<Boolean, String> validate(String password, String confirmation) {
        if (password == null)
            return new Pair<>(false, "Password missing");

        if (password.isEmpty() || password.length() < 8)
            return new Pair<>(false, "Password lenght must be 8 or more");

        Pattern pattern = Pattern.compile("\\s");
        Matcher matcher = pattern.matcher(password);

        if (matcher.find())
            return new Pair<>(false, "Password must not contain whitespaces");

        if (password.equals(confirmation)) {
            return new Pair<>(true, "");
        } else {
            return new Pair<>(false, "Passwords don't match");
        }
    }
}
