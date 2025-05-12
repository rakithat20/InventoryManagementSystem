package src.view;

import src.model.InventoryItem;
import src.model.Supplier;
import src.model.SupplierDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AddEditItemView extends JFrame {
    private final JTextField itemNameField;
    private final JTextField itemCodeField;
    private final JTextField quantityField;
    private final JTextField priceField;
    private final JTextField supplierIdField;
    private final JButton saveButton;

    public AddEditItemView(InventoryItem item) {

        if(item != null ){
            setTitle("Edit Item");

        }else {
            setTitle("Add New Item");
        }

        setSize(400, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Item Name
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Item Name:"), gbc);
        gbc.gridx = 1;
        itemNameField = new JTextField(20);

        add(itemNameField, gbc);

        // Item Code
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Item Code:"), gbc);
        gbc.gridx = 1;
        itemCodeField = new JTextField(20);
        add(itemCodeField, gbc);

        // Quantity
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Quantity:"), gbc);
        gbc.gridx = 1;
        quantityField = new JTextField(20);
        add(quantityField, gbc);

        // Price Per Unit
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Price Per Unit:"), gbc);
        gbc.gridx = 1;
        priceField = new JTextField(20);
        add(priceField, gbc);

        // Supplier ID
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("Supplier ID:"), gbc);
        gbc.gridx = 1;
        supplierIdField = new JTextField(20);
        add(supplierIdField, gbc);

        // Save Button
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        saveButton = new JButton("Save");
        add(saveButton, gbc);
        if (item != null){
            SupplierDAO dao = new SupplierDAO();
            Supplier supplier = dao.getSupplierById(item.getSupplier());

            setItemName(item.getItemName());
            setItemCodeField(item.getItemCode());
            setPriceField(Double.toString(item.getPricePerUnit()));
            setQuantityField(Integer.toString(item.getQuantity()));
            setSupplierIdField(Integer.toString(item.getSupplier()));
        }
    }
    public void setItemName(String name){
        itemNameField.setText(name);
    }
    public void setQuantityField(String quantity){
        quantityField.setText(quantity);
    }
    public void setItemCodeField(String itemCode){
        itemCodeField.setText(itemCode);
    }
    public void setPriceField(String price){
        priceField.setText(price);
    }
    public void setSupplierIdField(String supplierId){
        supplierIdField.setText(supplierId);
    }

    // Accessor methods for controller
    public String getItemName() {
        return itemNameField.getText().trim();
    }

    public String getItemCode() {
        return itemCodeField.getText().trim();
    }

    public String getQuantity() {
        return quantityField.getText().trim();
    }

    public String getPricePerUnit() {
        return priceField.getText().trim();
    }

    public String getSupplierId() {
        return supplierIdField.getText().trim();
    }

    public void addSaveListener(ActionListener listener) {
        saveButton.addActionListener(listener);
    }
}
