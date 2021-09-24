package Application.Peresistency;

import java.sql.*;
import java.util.LinkedList;

/**
 * This is the class to connect with the teacher table inside the database.
 * All the data get from the data base will be stored at here first and then transport to another class or method.
 * @author 18206138 Jin Chenzhe
 * @author 18206115 Guo Zikang
 */
public class teacherMapper {

    /**
     * This method is used to connect with the database and get all the teachers
     * @return the name of the teachers
     */
    public LinkedList<String> getAllTeachers() {
        Connection con = null;
        Statement stm = null;
        LinkedList<String> teacherlist = new LinkedList<String>();

        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:booking.db");

            stm = con.createStatement();
            ResultSet res;
            String sql = "select * from teachers";
            res = stm.executeQuery(sql);

            while (res.next()) {
                teacherlist.add(res.getString("name"));
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
        return teacherlist;
    }

    /**
     * This method is used to connect with the database and return a teacher id through his name
     * @param name teacher name
     * @return teacher id
     */
    public int getTeacherID(String name) {
        Connection con = null;
        Statement stm = null;
        int id = 0;

        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:booking.db");

            stm = con.createStatement();
            ResultSet res;
            String sql = "select tid from teachers where name = '" + name + "'";
            res = stm.executeQuery(sql);

            while (res.next()) {
                id = res.getInt("tid");
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
     * This method is used to connect with the database and return teacher name through his id
     * @param id teacher id
     * @return the name of a teacher
     */
    public String getTeacherName(int id) {
        Connection con = null;
        Statement stm = null;
       String name = null;

        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:booking.db");

            stm = con.createStatement();
            ResultSet res;
            String sql = "select name from teachers where tid = " + id;
            res = stm.executeQuery(sql);

            while (res.next()) {
                name= res.getString("name");
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

}
