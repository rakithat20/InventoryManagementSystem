package src.controller;

import src.view.LoginView;
import src.model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {
    private LoginView loginView;
    private AuthController authController;

    public LoginController() {
        this.loginView = new LoginView();
        this.authController = new AuthController();

        // Set listeners
        this.loginView.addLoginListener(new LoginListener());
        this.loginView.addRegisterListener(new RegisterListener());

        // Show the login UI
        this.loginView.setVisible(true);
    }

    class LoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = loginView.getUsername();
            String password = loginView.getPassword();

            if (username.isEmpty() || password.isEmpty()) {
                loginView.showError("Please fill in both fields.");
                return;
            }

            User user = authController.login(username, password);
            if (user != null) {
                loginView.showError("");  // Clear any error
                // Login successful – open dashboard or next view
                JOptionPane.showMessageDialog(loginView, "Login successful! Welcome, " + user.getUsername());
                loginView.dispose(); // Optionally close the login window
                new InventoryDashboardController();
            } else {
                loginView.showError("Invalid username or password.");
            }
        }
    }

    class RegisterListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            loginView.dispose();
            new RegisterController();
        }
    }
}
