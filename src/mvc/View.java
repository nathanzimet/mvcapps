package mvc;

import javax.swing.*;

public class View extends JPanel implements Subscriber {
    public Model model;

    public View(Model model) {
        this.model = model;
        this.model.subscribe(this);
    }

    public void update() {
        revalidate();
        repaint();
    }
    public void setModel(Model newModel) {
        model.unsubscribe(this);
        this.model = newModel;
        this.model.subscribe(this);
        repaint();
    }
}
