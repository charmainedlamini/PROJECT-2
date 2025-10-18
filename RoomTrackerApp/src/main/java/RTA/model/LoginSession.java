package RTA.model;

/**
 * @author Charmaine
 * added this class to use it to get the email of the user when they log in
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

