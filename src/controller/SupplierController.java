package src.controller;

import src.model.Supplier;
import src.model.SupplierDAO;

import java.util.List;

public class SupplierController {

    private SupplierDAO supplierDAO;

    public SupplierController() {
        this.supplierDAO = new SupplierDAO();
    }

    public List<Supplier> getAllSuppliers() {
        return supplierDAO.getAllSuppliers();
    }

    public int addSupplier(Supplier supplier) {
        return supplierDAO.addSupplier(supplier);
    }

    public boolean updateSupplier(Supplier supplier) {
        return supplierDAO.updateSupplier(supplier);
    }

    public boolean deleteSupplier(int id) {
        return supplierDAO.deleteSupplier(id);
    }

    // TODO: Connect with supplier UI (form, table, etc.)
}
