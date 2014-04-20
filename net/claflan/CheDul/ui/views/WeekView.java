 package net.claflan.CheDul.ui.views;
 
import java.awt.Component;
import java.awt.Font;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;
import net.claflan.CheDul.constants.Day;
import net.claflan.CheDul.logic.Event;

public class WeekView extends View {
    
    public WeekView() {
        super(Calendar.DATE, 7);
        
        horizontalHeader = true;
        headerRenderer = new TableCellRenderer() {
            
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JPanel headerPane = new JPanel();
                headerPane.setLayout(new BoxLayout(headerPane, BoxLayout.PAGE_AXIS));
                
                ArrayList<String> headerStrings = (ArrayList<String>) value;
                JLabel dayLabel = new JLabel(headerStrings.get(0));
                dayLabel.setFont(new Font("Dialog", Font.BOLD, 16));
                dayLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
                dayLabel.setAlignmentX(Box.RIGHT_ALIGNMENT);
                JLabel dateLabel = new JLabel(headerStrings.get(1));
                dateLabel.setFont(new Font("Dialog", Font.PLAIN, 10));
                dateLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
                dateLabel.setAlignmentX(Box.RIGHT_ALIGNMENT);
                
                headerPane.add(dateLabel);
                headerPane.add(dayLabel);
                
                int oldHeight = (column == 0) ? 0 : table.getRowHeight(0);
                int newHeight = headerPane.getPreferredSize().height;
            
                if (newHeight > oldHeight)
                    table.setRowHeight(0, newHeight);
                
                return headerPane;
            }
        };
        dataRenderer = new TableCellRenderer() {
            
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JPanel dataPane = new JPanel();
                dataPane.setLayout(new BoxLayout(dataPane, BoxLayout.PAGE_AXIS));
                
                ArrayList<Event> events = (ArrayList<Event>) value;
                for (Event event : events) {
                    JLabel eventLabel = new JLabel(event.getName());
                    dataPane.add(eventLabel);
                }
                
                int oldHeight = (column == 0) ? 0 : table.getRowHeight(1);
                int firstRowHeight = table.getRowHeight(0);
                int proposedHeight = dataPane.getPreferredSize().height;
                int heightOfParent = table.getParent().getSize().height;
            
                int newHeight = (proposedHeight > heightOfParent) ? proposedHeight : heightOfParent - firstRowHeight;
                if (newHeight > oldHeight)
                    table.setRowHeight(1, newHeight);
                
                return dataPane;
            }
        };
    }

    @Override
    public int getRowCount() {
        return 2;
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Calendar dayOfColumn = (Calendar) targetedDate.clone();
        dayOfColumn.add(Calendar.DATE, -dayOfColumn.get(Calendar.DAY_OF_WEEK) + columnIndex + 1);
        
        if (rowIndex == 0) {
            ArrayList<String> headerStrings = new ArrayList();
            headerStrings.add(Day.values()[dayOfColumn.get(Calendar.DAY_OF_WEEK) - 1].toString());
            headerStrings.add(DateFormat.getDateInstance(DateFormat.LONG).format(dayOfColumn.getTime()));
            return headerStrings;
        } else {
            ArrayList<Event> events = new ArrayList();
            
            for (Event current : currentSchedule.getEvents()) {
                Calendar eventDate = current.getDueDate();
                if (dayOfColumn.get(Calendar.YEAR) == eventDate.get(Calendar.YEAR) &&
                        dayOfColumn.get(Calendar.MONTH) == eventDate.get(Calendar.MONTH) &&
                        dayOfColumn.get(Calendar.DATE) == eventDate.get(Calendar.DATE)) {
                    events.add(current);
                }
            }
        
            return events;
        }
    }
}