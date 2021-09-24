package Application.Peresistency;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * This is the class to connect with the login table inside the database.
 * All the data get from the data base will be stored at here first and then transport to another class or method.
 * @author 18206141 Gong Chenhan
 */
public class loginMapper {

    /**
     * This method is used to connect with the database and return all the class rooms inside the database
     * @param userid user id
     * @return the password bounds with the user id
     */
    public String getTruepassword(int userid) {
        Connection con = null;
        Statement stm = null;
        String Truepassword = null;


        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:booking.db");

            stm = con.createStatement();
            ResultSet res;
            String  sql = "select password from logins where uid = '" + userid + "'";
            res = stm.executeQuery(sql);

            while(res.next()){
                Truepassword = res.getString("password");
            }

        }
        catch (Exception e) {
            e.printStackTrace();

        }
        return Truepassword;
    }
}
