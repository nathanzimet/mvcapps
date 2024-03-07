package mvc;

import javax.swing.*;
import java.awt.*;

public class View extends JPanel implements Subscriber {
    public Model model;

    public View(Model model) {
        this.model = model;
    }

    public void update() {

    }
}
