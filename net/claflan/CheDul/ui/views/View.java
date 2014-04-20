package net.claflan.CheDul.ui.views;

import java.util.Calendar;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import net.claflan.CheDul.logic.Schedule;

public abstract class View extends AbstractTableModel {

    protected boolean horizontalHeader;
    protected TableCellRenderer headerRenderer;
    protected TableCellRenderer dataRenderer;
    
    protected Schedule currentSchedule;
    protected Calendar targetedDate;
    private final int incrementType;
    private final int incrementValue;
    
    public View(int incrementType, int incrementValue) {
        this(Calendar.getInstance(), incrementType, incrementValue);
    }
    public View(Calendar calendar, int incrementType, int incrementValue) {
        this.incrementType = incrementType;
        this.incrementValue = incrementValue;
        goToDate(calendar);
    }
    
    public void setSchedule(Schedule schedule) {
        this.currentSchedule = schedule;
    }
    
    public final void previous() {
        targetedDate.add(incrementType, -incrementValue);
    }
    public final void next() {
        targetedDate.add(incrementType, incrementValue);
    }
    public final void goToDate(Calendar calendar){
        targetedDate = calendar;
        fireTableDataChanged();
    }
    
    public JTable getJTable() {
        JTable table = new JTable(this) {
            
            @Override
            public TableCellRenderer getCellRenderer(int row, int column) {
                if (horizontalHeader) {
                    return (row == 0) ? headerRenderer : dataRenderer;
                } else {
                    return (column == 0) ? headerRenderer : dataRenderer;
                }
            }
        };
        
        table.setTableHeader(null);
        table.setFillsViewportHeight(true);
        
        return table;
    }
}
