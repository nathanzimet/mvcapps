package stopLight;

import javax.swing.*;

import mvc.*;

import java.awt.*;


public class StopLightPanel extends AppPanel {
    private JButton change;
    public StopLightPanel(AppFactory factory) {
        super(factory);
        change = new JButton("Change");
        change.addActionListener(this);
        controlPanel.setBackground(Color.PINK);
        controlPanel.add(change);
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        AppFactory factory = new StoplightFactory();
        AppPanel panel = new StopLightPanel(factory);
        panel.display();
    }

}

