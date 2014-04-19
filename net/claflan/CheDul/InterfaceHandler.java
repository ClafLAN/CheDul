package net.claflan.CheDul;

import net.claflan.CheDul.ui.AddEventDialog;
import net.claflan.CheDul.ui.MainWindow;
import net.claflan.CheDul.ui.NewScheduleDialog;

public final class InterfaceHandler {

    private final EventHandler eH;
    
    private MainWindow mW;
    private NewScheduleDialog nSD;
    private AddEventDialog aED;
    
    public InterfaceHandler(EventHandler eH) {
        this.eH = eH;
        eH.setInterfaceHandler(this);
        initializeWindows();
    }
    
    private void initializeWindows() {
        mW = new MainWindow(eH);
        nSD = new NewScheduleDialog(mW);
        aED = new AddEventDialog(mW);
    }
    
    void show(String identifier) {
        switch(identifier) {
            case "MAIN_WINDOW":
                mW.setVisible(true);
                break;
            case "NEW_SCHEDULE":
                nSD.reset();
                nSD.setVisible(true);
                break;
            case "ADD_EVENT":
                aED.reset();
                aED.setVisible(true);
                break;
        }
    }
}
