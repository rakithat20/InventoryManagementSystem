package src.model;

import java.sql.*;

public class UserDAO {
    public boolean registerUser(User user) {
        String sql = "INSERT INTO users (username, password_hash) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPasswordHash());
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public User login(String username, String passwordHash) {
        String sql = "SELECT * FROM users WHERE username = ? AND password_hash = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, passwordHash);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password_hash")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
