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
    private JButton previousButton, nextButton, goButton, addEventButton;
    private JComboBox monthBox, dateBox;
    private JTextField yearField;
    
    public ViewControl(Calendar calendar, ActionListener viewChanger, ActionListener ui) {
        init(calendar, viewChanger, ui);
    }
    
    private void init(Calendar calendar, ActionListener viewChanger, ActionListener ui) {
        setLayout(new GridBagLayout());
        
        monthViewButton = new JButton("Month");
        weekViewButton = new JButton("Week");
        dayViewButton = new JButton("Day");
        
        previousButton = new JButton("Previous");
        nextButton = new JButton("Next");
        goButton = new JButton("Go");
        addEventButton = new JButton("Add Event");
        
        monthBox = new JComboBox(Month.values());
        dateBox = new JComboBox();
        
        yearField = new JTextField(5);
        
        addEventButton.setFocusable(false);
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
        
        addEventButton.setActionCommand("SHOW ADD_EVENT");
        addEventButton.addActionListener(ui);
        
        monthViewButton.setActionCommand("MONTH");
        monthViewButton.addActionListener(viewChanger);
        weekViewButton.setActionCommand("WEEK");
        weekViewButton.addActionListener(viewChanger);
        dayViewButton.setActionCommand("DAY");
        dayViewButton.addActionListener(viewChanger);
        
        Insets insets = new Insets(2, 2, 2, 0);
        int xValue = 0;
        add(addEventButton, new GridBagConstraints(xValue++, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL, insets, 5, 5));
        add(monthViewButton, new GridBagConstraints(xValue++, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL, insets, 5, 5));
        add(weekViewButton, new GridBagConstraints(xValue++, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL, insets, 5, 5));
        add(dayViewButton, new GridBagConstraints(xValue++, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL, insets, 5, 5));
        add(new JComponent(){}, new GridBagConstraints(xValue++, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL, insets, 5, 5));
        add(previousButton, new GridBagConstraints(xValue++, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL, insets, 5, 5));
        add(nextButton, new GridBagConstraints(xValue++, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL, insets, 5, 5));
        add(new JComponent(){}, new GridBagConstraints(xValue++, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL, insets, 5, 5));
        add(monthBox, new GridBagConstraints(xValue++, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL, insets, 5, 5));
        add(dateBox, new GridBagConstraints(xValue++, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL, insets, 5, 5));
        add(yearField, new GridBagConstraints(xValue++, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL, insets, 5, 5));
        add(goButton, new GridBagConstraints(xValue++, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL, insets, 5, 5));
        add(new JComponent(){}, new GridBagConstraints(xValue++, 0, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL, insets, 5, 5));
        
        monthBox.setSelectedIndex(calendar.get(Calendar.MONTH));
        yearField.setText(calendar.get(Calendar.YEAR) + "");
        validateCalendar();
        dateBox.setSelectedIndex(calendar.get(Calendar.DATE)- 1);
        
        monthBox.setActionCommand("UPDATE");
        monthBox.addActionListener(this);
        yearField.setActionCommand("UPDATE");
        yearField.addActionListener(this);
        
        setControlsEnabled(false);
    }
    
    public Calendar getRepresentativeCalendar() {
        return new GregorianCalendar(Integer.parseInt(yearField.getText()), 
                monthBox.getSelectedIndex(), dateBox.getSelectedIndex() + 1);
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
    
    public void addView(View view) {
        views.add(view);
    }
    
    public void setControlsEnabled(boolean enabled) {
        addEventButton.setEnabled(enabled);
        monthViewButton.setEnabled(enabled);
        weekViewButton.setEnabled(enabled);
        dayViewButton.setEnabled(enabled);
        previousButton.setEnabled(enabled);
        nextButton.setEnabled(enabled);
        goButton.setEnabled(enabled);
        monthBox.setEnabled(enabled);
        dateBox.setEnabled(enabled);
        yearField.setEnabled(enabled);
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
