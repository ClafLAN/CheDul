package net.claflan.CheDul.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Calendar;
import java.util.Date;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import net.claflan.CheDul.constants.Month;

/**
 * @author scipio
 */
public class AddEventDialog extends JDialog {

    private JLabel instructionLabel, nameLabel, dateLabel, descriptionLabel;
    private JTextField nameField, yearField;
    private JTextArea descriptionArea;
    private JComboBox monthBox, dateBox, hourBox, minuteBox;
    private JCheckBox timeCheckBox;
    private JRadioButton amButton, pmButton;
    private JButton addButton;
    
    private ButtonGroup ampmGroup;
    
    public AddEventDialog(JFrame parent) {
        super(parent, "Add a New Event");
        init(parent);
    }
    
    private void init(JFrame parent) {
        
        setLayout(new GridBagLayout());
        
        instructionLabel = new JLabel("<HTML>Fill out the following form to "
                + "add a new event.</HTML>");
        nameLabel = new JLabel("Name:");
        dateLabel = new JLabel("Date:");
        descriptionLabel = new JLabel("Description:");
        
        nameField = new JTextField(20);
        yearField = new JTextField(5);
        
        descriptionArea = new JTextArea();
        
        monthBox = new JComboBox();
        dateBox = new JComboBox();
        hourBox = new JComboBox();
        minuteBox = new JComboBox();
        fillComboBoxes();
        setComboBoxes(Calendar.getInstance().getTime());
        
        timeCheckBox = new JCheckBox("Time:");
        amButton = new JRadioButton("AM");
        pmButton = new JRadioButton("PM");
        
        addButton = new JButton("Add");
        
        Insets insets = new Insets(5, 5, 10, 5);
        add(instructionLabel, new GridBagConstraints(0, 0, 9, 1, 1, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, insets, 2, 2));
        
        insets = new Insets(2, 5, 2, 2);
        add(nameLabel, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, insets, 2, 2));
        add(dateLabel, new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, insets, 2, 2));
        add(descriptionLabel, new GridBagConstraints(0, 3, 1, 1, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, insets, 2, 2));
        
        insets = new Insets(2, 2, 2, 5);
        add(nameField, new GridBagConstraints(1, 1, 8, 1, 1, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, insets, 2, 2));
        add(pmButton, new GridBagConstraints(8, 2, 1, 1, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, insets, 2, 2));
        add(descriptionArea, new GridBagConstraints(1, 3, 8, 1, 1, 1, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, insets, 2, 2));
        
        insets = new Insets(2, 2, 2, 2);
        add(monthBox, new GridBagConstraints(1, 2, 1, 1, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, insets, 2, 2));
        add(dateBox, new GridBagConstraints(2, 2, 1, 1, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, insets, 2, 2));
        add(yearField, new GridBagConstraints(3, 2, 1, 1, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, insets, 2, 2));
        add(timeCheckBox, new GridBagConstraints(4, 2, 1, 1, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, insets, 2, 2));
        add(hourBox, new GridBagConstraints(5, 2, 1, 1, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, insets, 2, 2));
        add(minuteBox, new GridBagConstraints(6, 2, 1, 1, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, insets, 2, 2));
        add(amButton, new GridBagConstraints(7, 2, 1, 1, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, insets, 2, 2));
        
        insets = new Insets(2, 2, 5, 5);
        add(addButton, new GridBagConstraints(7, 4, 2, 1, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, insets, 2, 2));
        
        setModalityType(ModalityType.APPLICATION_MODAL);
        pack();
        setLocationRelativeTo(parent);
    }
    
    private void fillComboBoxes() {
        for (Month month: Month.values())
            monthBox.addItem(month);
        
        for (int hour = 1; hour <= 12; hour++)
            hourBox.addItem(hour);
        
        for (int minute = 1; minute <= 59; minute++)
            minuteBox.addItem(minute);
    }
    
    private void setComboBoxes(Date date) {
        
    }
    
    public static void main(String[] args) {
        JFrame temp = new JFrame("Temp");
        temp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        temp.setSize(500, 500);
        temp.setLocationRelativeTo(null);
        temp.setVisible(true);
        AddEventDialog aed = new AddEventDialog(temp);
        aed.setVisible(true);
    }
}
