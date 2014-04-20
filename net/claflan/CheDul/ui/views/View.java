package net.claflan.CheDul.ui.views;

import java.util.Calendar;
import javax.swing.table.AbstractTableModel;

public abstract class View extends AbstractTableModel {

    private Calendar targetedDate;
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
    
    public void previous() {
        targetedDate.add(incrementType, -incrementValue);
    }
    public void next() {
        targetedDate.add(incrementType, incrementValue);
    }
    public void goToDate(Calendar calendar){
        targetedDate = calendar;
    }
}
