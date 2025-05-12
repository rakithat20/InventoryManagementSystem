package src.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class RegisterView extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton registerButton;
    private JButton backButton;
    private JLabel errorLabel;

    private Color primaryColor = new Color(33, 150, 243); // Blue
    private Color textColor = Color.WHITE;

    public RegisterView() {
        setTitle("Register");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Title
        JLabel titleLabel = new JLabel("Register New Account", JLabel.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(titleLabel, BorderLayout.NORTH);

        // Form panel with GridBagLayout
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
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

        // Error Label
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        errorLabel = new JLabel("", JLabel.CENTER);
        errorLabel.setForeground(Color.RED);
        formPanel.add(errorLabel, gbc);

        // Buttons
        gbc.gridy = 3;
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        registerButton = createStyledButton("Register");
        backButton = createStyledButton("Back");
        buttonPanel.add(registerButton);
        buttonPanel.add(backButton);
        formPanel.add(buttonPanel, gbc);

        add(formPanel, BorderLayout.CENTER);
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

    // Public methods for controller
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
    }

    public void addRegisterListener(ActionListener listener) {
        registerButton.addActionListener(listener);
    }

    public void addBackListener(ActionListener listener) {
        backButton.addActionListener(listener);
    }
}
