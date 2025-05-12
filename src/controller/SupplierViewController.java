package src.controller;

import src.model.Supplier;
import src.view.SupplierFormView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SupplierViewController {

    private SupplierController supplierController;
    private SupplierFormView view;

    public SupplierViewController(SupplierFormView view) {
        this.view = view;
        this.supplierController = new SupplierController();

        initController();
        loadSupplierData();
    }

    private void initController() {

        view.getSupplierTable().getSelectionModel().addListSelectionListener(e -> fillFormFromTable());
    }

    private void loadSupplierData() {
        List<Supplier> suppliers = supplierController.getAllSuppliers();
        view.updateTableData(suppliers);
    }





    private void fillFormFromTable() {
        Supplier selected = view.getSelectedSupplierFromTable();
        if (selected != null) {
            view.fillForm(selected);
        }
    }
}
