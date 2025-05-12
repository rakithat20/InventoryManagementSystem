package src.controller;

import src.model.InventoryItem;
import src.model.InventoryDAO;

import java.util.ArrayList;
import java.util.List;

public class InventoryController {

    private InventoryDAO inventoryDAO;

    public InventoryController() {
        this.inventoryDAO = new InventoryDAO();
    }

    public List<InventoryItem> getAllItems() {

       List<InventoryItem> inventoryItems = inventoryDAO.getAllItems();
       inventoryItems.forEach(inventoryItem -> System.out.println(inventoryItem.getItemName()));
       return  inventoryItems;
    }

    public boolean addItem(InventoryItem item) {
        return inventoryDAO.addItem(item);
    }

    public boolean updateItem(InventoryItem item) {
        return inventoryDAO.updateItem(item);
    }

    public boolean deleteItem(int id) {
        return inventoryDAO.deleteItem(id);
    }

    public int getTotalItems(){
        return inventoryDAO.getAllItems().size();
    }
    public int getLowStocks(){
        int lowStockProducts = 0;
        List<InventoryItem> itemList = inventoryDAO.getAllItems();
        for(InventoryItem item : itemList){
            if(item.getQuantity()<10){
                lowStockProducts++;
            }
        }
        return lowStockProducts;
    }
    // TODO: Connect with inventory UI (table view, form inputs, etc.)
}
