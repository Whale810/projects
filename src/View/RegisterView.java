package View;

import javax.swing.*;
import java.awt.*;

/**
 * This class is to create GUI components for register dialog.
 * The register dialog extends this class to edit the components directly.
 * @author 18206138 Jin Chenzhe
 */
public class RegisterView {

    protected JFrame RegisterFrame;
    protected JPanel RegisterPanel;
    protected JButton confirmButton;
    protected JButton returnButton;
    protected JLabel idLabel, passwordLabel, repassLabel;
    protected JTextField idField;
    protected JPasswordField passwordField, repassField;

    public RegisterView() {
        RegisterFrame = new JFrame("Register");
        RegisterFrame.setSize(600, 400);
        RegisterFrame.setResizable(false);
        RegisterFrame.setLocationRelativeTo(null);
        RegisterFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        init();
    }

    public void init() {

        returnButton = new JButton("Back");
        confirmButton = new JButton("Confirm");
        idLabel = new JLabel("ID: ");
        passwordLabel = new JLabel("Password: ");
        repassLabel = new JLabel("Reconfirm: ");
        idField = new JTextField();
        passwordField = new JPasswordField();
        repassField = new JPasswordField();

        RegisterPanel = new JPanel(null);
        idLabel.setBounds(50, 50, 100, 50);
        idField.setBounds(150, 50, 400, 50);
        idLabel.setFont(new Font("Arial", Font.BOLD, 16));
        idField.setFont(new Font("Arial", Font.PLAIN, 20));
        passwordLabel.setBounds(50, 125, 100, 50);
        passwordField.setBounds(150, 125, 400, 50);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 16));
        passwordField.setFont(new Font("Arial", Font.PLAIN, 20));
        repassLabel.setBounds(50, 200, 100, 50);
        repassField.setBounds(150, 200, 400, 50);
        repassLabel.setFont(new Font("Arial", Font.BOLD, 16));
        repassField.setFont(new Font("Arial", Font.PLAIN, 20));
        returnButton.setBounds(125, 275, 150, 50);
        confirmButton.setBounds(325, 275, 150, 50);
        RegisterPanel.add(idLabel);
        RegisterPanel.add(idField);
        RegisterPanel.add(passwordLabel);
        RegisterPanel.add(passwordField);
        RegisterPanel.add(repassLabel);
        RegisterPanel.add(repassField);
        RegisterPanel.add(returnButton);
        RegisterPanel.add(confirmButton);

        RegisterFrame.add(RegisterPanel);
        RegisterFrame.setVisible(true);

    }

    /**
     * This method is to close the frame
     */
    public void destroy() {
        RegisterFrame.dispose();
    }

}
