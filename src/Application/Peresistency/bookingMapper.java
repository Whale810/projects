package Application.Peresistency;

import Application.Domain.booking;

import java.sql.*;
import java.util.LinkedList;

/**
 * This is the class to connect with the booking table inside the database.
 * All the data get from the data base will be stored at here first and then transport to another class or method.
 * @author 18206138 Jin Chenzhe
 * @author 18206115 Guo Zikang
 * @author 18206129 Xu Chuyuan
 */
public class bookingMapper {

    /**
     * This method is used to connect with the database and insert a new booking into the booking table.
     * @param bid booking id
     * @param cid classroom id
     * @param tid teacher id
     * @param gid class group id
     * @param section section time
     * @param date booking date
     */
    public void insertBooking(int bid, int cid, int tid, int gid, int section, String date) {
        //variables below is the connect components tp connect with thw database
        Connection con = null;
        Statement stm = null;

        try {
            //connect with the database
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:booking.db");

            //initialize the connect statement
            stm = con.createStatement();
            //the sql words to be executed
            String sql = "insert into bookings values ('" + bid + "','" + cid + "','" + tid + "','" + gid + "','" + date + "','" + section + "')";
            //execute the sql words
            stm.executeQuery(sql);

        }catch (Exception e) {

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
    }

    /**
     * This method is connect the database and return the empty classrooms that is free at the date and section
     * @param date booking date
     * @param section booking section
     * @return free classrooms
     */
    public LinkedList<String> getEmptyRoom(String date, int section) {
        Connection con = null;
        Statement stm = null;
        //lists below is to store information of rooms
        LinkedList<String> bookinglist = new classroomMapper().getAllClassrooms();
        LinkedList<Integer> roomIDlist = new LinkedList<Integer>();
        LinkedList<String> roomlist = new classroomMapper().getAllClassrooms();
        LinkedList<String> roombookedlist = new LinkedList<String>();

        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:booking.db");

            stm = con.createStatement();
            ResultSet res;
            String sql = "select cid from bookings where date ='" + date + "' and section = '" + section +"'";
            res = stm.executeQuery(sql);
            //get the certain rooms id
            while (res.next()) {
                roomIDlist.add(res.getInt("cid"));
            }

            //according to id found all the place name
            for (int cid: roomIDlist) {
                sql = "select place from classrooms where cid = '" + cid + "'";
                res = stm.executeQuery(sql);
                while (res.next()) {
                    roombookedlist.add(res.getString("place"));
                }
            }

            //remove the booked classroom and return the free rooms
            for (String rl: roomlist) {
                for (String rbl: roombookedlist) {
                    if (rl.equals(rbl)) {
                        bookinglist.remove(rbl);
                    }
                }
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
        return bookinglist;
    }

    /**
     * This method is connect the database and return whether the teacher is available at the selected date and time
     * @param date booking date
     * @param id teacher id
     * @param section booking section
     * @return list of teachers who are free at that time
     */
    public Boolean teacherAvailable(String date, int id, int section) {
        Connection con = null;
        Statement stm = null;
        Boolean ava = true;
        LinkedList<Integer> sectionlist = new LinkedList<Integer>();


        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:booking.db");

            stm = con.createStatement();
            ResultSet res;
            String sql = "select section from bookings where date ='" + date + "' and tid = '" + id +"'";
            res = stm.executeQuery(sql);
            //get all the sections teacher owns at that date
            while (res.next()) {
                sectionlist.add(res.getInt("section"));
            }

            //if teacher is not available at that section, return false
            for (int s: sectionlist) {
                if (s == section) {
                    ava = false;
                }
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
        return ava;
    }

    /**
     * This method is connect the database and return bookings through teacher id, date and time
     * @param tid teacher id
     * @param day booking date
     * @param section booking section
     * @param cid classroom id
     * @return bookings that is suitable to the conditions
     */
    public booking getBookingbytid(int tid, String day, int section, int cid) {
        Connection con = null;
        Statement stm = null;
        booking book =null;

        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:booking.db");

            stm = con.createStatement();
            ResultSet res;
            String sql = "select * from bookings where tid = "+tid+" and date= '"+day+"' and section = "+section+" and cid = "+cid;
            res = stm.executeQuery(sql);
            while (res.next()) {
                book =new booking(res.getInt("bid"),res.getInt("cid"),res.getInt("tid"),res.getInt("gid"),res.getString("date"),res.getInt("section"));
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if(stm != null) stm.close();
            }catch(SQLException se2) { }

            try {
                if(con != null) con.close();
            }catch(SQLException se) {
                se.printStackTrace();
            }
        }
	        return book;
    }

    /**
     * This method is connect the database and return bookings through classgroup id, date and time
     * @param gid group id
     * @param day booking date
     * @param section booking section
     * @param cid classroom id
     * @return bookings that are suitable to the conditions
     */
    public booking getBookingbygid(int gid,String day,int section,int cid) {
        Connection con = null;
        Statement stm = null;
        booking book =null;

        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:booking.db");

            stm = con.createStatement();
            ResultSet res;
            String sql = "select * from bookings where gid = "+gid+" and date= '"+day+"' and section = "+section+" and cid = "+cid;
            res = stm.executeQuery(sql);
            while (res.next()) {
        	    book =new booking(res.getInt("bid"),res.getInt("cid"),res.getInt("tid"),res.getInt("gid"),res.getString("date"),res.getInt("section"));
            }

        } catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally {
        try {
            if(stm != null) stm.close();
        }catch(SQLException se2) { }
        try {
            if(con != null) con.close();
        }catch(SQLException se) {
            se.printStackTrace();
            }
        }
        return book;
    }

    /**
     * This method is connect the database and return whether the class group is available at the selected date and time
     * @param date booking date
     * @param id class group id
     * @param section booking section
     * @return list of teachers who are free at that time
     */
    public Boolean classgoupAvailable(String date, int id, int section) {
        Connection con = null;
        Statement stm = null;
        Boolean ava = true;
        LinkedList<Integer> sectionlist = new LinkedList<Integer>();

        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:booking.db");

            stm = con.createStatement();
            ResultSet res;
            String sql = "select section from bookings where date ='" + date + "' and gid = '" + id +"'";
            res = stm.executeQuery(sql);
            while (res.next()) {
                sectionlist.add(res.getInt("section"));
            }

            for (int s: sectionlist) {
                if (s == section) {
                    ava = false;
                }
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
        return ava;
    }

    /**
     * This method is connect the database and update the booking information of a exist booking
     * @param bid booking id
     * @param cid class room id
     * @param tid teacher id
     * @param gid group id
     * @param date booking date
     * @param section booking section
     */
    public void update(int bid,int cid,int tid,int gid,String date,int section) {
        Connection con = null;
        Statement stm = null;

        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:booking.db");

            PreparedStatement psql;
            psql = con.prepareStatement("update bookings set cid = ?, tid = ?, gid = ?,date = ?, section = ? where bid = ?");
            psql.setInt(1,cid);
            psql.setInt(2,tid);
            psql.setInt(3,gid);
            psql.setString(4,date);
            psql.setInt(5,section);
            psql.setInt(6,bid);
            psql.executeUpdate();

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
    }

    /**
     * This method is connect the database and delete the selected booking
     * @param bid booking id
     */
    public void deleteBooking(int bid) {
        Connection con = null;
        Statement stm = null;

        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:booking.db");

            PreparedStatement psql;
            psql = con.prepareStatement("delete from bookings where bid = ?");
            psql.setInt(1,bid);
            psql.executeUpdate();

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
    }

    /**
     * This method is connect the database and return all bookings at that date and time
     * @param date booking date
     * @param section booking section
     * @param cid booking classroom
     * @return booking that is suitable to the conditions
     */
    public booking getAllBooking(String date, int section, int cid) {
        Connection con = null;
        Statement stm = null;
        booking book =null;

        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:booking.db");

            stm = con.createStatement();
            ResultSet res;
            String sql = "select * from bookings where date= '"+date+"' and section = "+section+" and cid = "+cid;
            res = stm.executeQuery(sql);
            while (res.next()) {
                book =new booking(res.getInt("bid"),res.getInt("cid"),res.getInt("tid"),res.getInt("gid"),res.getString("date"),res.getInt("section"));
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if(stm != null) stm.close();
            }catch(SQLException se2) { }
            try {
                if(con != null) con.close();
            }catch(SQLException se) {
                se.printStackTrace();
            }
        }
        return book;
    }
}
