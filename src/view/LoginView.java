package src.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private JLabel errorLabel;

    private Color primaryColor = new Color(33, 150, 243); // Blue
    private Color textColor = Color.WHITE;

    public LoginView() {
        setTitle("Login - Inventory Manager");
        setSize(400, 320);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Header with welcoming message
        JLabel titleLabel = new JLabel("Welcome to Inventory Manager", JLabel.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        add(titleLabel, BorderLayout.NORTH);

        // Center panel with form fields
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 10, 12, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Username
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Username:"), gbc);

        gbc.gridx = 1;
        usernameField = new JTextField(15);
        formPanel.add(usernameField, gbc);

        // Password
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        passwordField = new JPasswordField(15);
        formPanel.add(passwordField, gbc);

        // Error message
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        errorLabel = new JLabel("", JLabel.CENTER);
        errorLabel.setForeground(Color.RED);
        errorLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        formPanel.add(errorLabel, gbc);

        // Buttons
        gbc.gridy = 3;
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        loginButton = createStyledButton("Login");
        registerButton = createStyledButton("Register");
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);
        formPanel.add(buttonPanel, gbc);

        add(formPanel, BorderLayout.CENTER);

        // Improve UX
        usernameField.setToolTipText("Enter your username");
        passwordField.setToolTipText("Enter your password");

        usernameField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                usernameField.selectAll();
            }
        });

        passwordField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                passwordField.selectAll();
            }
        });
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setBackground(primaryColor);
        button.setForeground(textColor);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(120, 35));
        return button;
    }

    // Public methods
    public String getUsername() {
        return usernameField.getText().trim();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public void showError(String message) {
        errorLabel.setText(message);
    }

    public void clearFields() {
        usernameField.setText("");
        passwordField.setText("");
        errorLabel.setText(""); // Clear any error
    }

    public void addLoginListener(ActionListener listener) {
        loginButton.addActionListener(listener);
    }

    public void addRegisterListener(ActionListener listener) {
        registerButton.addActionListener(listener);
    }
}
