package src.controller;

import src.model.InventoryDAO;
import src.model.InventoryItem;
import src.model.Supplier;
import src.model.SupplierDAO;
import src.view.AddEditItemView;
import src.view.InventoryDashboard;
import src.view.LoginView;
import src.view.SupplierFormView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class InventoryDashboardController {
    private final InventoryDashboard dashboardView;
    private final InventoryDAO inventoryDAO;
    private final SupplierDAO supplierDAO;

    public InventoryDashboardController() {
        this.inventoryDAO = new InventoryDAO();
        this.supplierDAO = new SupplierDAO();
        dashboardView = new InventoryDashboard();
        dashboardView.setVisible(true);

        // Load initial data
        loadInventoryItems();

        // Add action listeners
        dashboardView.addAddItemListener(new AddItemListener());
        dashboardView.addEditItemListener(new EditItemListener());
        dashboardView.addDeleteItemListener(new DeleteItemListener());
        dashboardView.addViewSuppliersListener(new ViewSuppliersListener());
        dashboardView.addLogoutListener(new LogoutListener());
        dashboardView.addSearchListener(new SearchListener());
    }

    private void loadInventoryItems() {
        List<InventoryItem> items = inventoryDAO.getAllItems();
        Object[][] tableData = new Object[items.size()][6];

        for (int i = 0; i < items.size(); i++) {
            InventoryItem item = items.get(i);
            Supplier supplier = supplierDAO.getSupplierById(item.getSupplier());

            tableData[i][0] = item.getId();
            tableData[i][1] = item.getItemName();
            tableData[i][2] = item.getItemCode();
            tableData[i][3] = item.getQuantity();
            tableData[i][4] = item.getPricePerUnit();
            tableData[i][5] = supplier != null ? supplier.getName() : "Unknown";
        }

        dashboardView.updateTableData(tableData);
    }

    class AddItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            AddEditItemView addView = new AddEditItemView(null);
            addView.setVisible(true);

            addView.addSaveListener(ev -> {
                try {
                    String name = addView.getItemName();
                    String code = addView.getItemCode();
                    int quantity = Integer.parseInt(addView.getQuantity());
                    double price = Double.parseDouble(addView.getPricePerUnit());
                    int supplierId = Integer.parseInt(addView.getSupplierId());

                    InventoryItem item = new InventoryItem(0, name, code, quantity, price, supplierId);
                    boolean success = inventoryDAO.addItem(item);

                    if (success) {
                        dashboardView.showMessage("Item added successfully!");
                        addView.dispose();
                        loadInventoryItems();
                    } else {
                        dashboardView.showMessage("Failed to add item.");
                    }
                } catch (Exception ex) {
                    dashboardView.showMessage("Invalid input: " + ex.getMessage());
                }
            });
        }
    }

    class EditItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = dashboardView.getItemTable().getSelectedRow();
            if (selectedRow == -1) {
                dashboardView.showMessage("Please select an item to edit.");
                return;
            }

            int id = (int) dashboardView.getItemTable().getValueAt(selectedRow, 0);
            InventoryItem item = inventoryDAO.getItemById(id);

            if (item == null) {
                dashboardView.showMessage("Selected item not found.");
                return;
            }

            AddEditItemView editView = new AddEditItemView(item);
            editView.setVisible(true);

            editView.addSaveListener(ev -> {
                try {
                    item.setItemName(editView.getItemName());
                    item.setItemCode(editView.getItemCode());
                    item.setQuantity(Integer.parseInt(editView.getQuantity()));
                    item.setPricePerUnit(Double.parseDouble(editView.getPricePerUnit()));
                    item.setSupplier(Integer.parseInt(editView.getSupplierId()));

                    boolean success = inventoryDAO.updateItem(item);

                    if (success) {
                        dashboardView.showMessage("Item updated successfully.");
                        editView.dispose();
                        loadInventoryItems();
                    } else {
                        dashboardView.showMessage("Failed to update item.");
                    }
                } catch (Exception ex) {
                    dashboardView.showMessage("Invalid input: " + ex.getMessage());
                }
            });
        }
    }

    class DeleteItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = dashboardView.getItemTable().getSelectedRow();
            if (selectedRow == -1) {
                dashboardView.showMessage("Please select an item to delete.");
                return;
            }

            int id = (int) dashboardView.getItemTable().getValueAt(selectedRow, 0);
            int confirm = JOptionPane.showConfirmDialog(dashboardView, "Are you sure you want to delete this item?", "Confirm Delete", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                boolean success = inventoryDAO.deleteItem(id);

                if (success) {
                    dashboardView.showMessage("Item deleted successfully.");
                    loadInventoryItems();
                } else {
                    dashboardView.showMessage("Failed to delete item.");
                }
            }
        }
    }

    class ViewSuppliersListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dashboardView.showMessage("View Suppliers clicked — implement SupplierFormView here.");
            // You can open SupplierFormView here, e.g.:
            SupplierFormView view = new SupplierFormView();
            new SupplierViewController(view);
        }
    }

    class LogoutListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dashboardView.dispose();
            new LoginView().setVisible(true);
        }
    }

    class SearchListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String searchTerm = dashboardView.getSearchText().toLowerCase();
            List<InventoryItem> items = inventoryDAO.getAllItems();

            Object[][] filteredData = items.stream()
                    .filter(item -> item.getItemName().toLowerCase().contains(searchTerm) ||
                            item.getItemCode().toLowerCase().contains(searchTerm))
                    .map(item -> {
                        Supplier supplier = supplierDAO.getSupplierById(item.getSupplier());
                        return new Object[]{
                                item.getId(),
                                item.getItemName(),
                                item.getItemCode(),
                                item.getQuantity(),
                                item.getPricePerUnit(),
                                supplier != null ? supplier.getName() : "Unknown"
                        };
                    })
                    .toArray(Object[][]::new);

            dashboardView.updateTableData(filteredData);
        }
    }
}
