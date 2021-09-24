package View;

import javax.swing.*;
import java.awt.*;

/**
 * This class is to create GUI components for login dialog.
 * The login dialog extends this class to edit the components directly.
 * @author 18206141 Gong Chenhan
 */
public class LoginView {

    protected JFrame loginFrame;
    protected JPanel loginPanel;
    protected JButton loginButton, registerButton;
    protected JLabel idLabel, passwordLabel;
    protected JTextField idField;
    protected JPasswordField passwordField;

    public LoginView() {
        loginFrame = new JFrame("Log In");
        loginFrame.setSize(600, 400);
        loginFrame.setResizable(false);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        init();
    }

    public void init() {
        idLabel = new JLabel("ID: ");
        passwordLabel = new JLabel("Password: ");
        idField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("LogIn");
        registerButton = new JButton("Register");

        loginPanel = new JPanel(null);
        idLabel.setBounds(50, 75, 100, 50);
        idField.setBounds(150, 75, 400, 50);
        idLabel.setFont(new Font("Arial", Font.BOLD, 16));
        idField.setFont(new Font("Arial", Font.PLAIN, 20));
        passwordLabel.setBounds(50, 150, 100, 50);
        passwordField.setBounds(150, 150, 400, 50);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 16));
        passwordField.setFont(new Font("Arial", Font.PLAIN, 20));
        loginButton.setBounds(125, 250, 150, 50);
        registerButton.setBounds(325, 250, 150,50);
        loginPanel.add(idLabel);
        loginPanel.add(idField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);
        loginPanel.add(registerButton);

        loginFrame.add(loginPanel);
        loginFrame.setVisible(true);
    }

    /**
     * This method is to close the frame
     */
    public void destroy() {
        loginFrame.dispose();
    }

    /**
     * This method is to display error message
     * @param msg error message
     */
    public void showErrorMessage(String msg) {
        JOptionPane.showMessageDialog(loginFrame, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

}
