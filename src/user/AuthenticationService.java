
package user;

import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class AuthenticationService {

    private UserDAO userDAO;

    public AuthenticationService() {
        userDAO = new UserDAO();
    }

    // Generate secure password hash
    private String hashPassword(String password) throws Exception {

        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);

        int iterations = 120000;

        KeySpec spec = new PBEKeySpec(
            password.toCharArray(), salt, iterations, 256
        );

        SecretKeyFactory factory =
            SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

        byte[] hash = factory.generateSecret(spec).getEncoded();

        return "PBKDF2$" + iterations + "$"
                + Base64.getEncoder().encodeToString(salt) + "$"
                + Base64.getEncoder().encodeToString(hash);
    }

    // Verify password
    private boolean verifyPassword(String password, String stored)
            throws Exception {

        String[] parts = stored.split("\\$");

        if (parts.length != 4 || !parts[0].equals("PBKDF2")) {
            return false;
        }

        int iterations = Integer.parseInt(parts[1]);
        byte[] salt = Base64.getDecoder().decode(parts[2]);
        byte[] originalHash = Base64.getDecoder().decode(parts[3]);

        KeySpec spec = new PBEKeySpec(
            password.toCharArray(), salt, iterations, 256
        );

        SecretKeyFactory factory =
            SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

        byte[] newHash = factory.generateSecret(spec).getEncoded();

        if (newHash.length != originalHash.length) {
            return false;
        }

        int difference = 0;

        for (int i = 0; i < newHash.length; i++) {
            difference |= newHash[i] ^ originalHash[i];
        }

        return difference == 0;
    }

    // Register user
    public boolean registerUser(String name, String email,
                                String password, String roleName) {

        try {

            if (name == null || name.trim().isEmpty()
                    || email == null || !email.contains("@")
                    || password == null || password.length() < 6
                    || roleName == null || roleName.trim().isEmpty()) {

                System.out.println("Invalid registration details.");
                return false;
            }

            if (userDAO.getUserByEmail(email.trim()) != null) {
                System.out.println("Email already registered.");
                return false;
            }

            int roleId = userDAO.getRoleIdByName(roleName.trim());

            if (roleId <= 0) {
                System.out.println("Role does not exist.");
                return false;
            }

            if (roleName.trim().equalsIgnoreCase("ADMIN")) {
                System.out.println("Admin registration is not allowed.");
                return false;
            }

            String hashedPassword = hashPassword(password);

            User user = new User(
                0, name.trim(), email.trim(),
                hashedPassword, roleId, "ACTIVE"
            );

            return userDAO.addUser(user);

        } catch (Exception e) {
            System.out.println("Registration error: " + e.getMessage());
            return false;
        }
    }

    // Login user
    public User login(String email, String password) {

        if (email == null || password == null
                || email.trim().isEmpty() || password.isEmpty()) {
            System.out.println("Email and password are required.");
            return null;
        }

        User user = userDAO.getUserByEmail(email.trim());

        if (user == null) {
            System.out.println("Account not found.");
            return null;
        }

        if (!"ACTIVE".equalsIgnoreCase(user.getAccountStatus())) {
            System.out.println("Account is not active.");
            return null;
        }

        try {

            String stored = user.getPasswordHash();
            boolean valid;

            if (stored != null && stored.startsWith("PBKDF2$")) {
                valid = verifyPassword(password, stored);
            } else {
                valid = stored != null && stored.equals(password);

                if (valid) {
                    userDAO.updatePassword(
                        user.getUserId(), hashPassword(password)
                    );
                }
            }

            if (!valid) {
                System.out.println("Incorrect password.");
                return null;
            }

            return user;

        } catch (Exception e) {
            System.out.println("Login error: " + e.getMessage());
            return null;
        }
    }

    // Get role name
    public String getRoleName(int roleId) {
        return userDAO.getRoleNameById(roleId);
    }
}
