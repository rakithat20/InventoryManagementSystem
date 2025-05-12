package src.model;

import java.util.function.Supplier;

public class InventoryItem {
    private int id;
    private String itemName;
    private String itemCode;
    private int quantity;
    private double pricePerUnit;
    private int supplier;

    public InventoryItem(int id, String itemName, String itemCode, int quantity, double pricePerUnit, int supplier) {
        this.id = id;
        this.itemName = itemName;
        this.itemCode = itemCode;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
        this.supplier = supplier;
    }

    public InventoryItem(String itemName, String itemCode, int quantity, double pricePerUnit, int supplier) {
        this.itemName = itemName;
        this.itemCode = itemCode;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
        this.supplier = supplier;
    }

    public int getId() {
        return id;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemCode() {
        return itemCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public int getSupplier() {
        return supplier;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode ;
    }

    public void setQuantity(int quantity) {

        this.quantity = quantity;
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public void setSupplier(int supplier) {
        this.supplier = supplier;
    }
}
