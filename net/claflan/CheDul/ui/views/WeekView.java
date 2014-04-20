 package net.claflan.CheDul.ui.views;
 
import java.awt.Component;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
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
                JLabel dateLabel = new JLabel(headerStrings.get(1));
                
                headerPane.add(dateLabel);
                headerPane.add(dayLabel);
                
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
        dayOfColumn.add(Calendar.DATE, -dayOfColumn.get(Calendar.DAY_OF_WEEK) + columnIndex);
        
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