package Application.Peresistency;

import java.sql.*;

/**
 * This is the class to connect with the login table inside the database.
 * All the data get from the data base will be stored at here first and then transport to another class or method.
 * @author 18206141 Gong Chenhan
 */
public class registerMapper {

    /**
     * This method is used to connect with the database and inset a new account into the database
     * @param userid user id
     * @param p1 password bound with id above
     */
    public void adduser(int userid,String p1){
        Connection con = null;
        Statement stm = null;
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:booking.db");
            ResultSet res;
            PreparedStatement psql;
            psql = con.prepareStatement("insert into logins(uid, password)" + "values(?, ?)");
            psql.setInt(1,userid);
            psql.setString(2,p1);
            psql.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
