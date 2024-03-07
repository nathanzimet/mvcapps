package mvc;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppPanel extends JPanel implements ActionListener, Subscriber {
    public Model model;
    public View view;
    public ControlPanel controlPanel;
    public AppFactory appFactory;

    public AppPanel(AppFactory appFactory) {
        this.appFactory = appFactory;
    }

    public void actionPerformed(ActionEvent e) {


    }
    public void update() {

    }

    public void display() {

    }
}
