package src.view;

import src.model.Supplier;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class SupplierFormView extends JFrame {

    private final Color primaryColor = new Color(33, 150, 243);
    private final Color lightGray = new Color(245, 245, 245);
    private final Color buttonTextColor = Color.WHITE;
    private final Font labelFont = new Font("Segoe UI Emoji", Font.BOLD, 14);
    private final Font titleFont = new Font("Segoe UI Emoji", Font.BOLD, 22);
    private final Font tableFont = new Font("Segoe UI Emoji", Font.PLAIN, 13);

    private JButton addButton, updateButton, deleteButton;
    private JTable supplierTable;
    private DefaultTableModel tableModel;

    public SupplierFormView() {
        setTitle("🤝 Supplier Management");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(lightGray);

        initHeader();
        initTable();
        initButtonPanel();

        setVisible(true);
    }

    private void initHeader() {
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(new EmptyBorder(20, 20, 10, 20));

        JLabel title = new JLabel("🤝 Supplier Directory");
        title.setFont(titleFont);

        JLabel subtitle = new JLabel("Manage supplier records with ease.");
        subtitle.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 16));
        subtitle.setForeground(Color.DARK_GRAY);

        headerPanel.add(title);
        headerPanel.add(Box.createVerticalStrut(5));
        headerPanel.add(subtitle);

        add(headerPanel, BorderLayout.NORTH);
    }

    private void initTable() {
        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Email", "Phone", "Address"}, 0);
        supplierTable = new JTable(tableModel);
        supplierTable.setRowHeight(24);
        supplierTable.setFont(tableFont);
        supplierTable.getTableHeader().setFont(labelFont);
        supplierTable.getTableHeader().setBackground(primaryColor);
        supplierTable.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(supplierTable);
        scrollPane.setBorder(new EmptyBorder(10, 20, 10, 20));
        add(scrollPane, BorderLayout.CENTER);
    }

    private void initButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        addButton = createStyledButton("➕ Add");
        updateButton = createStyledButton("✏️ Update");
        deleteButton = createStyledButton("🗑️ Delete");

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI Emoji", Font.BOLD, 13));
        button.setMargin(new Insets(8, 15, 8, 15));
        button.setForeground(buttonTextColor);
        button.setBackground(primaryColor);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    public void updateTableData(List<Supplier> suppliers) {
        tableModel.setRowCount(0);
        for (Supplier s : suppliers) {
            tableModel.addRow(new Object[]{
                    s.getId(),
                    s.getName(),
                    s.getEmail(),
                    s.getContactNumber(),
                    s.getAddress()
            });
        }
    }

    public int getSelectedSupplierId() {
        int selectedRow = supplierTable.getSelectedRow();
        if (selectedRow >= 0) {
            return (int) tableModel.getValueAt(selectedRow, 0);
        }
        return -1;
    }

    public Supplier getSelectedSupplierFromTable() {
        int selectedRow = supplierTable.getSelectedRow();
        if (selectedRow >= 0) {
            Supplier supplier = new Supplier(
                    (String) tableModel.getValueAt(selectedRow, 1),
                    (String) tableModel.getValueAt(selectedRow, 3),
                    (String) tableModel.getValueAt(selectedRow, 2),
                    (String) tableModel.getValueAt(selectedRow, 4)
            );
            supplier.setId((int) tableModel.getValueAt(selectedRow, 0));
            return supplier;
        }
        return null;
    }

    // Getters
    public JButton getAddButton() { return addButton; }
    public JButton getUpdateButton() { return updateButton; }
    public JButton getDeleteButton() { return deleteButton; }
    public JTable getSupplierTable() { return supplierTable; }

    public void fillForm(Supplier selected) {
        // You can implement this later for a form view if needed
    }
}
