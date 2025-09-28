package utils;

import model.Person;
import java.util.Optional;

public class SessionManager {
    
    private static Person currentUser = null;
    
    public static void setCurrentUser(Person user) {
        currentUser = user;
    }
    
    public static Optional<Person> getCurrentUser() {
        return Optional.ofNullable(currentUser);
    }
    
    public static boolean isLoggedIn() {
        return currentUser != null;
    }
    
    public static void logout() {
        currentUser = null;
    }
}
