package net.claflan.CheDul;

import net.claflan.CheDul.ui.MainWindow;

public class InterfaceHandler {

    private final EventHandler eH;
    
    private MainWindow mW;
    
    public InterfaceHandler(EventHandler eH) {
        this.eH = eH;
        initializeWindows();
    }
    
    private void initializeWindows() {
        mW = new MainWindow();
    }
    
    public void launch() {
        mW.setVisible(true);
    }
}
