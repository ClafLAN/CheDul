package net.claflan.CheDul.ui;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainWindow extends JFrame {

    private JMenuBar menuBar;    
    private JMenu fileMenu, optionsMenu, helpMenu;
    private JMenuItem file_newSchedule, file_openSchedule, file_exit;
    
    public MainWindow() {
        super("CheDul");
        init();
    }
    
    private void init() {
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        menuBar = new JMenuBar();
        
        fileMenu = new JMenu("File");
        
        file_newSchedule = new JMenuItem("New Schedule");
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
        
        setVisible(true);
    }
}
