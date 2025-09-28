package utils;

import java.util.regex.Pattern;

public class Validation {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    public static boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean isValidName(String name) {
        return name != null && name.length() >= 2; 
    }

    public static boolean isValidPassword(String password) {
        return password != null && password.length() >= 6; 
    }
}