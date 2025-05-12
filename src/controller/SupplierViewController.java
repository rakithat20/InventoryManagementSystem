package src.controller;

import src.model.Supplier;
import src.model.SupplierDAO;
import src.view.AddEditSupplierView;
import src.view.SupplierFormView;

import javax.swing.*;
import java.util.List;

public class SupplierViewController {
    private final SupplierFormView view;
    private final SupplierDAO supplierDAO;

    public SupplierViewController(SupplierFormView view) {
        this.view = view;
        this.supplierDAO = new SupplierDAO();

        loadSupplierData();

        view.getAddButton().addActionListener(e -> openAddSupplierDialog());
        view.getUpdateButton().addActionListener(e -> openUpdateSupplierDialog());
        view.getDeleteButton().addActionListener(e -> deleteSupplier());
//        view.get().addActionListener(e -> view.dispose());  // optional
    }

    private void loadSupplierData() {
        List<Supplier> suppliers = supplierDAO.getAllSuppliers();
        view.updateTableData(suppliers);
    }

    private void openAddSupplierDialog() {
        AddEditSupplierView addView = new AddEditSupplierView(null);
        addView.addSaveListener(e -> {
            Supplier supplier = new Supplier(
                    addView.getName(),
                    addView.getContact(),
                    addView.getEmail(),
                    addView.getAddress()
            );

            int result = supplierDAO.addSupplier(supplier);
            if (result > 0) {
                JOptionPane.showMessageDialog(view, "Supplier added successfully!");
                addView.dispose();
                loadSupplierData();
            } else {
                JOptionPane.showMessageDialog(view, "Failed to add supplier.");
            }
        });

        addView.setVisible(true);
    }

    private void openUpdateSupplierDialog() {
        Supplier selected = view.getSelectedSupplierFromTable();
        if (selected == null) {
            JOptionPane.showMessageDialog(view, "Please select a supplier to update.");
            return;
        }

        AddEditSupplierView editView = new AddEditSupplierView(selected);
        editView.addSaveListener(e -> {
            selected.setName(editView.getName());
            selected.setPhone(editView.getContact());
            selected.setEmail(editView.getEmail());
            selected.setAddress(editView.getAddress());

            boolean success = supplierDAO.updateSupplier(selected);
            if (success) {
                JOptionPane.showMessageDialog(view, "Supplier updated successfully!");
                editView.dispose();
                loadSupplierData();
            } else {
                JOptionPane.showMessageDialog(view, "Failed to update supplier.");
            }
        });

        editView.setVisible(true);
    }

    private void deleteSupplier() {
        Supplier selected = view.getSelectedSupplierFromTable();
        if (selected == null) {
            JOptionPane.showMessageDialog(view, "Please select a supplier to delete.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(view, "Are you sure you want to delete this supplier?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {

            boolean success = supplierDAO.deleteSupplier(selected.getId());
            if (success) {
                JOptionPane.showMessageDialog(view, "Supplier deleted successfully.");
                loadSupplierData();
            } else {
                JOptionPane.showMessageDialog(view, "Failed to delete supplier.");
            }
        }
    }
}
