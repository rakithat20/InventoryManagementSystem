package src.view;

import src.model.Supplier;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AddEditSupplierView extends JFrame {
    private final JTextField supplierIdField;
    private final JTextField nameField;
    private final JTextField contactField;
    private final JTextField emailField;
    private final JTextField addressField;
    private final JButton saveButton;

    public AddEditSupplierView(Supplier supplier) {
        if (supplier != null) {
            setTitle("Edit Supplier");
        } else {
            setTitle("Add New Supplier");
        }

        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Supplier ID
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Supplier ID:"), gbc);
        gbc.gridx = 1;
        supplierIdField = new JTextField(20);
        add(supplierIdField, gbc);
        supplierIdField.setEditable(false); // Typically, the ID is auto-generated or uneditable

        // Supplier Name
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        nameField = new JTextField(20);
        add(nameField, gbc);

        // Contact
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Contact:"), gbc);
        gbc.gridx = 1;
        contactField = new JTextField(20);
        add(contactField, gbc);

        // Email
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        emailField = new JTextField(20);
        add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("Address:"), gbc);
        gbc.gridx = 1;
        addressField = new JTextField(20);
        add(addressField, gbc);

        // Save Button
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        saveButton = new JButton("Save");
        add(saveButton, gbc);

        if (supplier != null) {
            setSupplierId(Integer.toString(supplier.getId()));
            setName(supplier.getName());
            setContact(supplier.getContactNumber());
            setEmail(supplier.getEmail());
            setAddressField(supplier.getAddress());
        }
    }

    // Setter methods
    public void setSupplierId(String id) {
        supplierIdField.setText(id);
    }

    public void setName(String name) {
        nameField.setText(name);
    }

    public void setContact(String contact) {
        contactField.setText(contact);
    }

    public void setEmail(String email) {
        emailField.setText(email);
    }
    public void  setAddressField(String address){
        addressField.setText(address);
    }
    // Getter methods for controller
    public String getSupplierId() {
        return supplierIdField.getText().trim();
    }

    public String getName() {
        return nameField.getText().trim();
    }

    public String getContact() {
        return contactField.getText().trim();
    }

    public String getEmail() {
        return emailField.getText().trim();
    }
    public String getAddress(){
        return  addressField.getText().trim();
    }

    public void addSaveListener(ActionListener listener) {
        saveButton.addActionListener(listener);
    }
}
