package RTA.model;

/**
 * @author Charmaine
 */
public class LoginSession {

    private static String email; // store logged-in student email

    public static void setEmail(String userEmail) {
        email = userEmail;
    }

    public static String getEmail() {
        return email;
    }
}
