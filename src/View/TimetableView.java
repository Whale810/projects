package View;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Calendar;

/**
 * This class is to create GUI components for Timetable dialog.
 * The timetable dialog extends this class to edit the components directly.
 * @author 18206115 Guo Zikang
 */
public class TimetableView {

    protected JFrame tableFrame;
    protected JButton addButton, displayButton, backButton,nextday,previousday,twotwo,twothree,twofour,edit;
    protected JButton twofive,twosix, threetwo, threethree, threefour,threefive,threesix,  fourtwo,fourthree,fourfour,fourfive,foursix;
    protected JButton fivetwo, fivethree,fivefour, fivefive, fivesix, sixtwo, sixthree, sixfour,sixfive ,sixsix   ;
    protected JButton seventwo,seventhree,sevenfour,sevenfive,sevensix;
    protected JLabel oneone;
    protected JPanel settingPanel, tablePanel;
    protected JComboBox<String> searchBox;
    protected JLabel nowdate;
    protected JTextField searchField;

     Calendar c = Calendar.getInstance();
    public   int year = c.get(Calendar.YEAR);  
    public   int month = c.get(Calendar.MONTH)+1;
    public   int day = c.get(Calendar.DATE);
    public   String date =Integer.toString(year)+"-"+Integer.toString(month)+"-"+Integer.toString(day);
    
    public TimetableView() {
        tableFrame = new JFrame("Timetable");
        tableFrame.setLayout(null);
        tableFrame.setSize(1200, 800);
        tableFrame.setResizable(false);
        tableFrame.setLocationRelativeTo(null);
        tableFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        init();
    }

	public void init() {
        Border border = BorderFactory.createLineBorder(Color.GRAY);

        addButton = new JButton("Add");
        displayButton = new JButton("Search");
        backButton = new JButton("Exit");
        edit = new JButton("Edit");
        previousday = new JButton("The previous day");
        nextday = new JButton("The next day");
        nowdate = new JLabel("                                                       "+date);
        GridLayout layout = new GridLayout(3, 3);
        String[] section = {"All", "search by teacher", "search by classgroup"};
        searchBox = new JComboBox<String>(section);
        searchField =new JTextField();
        settingPanel = new JPanel(layout);
        
        settingPanel.add(searchBox);
        settingPanel.add(searchField);
        settingPanel.add(displayButton);
        settingPanel.add(backButton);
        settingPanel.add(addButton);
        settingPanel.add(edit);
        settingPanel.add(previousday);
        settingPanel.add(nowdate);
        settingPanel.add(nextday);
        settingPanel.setBorder(new TitledBorder("Setting"));
        settingPanel.setBounds(0, 0, 1185, 150);
        
        GridLayout layout1 = new GridLayout(7, 6);
        
        tablePanel = new JPanel(layout1);
        
        
        oneone = new JLabel("Selected Booking");
        oneone.setOpaque(true);
        oneone.setBackground(Color.pink);
        oneone.setBorder(border);
        JLabel onetwo = new JLabel("<html>Time section 1<br>8:00 -- 9:30</html>");
        onetwo.setBorder(border);
        JLabel onethree = new JLabel("<html>Time section 2<br>10:00 -- 11:30</html>");
        onethree.setBorder(border);
        JLabel onefour = new JLabel("<html>Time section 3<br>13:30 -- 15:00</html>");
        onefour.setBorder(border);
        JLabel onefive = new JLabel("<html>Time section 4<br>15:30 -- 17:30</html>");
        onefive.setBorder(border);
        JLabel onesix = new JLabel("<html>Time section 5<br>18:00 -- 19:30</html>");
        onesix.setBorder(border);
        
        JLabel twoone = new JLabel("classroom 101");
        twoone.setBorder(border);
        JLabel threeone = new JLabel("classroom 102");
        threeone.setBorder(border);
        JLabel fourone = new JLabel("classroom 103");
        fourone.setBorder(border);
        JLabel fiveone = new JLabel("classroom 201");
        fiveone.setBorder(border);
        JLabel sixone = new JLabel("classroom 202");
        sixone.setBorder(border);
        JLabel sevenone = new JLabel("classroom 203");
        sevenone.setBorder(border);

        twotwo = new JButton();
        twotwo.setContentAreaFilled(false);
        twothree = new JButton();twothree.setContentAreaFilled(false);
        twofour = new JButton();twofour.setContentAreaFilled(false);
        twofive = new JButton();twofive.setContentAreaFilled(false);
        twosix = new JButton();twosix.setContentAreaFilled(false);
        threetwo = new JButton();threetwo.setContentAreaFilled(false);
        threethree = new JButton();threethree.setContentAreaFilled(false);
        threefour = new JButton();threefour.setContentAreaFilled(false);
        threefive = new JButton();threefive.setContentAreaFilled(false);
        threesix = new JButton();threesix.setContentAreaFilled(false);
        fourtwo = new JButton();fourtwo.setContentAreaFilled(false);
        fourthree = new JButton();fourthree.setContentAreaFilled(false);
        fourfour = new JButton();fourfour.setContentAreaFilled(false);
        fourfive = new JButton();fourfive.setContentAreaFilled(false);
        foursix = new JButton();foursix.setContentAreaFilled(false);
        fivetwo = new JButton();fivetwo.setContentAreaFilled(false);
        fivethree = new JButton();fivethree.setContentAreaFilled(false);
        fivefour = new JButton();fivefour.setContentAreaFilled(false);
        fivefive = new JButton();fivefive.setContentAreaFilled(false);
        fivesix = new JButton();fivesix.setContentAreaFilled(false);
        sixtwo = new JButton();sixtwo.setContentAreaFilled(false);
        sixthree = new JButton();sixthree.setContentAreaFilled(false);
        sixfour = new JButton();sixfour.setContentAreaFilled(false);
        sixfive = new JButton();sixfive.setContentAreaFilled(false);
        sixsix = new JButton();sixsix.setContentAreaFilled(false);
        seventwo = new JButton();seventwo.setContentAreaFilled(false);
        seventhree = new JButton();seventhree.setContentAreaFilled(false);
        sevenfour = new JButton();sevenfour.setContentAreaFilled(false);
        sevenfive = new JButton();sevenfive.setContentAreaFilled(false);
        sevensix = new JButton();sevensix.setContentAreaFilled(false);
        

        tablePanel.add(oneone);
        tablePanel.add(onetwo);
        tablePanel.add(onethree);
        tablePanel.add(onefour);
        tablePanel.add(onefive);
        tablePanel.add(onesix);
        tablePanel.add(twoone);
        tablePanel.add(twotwo);
        tablePanel.add(twothree);
        tablePanel.add(twofour);
        tablePanel.add(twofive);
        tablePanel.add(twosix);
        tablePanel.add(threeone);
        tablePanel.add(threetwo);
        tablePanel.add(threethree);
        tablePanel.add(threefour);
        tablePanel.add(threefive);
        tablePanel.add(threesix);
        tablePanel.add(fourone);
        tablePanel.add(fourtwo);
        tablePanel.add(fourthree);
        tablePanel.add(fourfour);
        tablePanel.add(fourfive);
        tablePanel.add(foursix);
        tablePanel.add(fiveone);
        tablePanel.add(fivetwo);
        tablePanel.add(fivethree);
        tablePanel.add(fivefour);
        tablePanel.add(fivefive);
        tablePanel.add(fivesix);
        tablePanel.add(sixone);
        tablePanel.add(sixtwo);
        tablePanel.add(sixthree);
        tablePanel.add(sixfour);
        tablePanel.add(sixfive);
        tablePanel.add(sixsix);
        tablePanel.add(sevenone);
        tablePanel.add(seventwo);
        tablePanel.add(seventhree);
        tablePanel.add(sevenfour);
        tablePanel.add(sevenfive);
        tablePanel.add(sevensix);
        
        tablePanel.setSize(1200, 700);
        tablePanel.setBorder(new TitledBorder("Timetable"));
        tablePanel.setBounds(0, 150, 1185, 605);

        tableFrame.add(settingPanel);
        tableFrame.add(tablePanel);
        tableFrame.setVisible(true);
    }

    /**
     * This method is to close the frame
     */
	public void destroy() {
        tableFrame.dispose();
    }

}