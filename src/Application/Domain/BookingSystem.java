package Application.Domain;

import View.EditBookingView;
import View.TimetableView;

import javax.swing.*;

import Application.Peresistency.bookingMapper;
import Application.Peresistency.classgroupMapper;
import Application.Peresistency.classroomMapper;
import Application.Peresistency.teacherMapper;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This the domain class for display timetable GUI
 * This class is to control the components in the timetable and achieve the search and display functions
 * @author 18206115 Guo Zikang
 * @author 18206129 Xu Chuyuan
 * @author 18206138 Jin Chenzhe
 */
public class BookingSystem extends TimetableView {

    String msg = "";//this the variable for exit button message
    booking book = null;//an initial book entity for selected gird in the timetable

    /**
     * The array below is contain the buttons inside this GUI
     */
    JButton[][] buttontable = new JButton[7][6];
    {buttontable[1][1]=twotwo;
    buttontable[1][2]=twothree;           
    buttontable[1][3]=twofour;
    buttontable[1][4]=twofive;
    buttontable[1][5]=twosix;
    buttontable[2][1]=threetwo;
    buttontable[2][2]=threethree;
    buttontable[2][3]=threefour;
    buttontable[2][4]=threefive;
    buttontable[2][5]=threesix;
    buttontable[3][1]=fourtwo;
    buttontable[3][2]=fourthree;
    buttontable[3][3]=fourfour;
    buttontable[3][4]=fourfive;
    buttontable[3][5]=foursix;
    buttontable[4][1]=fivetwo;
    buttontable[4][2]=fivethree;
    buttontable[4][3]=fivefour;
    buttontable[4][4]=fivefive;
    buttontable[4][5]=fivesix;
    buttontable[5][1]=sixtwo;
    buttontable[5][2]=sixthree;
    buttontable[5][3]=sixfour;
    buttontable[5][4]=sixfive;
    buttontable[5][5]=sixsix;
    buttontable[6][1]=seventwo;
    buttontable[6][2]=seventhree;
    buttontable[6][3]=sevenfour;
    buttontable[6][4]=sevenfive;
    buttontable[6][5]=sevensix;}


    /**
     * The constructor of this class.
     * All the buttons are init and get listener from here.
     */
    public BookingSystem() {
        /**
         * This is the listener for exit button.
         * Click this button to exit the timetable.
         */
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showExitDialog(tableFrame, tableFrame);
                if (msg.equals("dispose")) {
                    destroy();
                }else if (msg.equals("login")) {
                    destroy();
                    new Main();
                }
            }
        });

        /**
         * This is the listener for add button.
         * Clike this button to add a new booking.
         */
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BookingDialog();
            }
        });

        /**
         * This is the listener for search button.
         * Click this button to display timetable according to the search type.
         */
        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String msg = searchBox.getSelectedItem().toString();

                /**
                 * The if statement below is decide which search type user wants.
                 * 1. If the type is "All", it will display the whole booking at one day.
                 * 2. If the type is "Teacher", it will display the whole booing at one day about this teacher
                 * 3. If the type is "Class group", it will display the whole booing at one day about this class group
                 */
                if (msg.equals("All")) {
                    for(int cid=1;cid<7;cid++) {
                        for(int section=1;section<6;section++) {
                            buttontable[cid][section].setText("");
                            booking book =new bookingMapper().getAllBooking(date, section, cid);
                            if(book!=null) {
                                buttontable[book.getcid()][book.getsection()].setText(new classgroupMapper().getGroupString(book.getgid())+" || "+
                                        new classroomMapper().getRoomPlace(book.getcid())+" || "+
                                        new teacherMapper().getTeacherName(book.gettid()));

                            }
                        }
                    }
                } else {
                    if ( searchField.getText() ==null||searchField.getText().equals("") ) {
                        showErrorMessage("Please enter search terms !!!");
                    }else {
                        String searchtype = (String) searchBox.getSelectedItem();
                        String searchitem = searchField.getText();

                        if(searchtype.equals("search by teacher")) {
                            int id = new teacherMapper().getTeacherID(searchitem);
                            for(int cid=1;cid<7;cid++) {
                                for(int section=1;section<6;section++) {
                                    buttontable[cid][section].setText("");
                                    booking book =new bookingMapper().getBookingbytid(id,date,section,cid);
                                    if(book!=null) {
                                        buttontable[book.getcid()][book.getsection()].setText(new classgroupMapper().getGroupString(book.getgid())+" || "+
                                                new classroomMapper().getRoomPlace(book.getcid())+" || "+
                                                new teacherMapper().getTeacherName(book.gettid()));
                                        ;
                                    }
                                }
                            }
                        } else if(searchtype.equals("search by classgroup")) {
                            int id = new classgroupMapper().getGroupID(searchitem);
                            for(int cid=1;cid<7;cid++) {
                                for(int section=1;section<6;section++) {
                                    buttontable[cid][section].setText("");
                                    booking book =new bookingMapper().getBookingbygid(id,date,section,cid);
                                    if(book!=null) {
                                        buttontable[book.getcid()][book.getsection()].setText(new classgroupMapper().getGroupString(book.getgid()) + " || " +
                                                new classroomMapper().getRoomPlace(book.getcid()) + " || " +
                                                new teacherMapper().getTeacherName(book.gettid()));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });

        /**
         * This is the listener for next day button.
         * Click the button to chang the date to the next day.
         */
        nextday.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
        		reset();
        		day=day+1;
        		date =Integer.toString(year)+"-"+Integer.toString(month)+"-"+Integer.toString(day);
        		
        		if(day>31) {
        			year=2021;
        			day=1;
        			month=1;
        		}
        		nowdate.setText("                                                       "+date);
        	}
        });

        /**
         * This is the listener for previous day button.
         * Click the button to chang the date to the previous day.
         */
        previousday.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		reset();
        		day=day-1;
        		date =Integer.toString(year)+"-"+Integer.toString(month)+"-"+Integer.toString(day);
        		if(day<1) {
        			day=30;
        			month=month-1;
        		}
        		nowdate.setText("                                                       "+date);
        	}
        });

        /**
         * This is the listener for edit button.
         * Click this button to edit a exist and selected booking on the timetable
         */
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(book == null){
                    showErrorMessage("You didn't select any booking!");
                }else{
                    new EditBookingView(book);
                }

            }
        });

        /**
         * This double loop is to add listeners to all the buttons of time tale grids.
         */
        for(int cid= 1;cid<7;cid++){
            for(int section=1; section<6;section++){
                JButton[][] buttons = getButtontable();
                int finalSection = section;
                int finalCid = cid;
                buttons[cid][section].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        /**
                         * The exception is to get the the information of a grid that helps edit function to edit the booking.
                         */
                        try {
                            String a = buttons[finalCid][finalSection].getText();
                            if(!a.equals("") && a!=null){
                                String classname = a.substring(0,a.indexOf(" || "));
                                int gid = new classgroupMapper().getGroupID(classname);
                                int cid = new classroomMapper().getRoomID(a.substring(a.indexOf("||")+3,a.lastIndexOf(" || ")));
                                int tid = new teacherMapper().getTeacherID(a.substring(a.lastIndexOf(" || ")+4));
                                book = new bookingMapper().getBookingbygid(gid,date,finalSection,cid);
                                oneone.setText(new classgroupMapper().getGroupString(book.getgid())+" || "+
                                        new classroomMapper().getRoomPlace(book.getcid())+" || "+
                                        new teacherMapper().getTeacherName(book.gettid()));
                            }
                        }catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    }
                });
            }
        }
    }

    /**
     * This method is to display a exit dialog if user clicks the exit button
     * @param frame The father component of this dialog
     * @param component The display component contains this dialog
     */
    private void showExitDialog(Frame frame, Component component) {
        final JDialog dialog = new JDialog(frame, "Tips", true);
        dialog.setSize(300, 100);
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(component);
        dialog.setLayout(new BorderLayout());

        JPanel panel1, panel2;
        JLabel message = new JLabel("Are you sure to exit?");
        JButton btn1 = new JButton("Yes");
        JButton btn2 = new JButton("No");
        JButton btn3 = new JButton("Back to Login");

        /**
         * This options will display when user click exit button to exit.
         * For the button listeners below:
         * 1. If the user selects "Yes", user will exit
         * 2. If the user selects "No", system will do nothing and return back to the timetable
         * 3. If the user selects "Back ...", system will close the add dialog the return to the login dialog
         */
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
                msg = "dispose";
            }
        });
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
                msg = "login";
            }
        });

        panel1 = new JPanel(new GridLayout(1, 1));
        panel1.add(message);

        panel2 = new JPanel();
        panel2.add(btn1);
        panel2.add(btn2);
        panel2.add(btn3);

        dialog.add(panel1, BorderLayout.NORTH);
        dialog.add(panel2, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    /**
     * this method is to display the error message
     * @param msg
     */
    private void showErrorMessage(String msg) {
        JOptionPane.showMessageDialog(tableFrame, msg, "Confirm Message",JOptionPane.ERROR_MESSAGE);
    }

    /**
     * this method is to reset the whole items inside the timetable
     */
    public void reset(){
        for(int i=1;i<7;i++) {
            for(int j=1;j<6;j++) {
                buttontable[i][j].setText("");
            }
        }
    }

    /**
     * This metod is to return the button array
     * @return buttons array
     */
    public JButton[][] getButtontable(){
        return buttontable;
    }

}
