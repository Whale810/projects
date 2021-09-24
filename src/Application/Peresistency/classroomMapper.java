package Application.Peresistency;

import java.sql.*;
import java.util.LinkedList;

/**
 * This is the class to connect with the class room table inside the database.
 * All the data get from the data base will be stored at here first and then transport to another class or method.
 * @author 18206138 Jin Chenzhe
 * @author 18206115 Guo Zikang
 */
public class classroomMapper {

    /**
     * This method is used to connect with the database and return all the class rooms inside the database
     * @return all the class rooms
     */
    public LinkedList<String> getAllClassrooms() {
        Connection con = null;
        Statement stm = null;
        LinkedList<String> roomlist = new LinkedList<String>();

        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:booking.db");

            stm = con.createStatement();
            ResultSet res;
            String sql = "select * from classrooms";
            res = stm.executeQuery(sql);

            while (res.next()) {
                roomlist.add(res.getString("place"));
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
        return roomlist;
    }

    /**
     * This method is used to connect with the database and return an class room id through its place
     * @param place class room place
     * @return id of a class room
     */
    public int getRoomID(String place) {
        Connection con = null;
        Statement stm = null;
        int id = 0;

        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:booking.db");

            stm = con.createStatement();
            ResultSet res;
            String sql = "select cid from classrooms where place = '" + place + "'";
            res = stm.executeQuery(sql);

            while (res.next()) {
                id = res.getInt("cid");
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
     * This method is used to connect with the database and return the people limit of a classroom through its id
     * @param id class room id
     * @return the people limit of a classroom
     */
    public int getRoomPeople(int id) {
        Connection con = null;
        Statement stm = null;
        int people = 0;

        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:booking.db");

            stm = con.createStatement();
            ResultSet res;
            String sql = "select people from classrooms where cid = '" + id + "'";
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

    /**
     * This method is used to connect with the database and return place of a class room through its id
     * @param id class room id
     * @return the place of a class room
     */
    public String getRoomPlace(int id) {
        Connection con = null;
        Statement stm = null;
        String place = "";

        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:booking.db");

            stm = con.createStatement();
            ResultSet res;
            String sql = "select place from classrooms where cid = " + id;
            res = stm.executeQuery(sql);

            while (res.next()) {
                place = res.getString("place");
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
        return place;
    }
}
