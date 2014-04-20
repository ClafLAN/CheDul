package net.claflan.CheDul.ui.sub;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import net.claflan.CheDul.constants.Month;
import net.claflan.CheDul.ui.views.View;

public class ViewControl extends JToolBar implements ActionListener {

    private final ArrayList<View> views = new ArrayList();
    
    private JButton monthViewButton, weekViewButton, dayViewButton;
    private JButton previousButton, nextButton, goButton;
    private JComboBox monthBox, dateBox;
    private JTextField yearField;
    
    public ViewControl(Calendar calendar, ActionListener viewChanger) {
        init(calendar, viewChanger);
    }
    
    private void init(Calendar calendar, ActionListener viewChanger) {
        setLayout(new GridBagLayout());
        
        monthViewButton = new JButton("Month");
        weekViewButton = new JButton("Week");
        dayViewButton = new JButton("Day");
        
        previousButton = new JButton("Previous");
        nextButton = new JButton("Next");
        goButton = new JButton("Go");
        
        monthBox = new JComboBox(Month.values());
        dateBox = new JComboBox();
        
        yearField = new JTextField(5);
        
        monthViewButton.setFocusable(false);
        weekViewButton.setFocusable(false);
        dayViewButton.setFocusable(false);
        previousButton.setFocusable(false);
        nextButton.setFocusable(false);
        goButton.setFocusable(false);
        monthBox.setFocusable(false);
        dateBox.setFocusable(false);
        
        previousButton.setActionCommand("PREVIOUS");
        previousButton.addActionListener(this);
        nextButton.setActionCommand("NEXT");
        nextButton.addActionListener(this);
        goButton.setActionCommand("GO");
        goButton.addActionListener(this);
        
        monthViewButton.setActionCommand("MONTH");
        monthViewButton.addActionListener(viewChanger);
        weekViewButton.setActionCommand("WEEK");
        weekViewButton.addActionListener(viewChanger);
        dayViewButton.setActionCommand("DAY");
        dayViewButton.addActionListener(viewChanger);
        
        Insets insets = new Insets(2, 2, 2, 0);
        add(monthViewButton, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL, insets, 5, 5));
        add(weekViewButton, new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL, insets, 5, 5));
        add(dayViewButton, new GridBagConstraints(2, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL, insets, 5, 5));
        add(new JComponent(){}, new GridBagConstraints(3, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL, insets, 5, 5));
        add(previousButton, new GridBagConstraints(4, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL, insets, 5, 5));
        add(nextButton, new GridBagConstraints(5, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL, insets, 5, 5));
        add(new JComponent(){}, new GridBagConstraints(6, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL, insets, 5, 5));
        add(monthBox, new GridBagConstraints(7, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL, insets, 5, 5));
        add(dateBox, new GridBagConstraints(8, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL, insets, 5, 5));
        add(yearField, new GridBagConstraints(9, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL, insets, 5, 5));
        add(goButton, new GridBagConstraints(10, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL, insets, 5, 5));
        add(new JComponent(){}, new GridBagConstraints(11, 0, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL, insets, 5, 5));
        
        monthBox.setSelectedIndex(calendar.get(Calendar.MONTH));
        yearField.setText(calendar.get(Calendar.YEAR) + "");
        validateCalendar();
        dateBox.setSelectedIndex(calendar.get(Calendar.DATE)- 1);
        
        monthBox.setActionCommand("UPDATE");
        monthBox.addActionListener(this);
        yearField.setActionCommand("UPDATE");
        yearField.addActionListener(this);
    }
    
    public Calendar getRepresentativeCalendar() {
        return new GregorianCalendar(Integer.parseInt(yearField.getText()), 
                monthBox.getSelectedIndex() + 1, dateBox.getSelectedIndex() + 1);
    }
    
    public void validateCalendar() {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.set(Calendar.YEAR, Integer.parseInt(yearField.getText()));
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, "Year values must be numeric.", "Non-Numeric Year", JOptionPane.ERROR_MESSAGE);
            yearField.setText(calendar.get(Calendar.YEAR) + "");
        }
        calendar.set(Calendar.MONTH, monthBox.getSelectedIndex());
        int oldDate = dateBox.getSelectedIndex();
        
        dateBox.removeAllItems();
        for (int date = 1; date <= calendar.getActualMaximum(Calendar.DATE); date++)
            dateBox.addItem(date);
        
        if (dateBox.getItemCount() > oldDate)
            dateBox.setSelectedIndex(oldDate);
        else
            dateBox.setSelectedIndex(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "PREVIOUS":
                for (View view : views)
                    view.previous();
                break;
            case "NEXT":
                for (View view : views)
                    view.next();
                break;
            case "GO":
                for (View view : views)
                    view.goToDate(getRepresentativeCalendar());
                break;
            case "UPDATE":
                validateCalendar();
                break;
        }
    }
}
