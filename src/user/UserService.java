
package user;

import java.util.ArrayList;

public class UserService {

    private UserDAO userDAO;

    // Constructor
    public UserService() {
        userDAO = new UserDAO();
    }

    // Register a new user
    public boolean registerUser(User user) {

        if (user.getName() == null || user.getName().trim().isEmpty()) {
            System.out.println("Name cannot be empty.");
            return false;
        }

        if (user.getEmail() == null || !user.getEmail().contains("@")) {
            System.out.println("Enter a valid email address.");
            return false;
        }

        if (user.getPasswordHash() == null
                || user.getPasswordHash().trim().isEmpty()) {
            System.out.println("Password cannot be empty.");
            return false;
        }

        if (user.getRoleId() <= 0) {
            System.out.println("Invalid role selected.");
            return false;
        }

        user.setAccountStatus("ACTIVE");

        return userDAO.addUser(user);
    }

    // Find user by email
    public User findUser(String email) {

        if (email == null || email.trim().isEmpty()) {
            System.out.println("Email cannot be empty.");
            return null;
        }

        return userDAO.getUserByEmail(email);
    }

    // Get all registered users
    public ArrayList<User> getAllUsers() {
        return userDAO.getAllUsers();
    }
    // Get profile
public User getUserProfile(int userId) {

    if (userId <= 0) {
        System.out.println("Invalid user ID.");
        return null;
    }

    return userDAO.getUserById(userId);
}

// Update profile
public boolean updateUserProfile(int userId, String name, String email) {

    if (name == null || name.trim().isEmpty()) {
        System.out.println("Name cannot be empty.");
        return false;
    }

    if (email == null || !email.contains("@")) {
        System.out.println("Enter a valid email address.");
        return false;
    }

    return userDAO.updateProfile(userId, name.trim(), email.trim());
}

// Change password
public boolean changePassword(int userId, String currentPassword,
                              String newPassword) {

    if (currentPassword == null || currentPassword.trim().isEmpty()) {
        System.out.println("Current password cannot be empty.");
        return false;
    }

    if (newPassword == null || newPassword.trim().length() < 6) {
        System.out.println("New password must contain at least 6 characters.");
        return false;
    }

    User user = userDAO.getUserById(userId);

    if (user == null) {
        System.out.println("User not found.");
        return false;
    }

    // This comparison assumes your current system stores the entered
    // password in password_hash directly. If you use hashing, this
    // comparison must use the same password verification method.
    if (!user.getPasswordHash().equals(currentPassword)) {
        System.out.println("Current password is incorrect.");
        return false;
    }

    return userDAO.updatePassword(userId, newPassword);
}
}
