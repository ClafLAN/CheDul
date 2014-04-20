 package net.claflan.CheDul.ui.views;
 
import java.awt.Component;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import net.claflan.CheDul.logic.Event;

public class WeekView extends View {
    
    public WeekView() {
        super(Calendar.DATE, 7);
        
        horizontalHeader = true;
        headerRenderer = new TableCellRenderer() {
            
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        dataRenderer = new TableCellRenderer() {
            
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        ArrayList<Event> events = new ArrayList();
        Iterator eventIterator = currentSchedule.getEvents().iterator();
        
        // Stuff goes here..
        
        while (eventIterator.hasNext()) {
            
        }
        
        return events;
    }
}