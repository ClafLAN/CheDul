package net.claflan.CheDul.ui.sub;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import net.claflan.CheDul.constants.Month;
import net.claflan.CheDul.ui.views.View;

public class ViewControl extends JToolBar implements ActionListener {

    private final ArrayList<View> views = new ArrayList();
    
    private JButton previousButton, nextButton, goButton;
    private JComboBox monthBox, dateBox;
    private JTextField yearField;
    
    public ViewControl() {
        init();
    }
    
    private void init() {
        setLayout(new GridBagLayout());
        
        previousButton = new JButton("Previous");
        nextButton = new JButton("Next");
        goButton = new JButton("Go");
        monthBox = new JComboBox(Month.values());
        dateBox = new JComboBox();
        yearField = new JTextField(5);
        
        previousButton.setFocusable(false);
        nextButton.setFocusable(false);
        goButton.setFocusable(false);
        monthBox.setFocusable(false);
        dateBox.setFocusable(false);
        
        Insets insets = new Insets(2, 2, 2, 0);
        add(previousButton, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL, insets, 5, 5));
        add(nextButton, new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL, insets, 5, 5));
        add(new JComponent(){}, new GridBagConstraints(2, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL, insets, 5, 5));
        add(monthBox, new GridBagConstraints(3, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL, insets, 5, 5));
        add(dateBox, new GridBagConstraints(4, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL, insets, 5, 5));
        add(yearField, new GridBagConstraints(5, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL, insets, 5, 5));
        add(goButton, new GridBagConstraints(6, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL, insets, 5, 5));
        add(new JComponent(){}, new GridBagConstraints(7, 0, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL, insets, 5, 5));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
