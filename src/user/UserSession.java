
package user;

public class UserSession {

    private static User currentUser;
    private static String currentRole;

    public static void startSession(User user, String role) {
        currentUser = user;
        currentRole = role;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static int getCurrentUserId() {

        if (currentUser == null) {
            return -1;
        }

        return currentUser.getUserId();
    }

    public static String getCurrentRole() {
        return currentRole;
    }

    public static boolean isLoggedIn() {
        return currentUser != null;
    }

    public static void logout() {
        currentUser = null;
        currentRole = null;
        System.out.println("You have been logged out successfully.");
    }
}
