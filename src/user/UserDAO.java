
package user;

import jdbc.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO {

    // Add a new user
    public boolean addUser(User user) {

        String query = "INSERT INTO USERS "
                + "(name, email, password_hash, role_id, account_status) "
                + "VALUES (?, ?, ?, ?, ?)";

        try {
            Connection con = DatabaseConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPasswordHash());
            ps.setInt(4, user.getRoleId());
            ps.setString(5, user.getAccountStatus());

            int rows = ps.executeUpdate();

            ps.close();
            con.close();

            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Error adding user: " + e.getMessage());
            return false;
        }
    }

    // Find user using email
    public User getUserByEmail(String email) {

        String query = "SELECT * FROM USERS WHERE email = ?";

        try {
            Connection con = DatabaseConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            User user = null;

            if (rs.next()) {
                user = new User(
                    rs.getInt("user_id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("password_hash"),
                    rs.getInt("role_id"),
                    rs.getString("account_status")
                );
            }

            rs.close();
            ps.close();
            con.close();

            return user;

        } catch (SQLException e) {
            System.out.println("Error finding user: " + e.getMessage());
            return null;
        }
    }

    // Display all users
    public ArrayList<User> getAllUsers() {

        ArrayList<User> users = new ArrayList<>();

        String query = "SELECT * FROM USERS";

        try {
            Connection con = DatabaseConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                User user = new User(
                    rs.getInt("user_id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("password_hash"),
                    rs.getInt("role_id"),
                    rs.getString("account_status")
                );

                users.add(user);
            }

            rs.close();
            ps.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Error displaying users: " + e.getMessage());
        }

        return users;
    }
    // Get user by ID
public User getUserById(int userId) {

    String query = "SELECT * FROM USERS WHERE user_id = ?";

    try {
        Connection con = DatabaseConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();

        User user = null;

        if (rs.next()) {
            user = new User(
                rs.getInt("user_id"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("password_hash"),
                rs.getInt("role_id"),
                rs.getString("account_status")
            );
        }

        rs.close();
        ps.close();
        con.close();

        return user;

    } catch (SQLException e) {
        System.out.println("Error retrieving profile: " + e.getMessage());
        return null;
    }
}

// Update name and email
public boolean updateProfile(int userId, String name, String email) {

    String query = "UPDATE USERS SET name = ?, email = ? WHERE user_id = ?";

    try {
        Connection con = DatabaseConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, name);
        ps.setString(2, email);
        ps.setInt(3, userId);

        int rows = ps.executeUpdate();

        ps.close();
        con.close();

        return rows > 0;

    } catch (SQLException e) {
        System.out.println("Error updating profile: " + e.getMessage());
        return false;
    }
}

// Find role ID using role name
public int getRoleIdByName(String roleName) {

    String query = "SELECT role_id FROM ROLES WHERE role_name = ?";

    try {
        Connection con = DatabaseConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, roleName);

        ResultSet rs = ps.executeQuery();

        int roleId = -1;

        if (rs.next()) {
            roleId = rs.getInt("role_id");
        }

        rs.close();
        ps.close();
        con.close();

        return roleId;

    } catch (SQLException e) {
        System.out.println("Error retrieving role: " + e.getMessage());
        return -1;
    }
}

// Get role name using role ID
public String getRoleNameById(int roleId) {

    String query = "SELECT role_name FROM ROLES WHERE role_id = ?";

    try {
        Connection con = DatabaseConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1, roleId);

        ResultSet rs = ps.executeQuery();

        String roleName = null;

        if (rs.next()) {
            roleName = rs.getString("role_name");
        }

        rs.close();
        ps.close();
        con.close();

        return roleName;

    } catch (SQLException e) {
        System.out.println("Error retrieving role name: " + e.getMessage());
        return null;
    }
}

// Update stored password
public boolean updatePassword(int userId, String newPasswordHash) {

    String query = "UPDATE USERS SET password_hash = ? WHERE user_id = ?";

    try {
        Connection con = DatabaseConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, newPasswordHash);
        ps.setInt(2, userId);

        int rows = ps.executeUpdate();

        ps.close();
        con.close();

        return rows > 0;

    } catch (SQLException e) {
        System.out.println("Error updating password: " + e.getMessage());
        return false;
    }
}


}
