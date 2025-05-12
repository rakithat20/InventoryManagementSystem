package src.view;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class InventoryDashboard extends JFrame {

    private JTable itemTable;
    private JButton addItemButton, editItemButton, deleteItemButton, viewSuppliersButton, logoutButton, searchButton;
    private JTextField searchField;

    private final Color primaryColor = new Color(33, 150, 243); // Blue
    private final Color lightGray = new Color(245, 245, 245);
    private final Color buttonTextColor = Color.WHITE;

    private JPanel dashboardCards;
    private JLabel totalItemsValue;
    private JLabel lowStockValue;
    private JLabel categoriesValue;
    private JLabel suppliersValue;


    public InventoryDashboard() {
        setTitle("📦 Inventory Dashboard");
        setSize(1100, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(lightGray);

        Font titleFont = new Font("Segoe UI Emoji", Font.BOLD, 24);
        Font subtitleFont = new Font("Segoe UI Emoji", Font.PLAIN, 16);
        Font labelFont = new Font("Segoe UI Emoji" +
                "", Font.BOLD, 14);

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(new EmptyBorder(20, 20, 10, 20));

        JLabel title = new JLabel("📦 Welcome to Inventory Manager");
        title.setFont(titleFont);
        JLabel subtitle = new JLabel("Your command center for stock and supplier insights.");
        subtitle.setFont(subtitleFont);
        subtitle.setForeground(Color.DARK_GRAY);

        headerPanel.add(title);
        headerPanel.add(Box.createVerticalStrut(5));
        headerPanel.add(subtitle);

        add(headerPanel, BorderLayout.NORTH);

        // Dashboard Cards
        dashboardCards = new JPanel(new GridLayout(1, 4, 15, 15));
        dashboardCards.setBorder(new EmptyBorder(15, 15, 15, 15));
        dashboardCards.setBackground(lightGray);

        totalItemsValue = new JLabel("0");
        lowStockValue = new JLabel("0");
        suppliersValue = new JLabel("0");

        dashboardCards.add(createDashboardCard("📦 Total Items", totalItemsValue));
        dashboardCards.add(createDashboardCard("⚠️ Low Stock", lowStockValue));
        dashboardCards.add(createDashboardCard("🤝 Suppliers", suppliersValue));


//        dashboardCards.add(createDashboardCard("📦 Total Items", "218"));
//        dashboardCards.add(createDashboardCard("⚠️ Low Stock", "12"));
//        dashboardCards.add(createDashboardCard("📂 Categories", "8"));
//        dashboardCards.add(createDashboardCard("🤝 Suppliers", "125"));

        // Center Table
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBorder(new EmptyBorder(0, 10, 10, 10));
        centerPanel.setBackground(lightGray);

        itemTable = new JTable(new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID", "Name", "Category", "Quantity", "Price", "Supplier"}
        ));
        itemTable.setRowHeight(24);
        itemTable.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 13));
        itemTable.getTableHeader().setFont(labelFont);
        itemTable.getTableHeader().setBackground(primaryColor);
        itemTable.getTableHeader().setForeground(Color.WHITE);

        JScrollPane tableScrollPane = new JScrollPane(itemTable);
        centerPanel.add(tableScrollPane, BorderLayout.CENTER);

        // Bottom Panel (Search + Buttons)
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.setBorder(new EmptyBorder(10, 15, 10, 15));

        // Search
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBackground(Color.WHITE);

        JLabel searchLabel = new JLabel("Search:");
        searchLabel.setFont(labelFont);

        searchField = new JTextField(20);
        searchField.setBorder(new CompoundBorder(
                new LineBorder(new Color(200, 200, 200), 1, true),
                new EmptyBorder(5, 10, 5, 10)
        ));

        searchButton = createStyledButton("🔍 Go");

        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        // Action Buttons
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        actionPanel.setBackground(Color.WHITE);
        addItemButton = createStyledButton("➕ Add");
        editItemButton = createStyledButton("✏️ Edit");
        deleteItemButton = createStyledButton("🗑️ Delete");
        viewSuppliersButton = createStyledButton("📋 Suppliers");
        logoutButton = createStyledButton("🚪 Logout");

        actionPanel.add(addItemButton);
        actionPanel.add(editItemButton);
        actionPanel.add(deleteItemButton);
        actionPanel.add(viewSuppliersButton);
        actionPanel.add(logoutButton);

        bottomPanel.add(searchPanel, BorderLayout.WEST);
        bottomPanel.add(actionPanel, BorderLayout.EAST);

        // Final Layout
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(lightGray);
        contentPanel.add(dashboardCards, BorderLayout.NORTH);
        contentPanel.add(centerPanel, BorderLayout.CENTER);
        contentPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(contentPanel, BorderLayout.CENTER);
    }

    // ✅ Corrected createDashboardCard method (removed recursion)
    private JPanel createDashboardCard(String title, JLabel valueLabel) {
        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(100, 100));
        card.setLayout(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(220, 220, 220), 1, true),
                new EmptyBorder(15, 15, 15, 15)
        ));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI Emoji", Font.BOLD, 14));
        titleLabel.setForeground(new Color(100, 100, 100));

        valueLabel.setFont(new Font("Segoe UI Emoji", Font.BOLD, 26));
        valueLabel.setForeground(primaryColor);

        card.add(titleLabel, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.CENTER);

        return card;
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

    // Getters
    public String getSearchText() {
        return searchField.getText().trim();
    }

    public JTable getItemTable() {
        return itemTable;
    }

    // Listeners
    public void addAddItemListener(ActionListener listener) {
        addItemButton.addActionListener(listener);
    }

    public void addEditItemListener(ActionListener listener) {
        editItemButton.addActionListener(listener);
    }

    public void addDeleteItemListener(ActionListener listener) {
        deleteItemButton.addActionListener(listener);
    }

    public void addViewSuppliersListener(ActionListener listener) {
        viewSuppliersButton.addActionListener(listener);
    }

    public void addLogoutListener(ActionListener listener) {
        logoutButton.addActionListener(listener);
    }

    public void addSearchListener(ActionListener listener) {
        searchButton.addActionListener(listener);
    }

    public void updateTableData(Object[][] data) {
        DefaultTableModel model = (DefaultTableModel) itemTable.getModel();
        model.setRowCount(0); // Clear existing rows
        for (Object[] row : data) {
            model.addRow(row);
        }
    }

    public int getSelectedItemId() {
        int selectedRow = itemTable.getSelectedRow();
        if (selectedRow != -1) {
            Object idValue = itemTable.getValueAt(selectedRow, 0);
            return Integer.parseInt(idValue.toString());
        } else {
            return -1;
        }
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
    public void updateTotalItems(int count) {
        totalItemsValue.setText(String.valueOf(count));
    }

    public void updateLowStock(int count) {
        lowStockValue.setText(String.valueOf(count));
    }

    public void updateCategories(int count) {
        categoriesValue.setText(String.valueOf(count));
    }

    public void updateSuppliers(int count) {
        suppliersValue.setText(String.valueOf(count));
    }

}
