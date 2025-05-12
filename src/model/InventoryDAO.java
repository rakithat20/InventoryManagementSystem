package src.model;

import src.model.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryDAO {
    private final SupplierDAO supplierDAO = new SupplierDAO();

    public boolean addItem(InventoryItem item) {
        String sql = "INSERT INTO inventory_items (item_name, item_code, quantity, price_per_unit, supplier_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, item.getItemName());
            stmt.setString(2, item.getItemCode());
            stmt.setInt(3, item.getQuantity());
            stmt.setDouble(4, item.getPricePerUnit());
            stmt.setInt(5, item.getSupplier());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<InventoryItem> getAllItems() {
        List<InventoryItem> itemList = new ArrayList<>();
        String sql = "SELECT * FROM inventory_items";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Supplier supplier = supplierDAO.getSupplierById(rs.getInt("supplier_id"));
                itemList.add(new InventoryItem(
                        rs.getInt("id"),
                        rs.getString("item_name"),
                        rs.getString("item_code"),
                        rs.getInt("quantity"),
                        rs.getDouble("price_per_unit"),
                        supplier.getId()  // Corrected this line, use 'supplier' directly without casting
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return itemList;
    }

    public boolean updateItem(InventoryItem item) {
        System.out.println(item.getQuantity());
        System.out.println(item.getId());
        System.out.println(item.getItemName());
        String sql = "UPDATE inventory_items SET item_name = ?, item_code = ?, quantity = ?, price_per_unit = ?, supplier_id = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, item.getItemName());
            stmt.setString(2, item.getItemCode());
            stmt.setInt(3, item.getQuantity());
            stmt.setDouble(4, item.getPricePerUnit());
            stmt.setInt(5, item.getSupplier());
            stmt.setInt(6, item.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteItem(int id) {
        String sql = "DELETE FROM inventory_items WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public InventoryItem getItemById(int id) {
        String sql = "SELECT * FROM inventory_items WHERE id = ?";
        InventoryItem inventoryItem = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id); // ✅ Set the ID parameter
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) { // ✅ Move the cursor to the first result
                inventoryItem = new InventoryItem(
                        rs.getInt("id"),
                        rs.getString("item_name"),
                        rs.getString("item_code"),
                        rs.getInt("quantity"),
                        rs.getDouble("price_per_unit"),
                        rs.getInt("supplier_id") // ✅ Assuming supplier_id is an integer
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return inventoryItem; // ✅ Return the found item or null
    }



}
