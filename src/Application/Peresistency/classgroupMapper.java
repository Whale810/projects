package Application.Peresistency;

import java.sql.*;
import java.util.LinkedList;

/**
 * This is the class to connect with the class groups table inside the database.
 * All the data get from the data base will be stored at here first and then transport to another class or method.
 * @author 18206138 Jin Chenzhe
 * @author 18206115 Guo Zikang
 */
public class classgroupMapper {

    /**
     * This method is used to connect with the database and return all the class groups inside the database
     * @return all the class groups
     */
    public LinkedList<String> getAllClassgroups() {
        Connection con = null;
        Statement stm = null;
        LinkedList<String> grouplist = new LinkedList<String>();

        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:booking.db");

            stm = con.createStatement();
            ResultSet res;
            String sql = "select * from classgroups";
            res = stm.executeQuery(sql);

            while (res.next()) {
                grouplist.add(res.getString("name"));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(stm != null) stm.close();
            }catch(SQLException se2) {
            }
            try {
                if(con != null) con.close();
            }catch(SQLException se) {
                se.printStackTrace();
            }
        }
        return grouplist;
    }

    /**
     * This method is used to connect with the database and return a class group through its name
     * @param name class group name
     * @return a class group that is suitable to the condiations
     */
    public int getGroupID(String name) {
        Connection con = null;
        Statement stm = null;
        int id = 0;

        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:booking.db");

            stm = con.createStatement();
            ResultSet res;
            String sql = "select gid from classgroups where name = '" + name + "'";
            res = stm.executeQuery(sql);

            while (res.next()) {
                id = res.getInt("gid");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(stm != null) stm.close();
            }catch(SQLException se2) {
            }
            try {
                if(con != null) con.close();
            }catch(SQLException se) {
                se.printStackTrace();
            }
        }
        return id;
    }

    /**
     * This method is used to connect with the database and return the name of a class group through it id
     * @param id the class group id
     * @return the name of a group
     */
    public String getGroupString(int id) {
        Connection con = null;
        Statement stm = null;
         String  name= "";

        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:booking.db");

            stm = con.createStatement();
            ResultSet res;
            String sql = "select name from classgroups where gid = "+ id;
            res = stm.executeQuery(sql);

            while (res.next()) {
                name = res.getString("name");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(stm != null) stm.close();
            }catch(SQLException se2) {
            }
            try {
                if(con != null) con.close();
            }catch(SQLException se) {
                se.printStackTrace();
            }
        }
        return name;
    }

    /**
     * This method is used to connect with the database and return a people size of a class group through its name
     * @param name class group name
     * @return group people number
     */
    public int getGroupPeople(String name) {
        Connection con = null;
        Statement stm = null;
        int people = 0;

        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:booking.db");

            stm = con.createStatement();
            ResultSet res;
            String sql = "select people from classgroups where name = '" + name + "'";
            res = stm.executeQuery(sql);

            while (res.next()) {
                people = res.getInt("people");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(stm != null) stm.close();
            }catch(SQLException se2) {
            }
            try {
                if(con != null) con.close();
            }catch(SQLException se) {
                se.printStackTrace();
            }
        }
        return people;
    }

}
