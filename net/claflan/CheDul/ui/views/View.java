package net.claflan.CheDul.ui.views;

import java.util.Calendar;
import javax.swing.JPanel;

public abstract class View extends JPanel {

    public View() {
        this(Calendar.getInstance());
    }
    public View(Calendar calendar) {
        goToDate(calendar);
    }
    
    public abstract void previous();
    public abstract void next();
    public abstract void goToDate(Calendar calendar);
}
