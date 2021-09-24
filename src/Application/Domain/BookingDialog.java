package Application.Domain;

import Application.Peresistency.bookingMapper;
import Application.Peresistency.teacherMapper;
import Application.Peresistency.classroomMapper;
import Application.Peresistency.classgroupMapper;

import View.addBookingView;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

/**
 * This is the domain class for add Booking GUI.
 * In this Class, all the function buttons gets their function ability.
 * The final function of this class is to add a new booking into database
 * @author 18206138 Jin Chenzhe
 */
public class BookingDialog extends addBookingView {

    //A message to return the choice of warn dialog of people oversize
    String msg = "";

    /**
     * This the constructor of this class.
     * Inside this method, all the buttons' listeners are added and the functions are achieve throw those listener.
     */
    public BookingDialog() {
        /**
         * The listener for search button.
         * After click this button, this class will ask booking mapper to search and return the available class rooms at the selected date.
         */
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listModel.removeAllElements();

                //Two linked lists.
                //First one is to record the whole teachers
                //Second one is to record the whole classrooms
                LinkedList<String> teacherlist = new teacherMapper().getAllTeachers();
                LinkedList<String> grouplist = new classgroupMapper().getAllClassgroups();
                Date datetime = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String datenow = sdf.format(datetime);
                String date = dateField.getText().trim();
                int section = Integer.parseInt(sectionBox.getSelectedItem().toString());

                //This exception is used to deal with two things
                //1. Find whether user has entered a date
                //2. Ask user that is not able to add a booking to last date
                try {
                    Date d1 = sdf.parse(datenow);
                    Date d2 = sdf.parse(date);

                    if (date.equals("yyyy-MM-dd")) {
                        showErrorMessage("Please enter a date !!!");
                        return;
                    }else if (d1.getTime() > d2.getTime()) {
                        showErrorMessage("Cannot book date before now !!!");
                        return;
                    }
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }

                //Here add button to the room list, teacher box and class group box
                //If there is no available rooms at the selected date, a warn dialog will occur
                LinkedList<String> emptyroom = new bookingMapper().getEmptyRoom(date, section);
                if (emptyroom.size() == 0) {
                    showErrorMessage("No available rooms, Please change the date or section !!!");
                    return;
                }else {
                    for (String i:emptyroom) {
                        listModel.addElement(i);
                    }
                    for (String i: teacherlist) {
                        teacherBox.addItem(i);
                    }
                    for (String i: grouplist) {
                        classgroupBox.addItem(i);
                    }
                    confirmButton.setEnabled(true);
                }
            }
        });

        /**
         * This listener is for confirm button.
         * The function of this button is to insert a booking into database in a correct format.
         */
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = classroomList.getSelectedIndex();
                int bid = (int)((Math.random()*9+1)*1000);

                if (index < 0) {//If the don not select the confirm, it will warn user
                    return;
                }else {
                    //Here get the information for a booking
                    String date = dateField.getText().trim();
                    int section = Integer.parseInt(sectionBox.getSelectedItem().toString());
                    int cid = new classroomMapper().getRoomID(listModel.getElementAt(index));
                    int tid = new teacherMapper().getTeacherID(teacherBox.getSelectedItem().toString());
                    int gid = new classgroupMapper().getGroupID(classgroupBox.getSelectedItem().toString());

                    bookingMapper bookingMapper = new bookingMapper();
                    if (!bookingMapper.teacherAvailable(date, tid, section)) {//Here check whether the teacher is free at that time
                        showErrorMessage("Teacher is not available at that time !!!");
                        return;
                    } else {
                        if (!bookingMapper.classgoupAvailable(date, gid, section)) {//Here check whether the classgroup is free at that time
                            showErrorMessage("Classgroup is not available at that time !!!");
                            return;
                        } else {
                            int groupPeople = getPeople(peopleLabel);
                            int roomPeople = getPeople(roompeopleLabel);
                            if (groupPeople >= roomPeople) {//Here check whether the people is over the limit of a classroom
                                showPeopleDialog(frame, frame);
                                if (msg.equals("Yes")) {
                                    new bookingMapper().insertBooking(bid, cid, tid, gid, section, date);
                                    destroy();
                                }else if (msg.equals("Back")) {
                                    destroy();
                                }
                            } else {
                                new bookingMapper().insertBooking(bid, cid, tid, gid, section, date);
                                destroy();
                                showErrorMessage("Booking Successfully !!!");
                            }
                        }
                    }
                }
            }
        });

        /**
         * This listener is for selected available classrooms
         */
        classroomList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int index = classroomList.getSelectedIndex();
                if (index < 0) {
                    return;
                }else {//If a classroom is selected, the label in the add dialog will display the people limit of this classroom
                    classroomMapper classroomMapper = new classroomMapper();
                    String place = listModel.getElementAt(index);
                    int id = classroomMapper.getRoomID(place);
                    int people = classroomMapper.getRoomPeople(id);
                    roompeopleLabel.setText("Room people : " + people);
                }
            }
        });

        /**
         * This is the listener for class group box
         */
        classgroupBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {//If a class group is selected, the label in the add dialog will display the people size of the group
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String name = classgroupBox.getSelectedItem().toString();
                    int people = new classgroupMapper().getGroupPeople(name);
                    peopleLabel.setText("Class people : " + people);
                }
            }
        });
    }

    /**
     * This method is to create a option dialog for user to confirm whether select one class room, if the people limit is lower than the selected class group
     * @param frame The father component of this dialog
     * @param component The display component contains this dialog
     */
    private void showPeopleDialog(Frame frame, Component component) {
        final JDialog dialog = new JDialog(frame, "Tips", true);
        dialog.setSize(500, 150);
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(component);
        dialog.setLayout(new BorderLayout());

        JPanel panel1, panel2;
        JLabel message1 = new JLabel("The people number of classgroup is larger than the limitation of class room !!!");
        JLabel message2 = new JLabel("Do you want to use this class room?");
        JButton btn1 = new JButton("Yes");
        JButton btn2 = new JButton("No");
        JButton btn3 = new JButton("Back to Timetable");

        /**
         * This options will display when the class room people limit is lower than the people size of a class group
         * For the button listeners below:
         * 1. If the user selects "Yes", system will still add this booking into database as usual
         * 2. If the user selects "No", system will do nothing and return back to the add dialog
         * 3. If the user selects "Back ...", system will close the add dialog the return to the time table
         */
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
                msg = "Yes";
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
                msg = "Back";
            }
        });

        panel1 = new JPanel();
        panel1.add(message1);
        panel1.add(message2);

        panel2 = new JPanel();
        panel2.add(btn1);
        panel2.add(btn2);
        panel2.add(btn3);

        dialog.add(panel1, BorderLayout.CENTER);
        dialog.add(panel2, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    /**
     * This method is to help get people number from a label
     * @param label The label that contains people number
     * @return the people number
     */
    public int getPeople(JLabel label) {
        String content = label.getText();
        String[] group = content.split(":");
        String number = group[1].substring(1);
        int people = Integer.parseInt(number);
        return people;
    }

}
