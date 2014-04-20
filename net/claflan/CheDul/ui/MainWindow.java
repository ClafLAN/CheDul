package net.claflan.CheDul.ui;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import net.claflan.CheDul.EventHandler;
import net.claflan.CheDul.ui.sub.ViewControl;

public class MainWindow extends JFrame {

    private final EventHandler eH;
    
    private JMenuBar menuBar;    
    private JMenu fileMenu, optionsMenu, helpMenu;
    private JMenuItem file_newSchedule, file_openSchedule, file_exit;
    
    private ViewControl viewControl;
    
    public MainWindow(EventHandler eH) {
        super("CheDul");
        this.eH = eH;
        init();
    }
    
    private void init() {
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        //  Create JMenu
        menuBar = new JMenuBar();
        
        fileMenu = new JMenu("File");
        
        file_newSchedule = new JMenuItem("New Schedule");
        file_newSchedule.setActionCommand("SHOW NEW_SCHEDULE");
        file_newSchedule.addActionListener(eH);
        fileMenu.add(file_newSchedule);
        file_openSchedule = new JMenuItem("Open Schedule");
        fileMenu.add(file_openSchedule);
        fileMenu.addSeparator();
        file_exit = new JMenuItem("Exit");
        fileMenu.add(file_exit);
        menuBar.add(fileMenu);
        
        optionsMenu = new JMenu("Options");
        menuBar.add(optionsMenu);
        
        helpMenu = new JMenu("Help");
        menuBar.add(helpMenu);
        
        setJMenuBar(menuBar);
        
        //Add ToolBar
        viewControl = new ViewControl();
        add(viewControl, BorderLayout.NORTH);
    }
}
