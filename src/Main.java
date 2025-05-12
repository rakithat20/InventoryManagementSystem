package src;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import src.controller.AuthController;
import src.controller.InventoryController;
import src.controller.LoginController;
import src.model.UserDAO;
import src.model.*;
import src.view.InventoryDashboard;
import src.view.LoginView;

import javax.swing.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        //Load Login view
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            UIManager.put("Button.arc", 10);  // Rounded buttons
            UIManager.put("Component.arc", 10);
            UIManager.put("TextComponent.arc", 10); // Rounded search field
        } catch (Exception ex) {
            System.err.println("Failed to initialize FlatLaf");
        }

        SwingUtilities.invokeLater(() -> {
            new LoginController();
        });



    }
}
