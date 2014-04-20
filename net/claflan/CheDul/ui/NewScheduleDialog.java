package net.claflan.CheDul.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import net.claflan.CheDul.logic.Schedule;

public class NewScheduleDialog extends JDialog implements ActionListener {
        
    private final MainWindow mW;
    
    private File chosenFile;
    private JFileChooser fileChooser;
    
    private JLabel instructionLabel, fileLabel, nameLabel, ownerLabel;
    private JTextField fileField, nameField, ownerField;
    private JButton fileButton, createButton;

    public NewScheduleDialog(MainWindow mW) {
        super(mW, "Create New Schedule");
        this.mW = mW;
        init();
    }
    
    private void init() {
        setLayout(new GridBagLayout());
        setResizable(false);
        
        instructionLabel = new JLabel("<HTML>Complete the following to create a new schedule.</HTML>");
        fileLabel = new JLabel("File Location:");
        nameLabel = new JLabel("Schedule Name:");
        ownerLabel = new JLabel("Owner:");
        
        fileField = new JTextField(30);
        fileField.setFocusable(false);
        nameField = new JTextField(10);
        nameField.addActionListener(this);
        ownerField = new JTextField(10);
        ownerField.addActionListener(this);
        
        fileButton = new JButton("Browse");
        fileButton.addActionListener(this);
        createButton = new JButton("Create");
        createButton.setActionCommand("LOAD");
        createButton.addActionListener(this);
        createButton.setEnabled(false);
        
        Insets insets = new Insets(5, 5, 10, 5);
        add(instructionLabel, new GridBagConstraints(0, 0, 5, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 5, 5));
        
        insets = new Insets(2, 5, 2, 2);
        add(fileLabel, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 5, 5));
        add(nameLabel, new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 5, 5));
        
        insets = new Insets(2, 2, 2, 2);
        add(fileField, new GridBagConstraints(1, 1, 3, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 5, 5));
        add(nameField, new GridBagConstraints(1, 2, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 5, 5));
        add(ownerLabel, new GridBagConstraints(2, 2, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 5, 5));
        
        insets = new Insets(2, 2, 5, 2);
        add(ownerField, new GridBagConstraints(3, 2, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 5, 5));
        
        insets = new Insets(2, 2, 2, 5);
        add(fileButton, new GridBagConstraints(4, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 5, 5));
        
        insets = new Insets(2, 2, 5, 5);
        add(createButton, new GridBagConstraints(4, 2, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 5, 5));
        
        fileChooser = new JFileChooser();
        
        setModalityType(ModalityType.APPLICATION_MODAL);
        pack();
        setLocationRelativeTo(mW);
    }
    
    public Schedule getSchedule() {
        return new Schedule(chosenFile);
    }
    
    public void reset() {
        chosenFile = null;
        fileField.setText("");
        nameField.setText("");
    }
    
    @Override
    public void repaint() {
        boolean file = fileField.getText().equals("");
        boolean name = nameField.getText().equals("");
        boolean owner = ownerField.getText().equals("");
        
        if (file || name || owner)
            createButton.setEnabled(false);
        else
            createButton.setEnabled(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == fileButton) {
            int decision = fileChooser.showSaveDialog(this);

            if (decision == JFileChooser.APPROVE_OPTION) {
                chosenFile = fileChooser.getSelectedFile();
                fileField.setText(chosenFile.getAbsolutePath());
            } else
                fileField.setText("");
        } else if (e.getActionCommand().equals("LOAD")) {
            mW.loadSchedule(getSchedule());
            dispose();
        }
        
        repaint();
    }
}
