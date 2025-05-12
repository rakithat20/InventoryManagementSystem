package src.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAO {

    public int addSupplier(Supplier supplier) {
        String sql = "INSERT INTO suppliers (name, contact_number, email, address) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, supplier.getName());
            stmt.setString(2, supplier.getContactNumber());
            stmt.setString(3, supplier.getEmail());
            stmt.setString(4, supplier.getAddress());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public Supplier getSupplierById(int id) {
        String sql = "SELECT * FROM suppliers WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Supplier(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("contact_number"),
                        rs.getString("email"),
                        rs.getString("address")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Supplier> getAllSuppliers() {
        List<Supplier> suppliers = new ArrayList<>();
        String sql = "SELECT * FROM suppliers";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                suppliers.add(new Supplier(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("contact_number"),
                        rs.getString("email"),
                        rs.getString("address")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return suppliers;
    }

    public boolean updateSupplier(Supplier supplier) {


        String sql = "UPDATE suppliers SET name = ?, contact_number = ?, email = ?, address = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, supplier.getName());
            stmt.setString(2, supplier.getContactNumber());
            stmt.setString(3, supplier.getEmail());
            stmt.setString(4, supplier.getAddress());
            stmt.setInt(5, supplier.getId());



            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteSupplier(int id) {
        String sql = "DELETE FROM suppliers WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
