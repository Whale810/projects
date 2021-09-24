package View;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

/**
 * This class is to create GUI components for add booking dialog.
 * The add booking dialog extends this class to edit the components directly.
 * @author 18206138 Jin Chenzhe
 */
public class addBookingView {

    Date datetime = new Date();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String localdate = simpleDateFormat.format(datetime);

    //GUI components
    protected JFrame frame;
    protected JPanel datePanel, infoPanel, combinePanel;
    protected JScrollPane listPanel;
    protected JLabel dateLabel, sectionLabel, teacherLabel, classgroupLabel, peopleLabel, roompeopleLabel;
    protected JButton confirmButton, searchButton;
    protected JTextField dateField;
    protected JComboBox<String> teacherBox, classgroupBox, sectionBox;
    protected JList classroomList;
    protected DefaultListModel<String> listModel;

    /**
     * The constructor of this class
     * The window frame is initialized here
     * init method will initialize all the other components
     */
    public addBookingView() {
        frame = new JFrame("Add Booking");
        frame.setSize(400, 500);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(0, 2));
        init();
    }

    /**
     * This method is to init all the interactive components inside the frame
     */
    public void init() {
        dateLabel = new JLabel("Date: ");
        sectionLabel = new JLabel("Section: ");
        teacherLabel = new JLabel("Teacher:　");
        classgroupLabel = new JLabel("Classgroup: ");
        peopleLabel = new JLabel("Class People:");
        dateField = new JTextField(localdate);
        roompeopleLabel = new JLabel("Room People:");
        searchButton = new JButton("Search");
        confirmButton = new JButton("Confirm");

        datePanel = new JPanel(new GridLayout(5,0));
        String[] section = {"1", "2", "3", "4", "5"};
        sectionBox = new JComboBox<String>(section);
        datePanel.add(dateLabel);
        datePanel.add(dateField);
        datePanel.add(sectionLabel);
        datePanel.add(sectionBox);
        datePanel.add(searchButton);
        datePanel.setBorder(new TitledBorder("Date"));

        infoPanel = new JPanel(new GridLayout(7, 0));
        teacherBox = new JComboBox<String>();
        classgroupBox = new JComboBox<String>();
        infoPanel.add(teacherLabel);
        infoPanel.add(teacherBox);
        infoPanel.add(classgroupLabel);
        infoPanel.add(classgroupBox);
        infoPanel.add(peopleLabel);
        infoPanel.add(roompeopleLabel);
        infoPanel.add(confirmButton);
        confirmButton.setEnabled(false);
        infoPanel.setBorder(new TitledBorder("Info"));

        combinePanel = new JPanel(new GridLayout(2, 0));
        combinePanel.add(datePanel);
        combinePanel.add(infoPanel);
        combinePanel.setBorder(new TitledBorder("Setting"));

        listModel = new DefaultListModel<String>();
        classroomList = new JList(listModel);
        listPanel = new JScrollPane(classroomList);
        listPanel.setBorder(new TitledBorder("Available Classroom: "));

        frame.add(combinePanel);
        frame.add(listPanel);
        frame.setVisible(true);

    }

    /**
     * This method is to close the frame
     */
    public void destroy() {
        frame.dispose();
    }

    /**
     * This method is to display error message
     * @param msg error message
     */
    public void showErrorMessage(String msg) {
        JOptionPane.showMessageDialog(frame, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

}
