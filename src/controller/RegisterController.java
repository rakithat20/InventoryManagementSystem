package src.controller;

import src.model.User;
import src.view.LoginView;
import src.view.RegisterView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterController {
    private RegisterView registerView;
    private AuthController authController;

    public RegisterController() {
        this.registerView = new RegisterView();
        this.authController = new AuthController();

        this.registerView.addRegisterListener(new RegisterListener());
        this.registerView.addBackListener(new BackListener());

        registerView.setVisible(true);
    }

    class RegisterListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = registerView.getUsername();
            String password = registerView.getPassword();

            if (username.isEmpty() || password.isEmpty()) {
                registerView.showError("Please fill in all fields.");
                return;
            }

            boolean success = authController.register(username, password);
            if (success) {
                JOptionPane.showMessageDialog(registerView, "Registration successful. Please log in.");
                registerView.dispose();
                new LoginController(); // Redirect to login
            } else {
                registerView.showError("Username already taken or registration failed.");
            }
        }
    }

    class BackListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            registerView.dispose();
            new LoginController(); // Back to login
        }
    }
}
