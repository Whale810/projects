package Storage;

import java.sql.*;

/**
 * This the class of storage.
 * The database and tables inside the database will be created in this class.
 * @author 18206138 Jin Chenzhe
 */
public class dataBase {

    /**
     * This method is to create all the tables inside the database.
     * The exception is make to avoid the error that tables have been created
     */
    public static void createTable() {
        Connection con = null;
        Statement stm = null;

        /**
         * create classrooms table
         */
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:booking.db");

            stm = con.createStatement();
            String sql = "create table classrooms(" +
                    "cid int primary key," +
                    "people int not null," +
                    "place varchar(20) not null)";
            stm.executeUpdate(sql);
            stm.close();
            con.close();
        }catch (Exception e) {
        	System.out.print("baga \n");
        }

        /**
         * create teachers table
         */
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:booking.db");

            stm = con.createStatement();
            String sql = "create table teachers(" +
                    "tid int primary key," +
                    "name varchar(40) not null)";
            stm.executeUpdate(sql);
            stm.close();
            con.close();
        }catch (Exception e) {

        }

        /**
         * create classgroups table
         */
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:booking.db");

            stm = con.createStatement();
            String sql = "create table classgroups(" +
                    "gid int primary key," +
                    "name varchar(40) not null," +
                    "people int not null)";
            stm.executeUpdate(sql);
            stm.close();
            con.close();
        }catch (Exception e) {

        }

        /**
         * create bookings table
         */
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:booking.db");

            stm = con.createStatement();
            String sql = "create table bookings(" +
                    "bid int primary key," +
                    "cid int not null," +
                    "tid int not null," +
                    "gid int not null," +
                    "date DATE not null," +
                    "section int not null)";
            stm.executeUpdate(sql);
            stm.close();
            con.close();
        }catch (Exception e) {

        }

        /**
         * create logins table
         */
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:booking.db");

            stm = con.createStatement();
            String sql = "create table logins(" +
                    "uid int primary key," +
                    "password varchar(30) not null)";
            stm.executeUpdate(sql);
            stm.close();
            con.close();
        }catch (Exception e) {

        }
    }

}
