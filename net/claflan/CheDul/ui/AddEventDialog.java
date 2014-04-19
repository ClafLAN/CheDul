package net.claflan.CheDul.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import net.claflan.CheDul.constants.Month;
import net.claflan.CheDul.logic.Event;

public class AddEventDialog extends JDialog implements ActionListener, ItemListener {

    private JLabel instructionLabel, nameLabel, dateLabel, descriptionLabel;
    private JTextField nameField, yearField;
    private JTextArea descriptionArea;
    private JScrollPane descriptionScroll;
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
        setResizable(false);
        
        instructionLabel = new JLabel("<HTML>Fill out the following form to "
                + "add a new event.</HTML>");
        nameLabel = new JLabel("Name:");
        dateLabel = new JLabel("Date:");
        descriptionLabel = new JLabel("Description:");
        
        nameField = new JTextField(20);
        yearField = new JTextField(5);
        
        descriptionArea = new JTextArea(5, 1);
        descriptionScroll = new JScrollPane(descriptionArea);
        
        timeCheckBox = new JCheckBox("Time:");
        amButton = new JRadioButton("AM");
        pmButton = new JRadioButton("PM");
        
        monthBox = new JComboBox();
        dateBox = new JComboBox();
        hourBox = new JComboBox();
        minuteBox = new JComboBox();
        fillComboBoxes();
        
        hourBox.setEnabled(false);
        minuteBox.setEnabled(false);
        amButton.setEnabled(false);
        pmButton.setEnabled(false);
        
        ampmGroup = new ButtonGroup();
        ampmGroup.add(amButton);
        ampmGroup.add(pmButton);
        
        addButton = new JButton("Add");
        
        monthBox.addActionListener(this);
        yearField.addActionListener(this);
        timeCheckBox.addItemListener(this);
        
        Insets insets = new Insets(5, 5, 10, 5);
        add(instructionLabel, new GridBagConstraints(0, 0, 9, 1, 1, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, insets, 2, 2));
        
        insets = new Insets(2, 5, 2, 2);
        add(nameLabel, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, insets, 2, 2));
        add(dateLabel, new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, insets, 2, 2));
        add(descriptionLabel, new GridBagConstraints(0, 3, 1, 1, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, insets, 2, 2));
        
        insets = new Insets(2, 2, 2, 5);
        add(nameField, new GridBagConstraints(1, 1, 8, 1, 1, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, insets, 2, 2));
        add(pmButton, new GridBagConstraints(8, 2, 1, 1, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, insets, 2, 2));
        add(descriptionScroll, new GridBagConstraints(1, 3, 8, 1, 1, 1, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, insets, 2, 2));
        
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
    
    public void setComboBoxes(Calendar calendar) {
        
        monthBox.setSelectedIndex(calendar.get(Calendar.MONTH));
        int hour = calendar.get(Calendar.HOUR) - 1;
        hourBox.setSelectedIndex(hour != -1 ? hour : 11);
        minuteBox.setSelectedIndex(calendar.get(Calendar.MINUTE) - 1);
        yearField.setText(calendar.get(Calendar.YEAR) + "");
        
        dateBox.removeAllItems();
        for (int date = 1; date <= calendar.getActualMaximum(Calendar.DATE); date++)
            dateBox.addItem(date);
        dateBox.setSelectedIndex(calendar.get(Calendar.DATE) - 1);
        
        if (calendar.get(Calendar.AM_PM) == Calendar.AM)
            amButton.setSelected(true);
        else
            pmButton.setSelected(true);
    }
    
    private Calendar getRepresentativeCalendar() {
        Calendar calendar = new GregorianCalendar();
        
        calendar.set(Integer.parseInt(yearField.getText()), 
                monthBox.getSelectedIndex(), dateBox.getSelectedIndex() + 1, 
                hourBox.getSelectedIndex() + 1, 
                minuteBox.getSelectedIndex() + 1);
        
        return calendar;
    }
    
    public Event getEvent() {
        return new Event(nameField.getText(), descriptionArea.getText(), 
                getRepresentativeCalendar().getTime());
    }
    
    public void reset() {
        nameField.setText("");
        setComboBoxes(Calendar.getInstance());
    }
    
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == timeCheckBox) {
            hourBox.setEnabled(timeCheckBox.isSelected());
            minuteBox.setEnabled(timeCheckBox.isSelected());
            amButton.setEnabled(timeCheckBox.isSelected());
            pmButton.setEnabled(timeCheckBox.isSelected());
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == yearField) {
            try {
                Integer.parseInt(yearField.getText());
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "ERROR:  Year must be an integer value.", "Non-Numeric Year", JOptionPane.ERROR_MESSAGE);
                yearField.setText(Calendar.getInstance().get(Calendar.YEAR) + "");
                return;
            }
        }
        
        setComboBoxes(getRepresentativeCalendar());
    }
}
