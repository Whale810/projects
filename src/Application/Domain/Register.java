package Application.Domain;

import Application.Domain.Main;
import Application.Peresistency.registerMapper;
import View.RegisterView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This the domain class for register GUI.
 * This class is to control the the display components and achieve the function of register an account
 * @author 18206138 Jin Chenzhe
 */
public class Register extends RegisterView {

    /**
     * The constructor of this class.
     * All the listeners of all the buttons are initialized in this constructor.
     */
    public Register() {
        /**
         * This is the listener of return button.
         * Click this to return to the login window and close the register window.
         */
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                destroy();
                Main main = new Main();
            }
        });

        /**
         * This is the listener for confirm button.
         * Click this to register am account.
         */
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (idField.getText() == null || passwordField.getPassword() == null || repassField.getPassword() == null || idField.getText().equals("") ) {//if no id or no password is entered
                        showErrorMessage("Please enter ID or Password !!!");
                    }else {
                        int userid = Integer.parseInt(idField.getText());
                        String p1 = String.valueOf(passwordField.getPassword());
                        String p2 = String.valueOf(repassField.getPassword());

                        if (p1.equals("") || p2.equals("")) {//if not enter password
                            showErrorMessage("Please enter Password !!!");
                        }else if (p1.equals(p2) == false) {//if two passwords are not same
                            showErrorMessage("Please enter the same Password !!!");
                        }else {
                            new registerMapper().adduser(userid,p1);
                            showSuccessfulMessage("Register Successfully !!!");
                            destroy();
                            Main main = new Main();
                        }
                    }
                } catch (Exception exception) {//If the id is not a intger
                    showErrorMessage("Please enter Integer for ID !!!");
                }
            }
        });
    }

    /**
     * This method is to display successful message
     * @param msg successful message
     */
    private void showSuccessfulMessage(String msg) {
        JOptionPane.showMessageDialog(RegisterFrame, msg, "Confirm Message",JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * This method is to display error message
     * @param msg error message
     */
    private void showErrorMessage(String msg) {
        JOptionPane.showMessageDialog(RegisterFrame, msg, "Confirm Message",JOptionPane.ERROR_MESSAGE);
    }

}
