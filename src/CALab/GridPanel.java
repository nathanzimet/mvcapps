package CALab;

/**
 * Nathan: created the file, started constructor
 *
 * 3/18/2024 Dexter wrote all of AppPanel, created all of the buttons
 */

import life.ClearCommand;
import mvc.AppFactory;
import mvc.AppPanel;
import stopLight.StopLightPanel;

import javax.swing.*;
import java.awt.*;

public class GridPanel extends AppPanel {

    //Nathan:
    private JButton run1;
    private JButton run50;
    private JButton populate;
    private JButton clear;

    public GridPanel(AppFactory appFactory) {
        super(appFactory);
        controlPanel.setLayout(new GridLayout(2,2));

        run1 = new JButton("RUN1");
        run1.addActionListener(this);
        run50 = new JButton(("RUN50"));
        run50.addActionListener(this);
        populate = new JButton("POPULATE");
        populate.addActionListener(this);
        clear = new JButton("CLEAR");
        clear.addActionListener(this);

        JPanel run1Panel = new JPanel();
        run1Panel.add(run1);
        controlPanel.add(run1Panel);

        JPanel run50Panel = new JPanel();
        run50Panel.add(run50);
        controlPanel.add(run50Panel);

        JPanel populatePanel = new JPanel();
        populatePanel.add(populate);
        controlPanel.add(populatePanel);

        JPanel clearPanel = new JPanel();
        clearPanel.add(clear);
        controlPanel.add(clearPanel);




    }

    public static void main(String[] args){
        AppFactory factory = new GridFactory();
        AppPanel panel = new GridPanel(factory);
        panel.display();



    }
}
