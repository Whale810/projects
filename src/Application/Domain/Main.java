package Application.Domain;

import Storage.dataBase;
import View.LoginView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This the Main class of this all classrooms booking system.
 * It is also the domain class for login GUI.
 * The system starts at the login window.
 * @author 18206138 Jin Chenzhe
 * @author 18206141 Gong Chenhan
 */
public class Main extends LoginView{

    /**
     * The constructor of this class.
     * All the listeners of all buttons are initialized here.
     */
    public Main() {
        /**
         * This is the listener for register button.
         * Click this button to register an account to login.
         */
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Register register = new Register();
                destroy();
            }
        });

        /**
         * This is the listener for login button.
         * Click the button to login to the system.
         */
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int i = 0;
                    String pass = String.valueOf(passwordField.getPassword());
                    int userid = Integer.parseInt(idField.getText());
                    Login l = new Login(userid,pass);
                    if(l.loginchecker()){
                        BookingSystem bookingSystem = new BookingSystem();
                        destroy();
                    }else if(!l.loginchecker()){//if the password is not correct
                        showErrorMessage("Please Enter Correct Password !!!");
                    }
                }catch (Exception exception){//if id or password is not entered
                    showErrorMessage("Please enter ID or Password !!!");
                }
            }
        });
    }

    /**
     * Main method to start the system
     * @param args
     */
    public static void main(String[] args) {
        new Main();
        dataBase.createTable();
    }

}
