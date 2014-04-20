package net.claflan.CheDul.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import net.claflan.CheDul.EventHandler;
import net.claflan.CheDul.logic.Schedule;
import net.claflan.CheDul.ui.sub.ViewControl;
import net.claflan.CheDul.ui.views.View;
import net.claflan.CheDul.ui.views.WeekView;

public class MainWindow extends JFrame implements ActionListener {

    private final EventHandler eH;
    private Schedule loadedSchedule;
    
    private JMenuBar menuBar;    
    private JMenu fileMenu, optionsMenu, helpMenu;
    private JMenuItem file_newSchedule, file_openSchedule, file_exit;
    
    private ViewControl viewControl;
    private View weekView;
    
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
        viewControl = new ViewControl(Calendar.getInstance(), this, eH);
        viewControl.setFloatable(false);
        
        weekView = new WeekView();
        viewControl.addView(weekView);
        
        add(viewControl, BorderLayout.NORTH);
    }
    
    public void loadSchedule(Schedule schedule) {
        loadedSchedule = schedule;
        weekView.setSchedule(schedule);
        JTable weekViewTable = weekView.getJTable();
        getContentPane().add(new JScrollPane(weekViewTable), BorderLayout.CENTER);
        viewControl.setControlsEnabled(true);
        viewControl.validateCalendar();
    }
    
    public Schedule getSchedule() {
        return loadedSchedule;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "MONTH":
                break;
            case "WEEK":
                break;
            case "DAY":
                break;
        }
    }
}
