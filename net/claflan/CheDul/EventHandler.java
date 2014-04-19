package net.claflan.CheDul;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class EventHandler implements ActionListener {
    
    private InterfaceHandler iH;
    
    void setInterfaceHandler(InterfaceHandler iH) {
        this.iH = iH;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String[] command = e.getActionCommand().split("\\s");
        switch(command[0]) {
            case "SHOW":
                iH.show(command[1]);
                break;
        }
    }
}
