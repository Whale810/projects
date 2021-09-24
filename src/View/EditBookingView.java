package View;

import Application.Domain.booking;
import Application.Peresistency.bookingMapper;
import Application.Peresistency.classgroupMapper;
import Application.Peresistency.classroomMapper;
import Application.Peresistency.teacherMapper;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

/**
 * This class is to create GUI components for edit booking dialog.
 * Also the edit functions are added in the class either.
 * @author 18206129 Xu Chuyuan
 */
public class EditBookingView {

    String msg = "";
    LinkedList<String> teacherlist;
    LinkedList<String> grouplist;
    LinkedList<String> emptyroom;

    protected JFrame frame;
    protected JPanel datePanel, infoPanel, combinePanel;
    protected JScrollPane listPanel;
    protected JLabel dateLabel, sectionLabel, teacherLabel, classgroupLabel, classpeopleLabel, roompeopleLabel;
    protected JButton confirmButton, searchButton, deleteButton;
    protected JTextField dateField, peopleField;
    protected JComboBox<String> teacherBox, classgroupBox, sectionBox;
    protected JList classroomList;
    protected DefaultListModel<String> listModel;
    protected booking book;

    public EditBookingView(booking b) {
        frame = new JFrame("Edit Booking");
        frame.setSize(400, 500);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(0, 2));

        this.book = b;
        init();

    }

    public void init() {

        classgroupMapper c = new classgroupMapper();
        dateLabel = new JLabel("Date: ");
        sectionLabel = new JLabel("Section: ");
        teacherLabel = new JLabel("Teacher:　");
        classgroupLabel = new JLabel("Classgroup: ");
        classpeopleLabel = new JLabel("Class People : " + c.getGroupPeople(c.getGroupString(book.getgid())));
        dateField = new JTextField(book.getdate());
        roompeopleLabel = new JLabel("Room People : " + new classroomMapper().getRoomPeople(book.getcid()));
        searchButton = new JButton("Search");
        confirmButton = new JButton("Confirm");
        deleteButton = new JButton("Delete");

        datePanel = new JPanel(new GridLayout(5,0));
        String[] section = {"1", "2", "3", "4", "5"};
        sectionBox = new JComboBox<String>(section);
        sectionBox.setSelectedItem(String.valueOf(book.getsection()));
        datePanel.add(dateLabel);
        datePanel.add(dateField);
        datePanel.add(sectionLabel);
        datePanel.add(sectionBox);
        datePanel.add(searchButton);
        datePanel.setBorder(new TitledBorder("Date"));

        infoPanel = new JPanel(new GridLayout(8, 0));
        teacherBox = new JComboBox<String>();
        classgroupBox = new JComboBox<String>();
        show();
        teacherBox.setSelectedItem(new teacherMapper().getTeacherName(book.gettid()));
        classgroupBox.setSelectedItem(new classgroupMapper().getGroupString(book.getgid()));
        infoPanel.add(teacherLabel);
        infoPanel.add(teacherBox);
        infoPanel.add(classgroupLabel);
        infoPanel.add(classgroupBox);
        infoPanel.add(classpeopleLabel);
        infoPanel.add(roompeopleLabel);
        infoPanel.add(confirmButton);
        infoPanel.add(deleteButton);
        confirmButton.setEnabled(true);
        deleteButton.setEnabled(true);
        infoPanel.setBorder(new TitledBorder("Info"));

        combinePanel = new JPanel(new GridLayout(2, 0));
        combinePanel.add(datePanel);
        combinePanel.add(infoPanel);
        combinePanel.setBorder(new TitledBorder("Resetting"));

        listModel = new DefaultListModel<String>();
        listModel.addElement(new classroomMapper().getRoomPlace(book.getcid()));
        classroomList = new JList(listModel);
        listPanel = new JScrollPane(classroomList);
        listPanel.setBorder(new TitledBorder("Selected: "));

        frame.add(combinePanel);
        frame.add(listPanel);
        frame.setVisible(true);

        /**
         * The listener for search button.
         * After click this button, this class will ask booking mapper to search and return the available class rooms at the selected date.
         */
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    java.util.Date datetime = new java.util.Date();

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String datenow = sdf.format(datetime);
                    String date = dateField.getText().trim();
                    java.util.Date d1 = sdf.parse(datenow);
                    java.util.Date d2 = sdf.parse(date);

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
                listPanel.setBorder(new TitledBorder("Available Classroom: "));

                String date = dateField.getText().trim();
                int section = Integer.parseInt(sectionBox.getSelectedItem().toString());

                listModel.removeAllElements();
                emptyroom = new bookingMapper().getEmptyRoom(date, section);

                if (emptyroom.size() == 0) {
                    showErrorMessage("No available rooms, Please change the date or section !!!");
                    return;
                }else{
                    for (String i:emptyroom) {
                        listModel.addElement(i);
                    }
                }
            }
        });

        /**
         * This listener is for confirm button.
         * The function of this button is to update a booking inside database in a correct format.
         */
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = classroomList.getSelectedIndex();

                String date = dateField.getText().trim();
                int section = Integer.parseInt(sectionBox.getSelectedItem().toString());
                int cid = new classroomMapper().getRoomID(listModel.getElementAt(index));
                int tid = new teacherMapper().getTeacherID(teacherBox.getSelectedItem().toString());
                int gid = new classgroupMapper().getGroupID(classgroupBox.getSelectedItem().toString());

                bookingMapper bookingMapper = new bookingMapper();
                System.out.println(bookingMapper.teacherAvailable(date, tid, section));
                if (!bookingMapper.teacherAvailable(date, tid, section)) {
                    showErrorMessage("Teacher is not available at that time !!!");
                    return;
                } else {
                    if (!bookingMapper.classgoupAvailable(date, gid, section)) {
                        showErrorMessage("Classgroup is not available at that time !!!");
                        return;
                    } else {
                        int groupPeople = getPeople(classpeopleLabel);
                        int roomPeople = getPeople(roompeopleLabel);
                        if (groupPeople >= roomPeople) {
                            showPeopleDialog(frame, frame);
                            if (msg.equals("Yes")) {
                                new bookingMapper().update(book.getbid(),cid,tid,gid, date,section);
                                frame.dispose();
                            }else if (msg.equals("Back")) {
                                frame.dispose();
                            }
                        } else {
                            new bookingMapper().update(book.getbid(),cid,tid,gid, date,section);
                            frame.dispose();
                            ShowSuccess("You changed this booking successfully");
                        }
                    }
                }
                frame.dispose();
            }
        });

        /**
         * This listener is for delete button.
         * The function of this button is to delete a booking from database in a correct format.
         */
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new bookingMapper().deleteBooking(book.getbid());
                ShowSuccess("You deleted this booking successfully!");
                frame.dispose();
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
                }else {
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
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String name = classgroupBox.getSelectedItem().toString();
                    int people = new classgroupMapper().getGroupPeople(name);
                    classpeopleLabel.setText("Class people : " + people);
                }
            }
        });

    }

    /**
     * This method is to display successful message
     * @param msg successful message
     */
    public void ShowSuccess(String msg){
        JOptionPane.showMessageDialog(frame, msg, "Succeed", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * This method is to display error message
     * @param msg error message
     */
    public void showErrorMessage(String msg) {
        JOptionPane.showMessageDialog(frame, msg, "Error", JOptionPane.ERROR_MESSAGE);
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

    /**
     * The function of this method is the same as the method in booking dialog
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
     * This method is to add data into classroom list.
     * The principle is the same as the method in booking dialog
     */
    public void show(){

        teacherlist = new teacherMapper().getAllTeachers();
        grouplist = new classgroupMapper().getAllClassgroups();
        java.util.Date datetime = new java.util.Date();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String datenow = sdf.format(datetime);
        String date = dateField.getText().trim();
        int section = Integer.parseInt(sectionBox.getSelectedItem().toString());

        try {
            java.util.Date d1 = sdf.parse(datenow);
            java.util.Date d2 = sdf.parse(date);

            if (date.equals("yyyy-MM-dd")) {
                showErrorMessage("Please enter a date !!!");
                return;
            }
        } catch (ParseException parseException) {
            parseException.printStackTrace();
        }

        emptyroom = new bookingMapper().getEmptyRoom(date, section);
        if (emptyroom.size() == 0) {
            showErrorMessage("No available rooms, Please change the date or section !!!");
            return;
        }else {

            for (String i: teacherlist) {
                teacherBox.addItem(i);
            }
            for (String i: grouplist) {
                classgroupBox.addItem(i);
            }
            confirmButton.setEnabled(true);
        }
    }
}
