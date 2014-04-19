 package net.claflan.CheDul.ui.views;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.*;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;


public class MonthView extends JPanel{
	static JLabel months, changeyear;
	static JButton previous, next;
	static JTable Calendar;
	static JComboBox<String> Year;
	static Container pane;
	static DefaultTableModel MCalendar; // Table model
	static JScrollPane CalendarScroll; // The scroll pane
	static JPanel Calendarpanel;
	private static int year, month, date;
	
	GregorianCalendar cal = new GregorianCalendar(); // Create calendar

	
	public MonthView(){
		super();
		year = cal.get(GregorianCalendar.YEAR);  
		month= cal.get(GregorianCalendar.MONTH);
		date = cal.get(GregorianCalendar.DATE);
		
		createCalendar();
	}
	
	public MonthView(int month, int date, int year){
		super();
		MonthView.month=month;
		MonthView.date= date;
		MonthView.year = year;
		
		createCalendar();
	};

	public void createCalendar() {

		// Create controls
		months = new JLabel("January");
		changeyear = new JLabel("Change year:");
		Year = new JComboBox<String>();
		previous = new JButton("previous");
		next = new JButton("next");
		MCalendar = new DefaultTableModel() {
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};
		Calendar = new JTable(MCalendar);
		CalendarScroll = new JScrollPane(Calendar);
		Calendarpanel = new JPanel(null);


		// Register action listeners
		previous.addActionListener(new Previous_Button());
		next.addActionListener(new Next_Button());
		Year.addActionListener(new Next_Year());

		// Add controls to pane
		Calendarpanel.add(months);
		Calendarpanel.add(changeyear);
		Calendarpanel.add(Year);
		Calendarpanel.add(previous);
		Calendarpanel.add(next);
		Calendarpanel.add(CalendarScroll);

		// Set bounds
		Calendarpanel.setBounds(10, 0, 600, 600);
		months.setBounds(160 - months.getPreferredSize().width / 2, 25, 350, 25); // Re-align label with calendar
		changeyear.setBounds(20, 500, 80, 20);
		Year.setBounds(400, 500, 80, 20);
		previous.setBounds(20, 25, 90, 25);
		next.setBounds(400, 25, 90, 25);
		CalendarScroll.setBounds(10, 50, 550, 400);

		// Make frame visible
	
		Calendarpanel.setVisible(true);

		
		String[] headers = { "Sunday", "Monday", "Tuesday", "Wednesday",
				"Thursday", "Friday", "Saturday" };
		for (int i = 0; i < 7; i++) {
			MCalendar.addColumn(headers[i]);
		}

		Calendar.getParent().setBackground(Calendar.getBackground()); // Set
																		// background

		// No resize/reorder
		Calendar.getTableHeader().setResizingAllowed(false);
		Calendar.getTableHeader().setReorderingAllowed(false);

		// Single cell selection
		Calendar.setColumnSelectionAllowed(true);
		Calendar.setRowSelectionAllowed(true);
		MCalendar.setColumnCount(7);
		MCalendar.setRowCount(6);

		// Populate table
		for (int i = year - 100; i <= year + 100; i++) {
			Year.addItem(String.valueOf(i));
		}

		// Refresh calendar
		refreshCalendar(month, year);// Refresh calendar
	
	}
	
	public static void refreshCalendar(int month, int year) {
		String[] mon = { "January", "February", "March", "April", "May",
				"June", "July", "August", "September", "October", "November",
				"December" };
		int day, start; // Number Of Days, Start Of Month

		// Allow/disallow buttons
		previous.setEnabled(true);
		next.setEnabled(true);

		if (month == 0 && year <= year - 10) {
			previous.setEnabled(false);
		} // Too early
		if (month == 11 && year >= year + 100) {
			next.setEnabled(false);
		} // Too late
		months.setText(mon[month]); // Refresh the month label (at the top)
		months.setBounds(160 - months.getPreferredSize().width / 2, 25, 180, 25); // Re-align
																					// label
																					// with
																					// calendar
		Year.setSelectedItem(String.valueOf(year)); // Select the correct year
													// in the combo box

		// Get first day of month and number of days
		GregorianCalendar calendar = new GregorianCalendar(year, month, 1);
		day = calendar.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		start = calendar.get(GregorianCalendar.DAY_OF_WEEK);

		// Draw calendar
		for (int i = 1; i <= day; i++) {
			int row = new Integer((i + start - 2) / 7);
			int column = (i + start - 2) % 7;
			MCalendar.setValueAt(i, row, column);
		}
	}

	static class Previous_Button implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (month == 0) { // Back one year
				month = 11;
				year -= 1;
			} else { // Back one month
				month -= 1;
			}
			refreshCalendar(month, year);
	}
	}

		static class Next_Button implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (month == 11) { // Forward one year
				month = 0;
				month += 1;
			} else { // Forward one month
				month += 1;			}
			refreshCalendar(month, year);
		}
	}

	static class Next_Year implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		if (Year.getSelectedItem() != null) {
			String b = Year.getSelectedItem().toString();
			year = Integer.parseInt(b);			
			refreshCalendar(month, year);
		}
	}
}
	public static void main(String[] args){
		MonthView mview = new MonthView();
		mview.createCalendar();
		
		
	}
}