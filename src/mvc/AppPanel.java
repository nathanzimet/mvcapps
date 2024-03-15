package mvc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppPanel extends JPanel implements ActionListener, Subscriber {
    protected Model model;
    protected View view;
    protected JPanel controlPanel;
    protected AppFactory appFactory;
    protected JFrame frame;

    public static int FRAME_WIDTH = 500;
    public static int FRAME_HEIGHT = 300;

    // public JMenuBar menu;


    public AppPanel(AppFactory appFactory) {
        this.appFactory = appFactory;
        model = appFactory.makeModel();
        model.subscribe(this);
        view = appFactory.makeView(model);
        controlPanel = new JPanel();

        frame = new SafeFrame();
        Container cp = frame.getContentPane();
        cp.add(this);
        frame.setJMenuBar(createMenuBar());
        frame.setTitle(appFactory.getTitle());
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);

        this.setLayout(new GridLayout(1,2));
        this.add(controlPanel);
        this.add(view);
    }

    public void display() {
        frame.setVisible(true);
    }

    public void update() {  /* override in extensions if needed */ }

    public Model getModel() {
        return model;
    }

    public void setModel(Model newModel) {
        this.model.unsubscribe(this);
        this.model = newModel;
        this.model.subscribe(this);
        // view must also unsubscribe then resubscribe:
        view.setModel(this.model);
        model.changed();
    }

    public void actionPerformed(ActionEvent e) {
        String cmmd = e.getActionCommand();
        Object source = e.getSource();
        try {
            switch (cmmd) {
                case "Save":
                    Utilities.save(this.model, false);
                    break;
                case "SaveAs":
                    Utilities.save(this.model, true);
                    break;
                case "Open":
                    Model newmodel = Utilities.open(this.model);
                    if(newmodel != null) {
                        setModel(newmodel);
                    }
                    break;
                case "New":
                    Utilities.saveChanges(this.model);
                    setModel(appFactory.makeModel());
                    // needed cuz setModel sets to true:
                    this.model.setUnsavedChanges(false);
                    break;
                case "Quit":
                    Utilities.saveChanges(model);
                    System.exit(0);
                    break;
                case "About":
                    Utilities.inform(appFactory.about());
                    break;
                case "Help":
                    Utilities.inform(appFactory.getHelp());
                    break;
                default:
                    Command newCommand = appFactory.makeEditCommand(this.model, cmmd, source);
                    if (newCommand != null) {
                        newCommand.execute();
                    } else {
                        handleException(new Exception("Unrecognized command: " + cmmd));
                    }
            }

        } catch (Exception ex) {
            handleException(ex);
        }
    }
    protected void handleException(Exception e) {
        Utilities.error(e);
    }

    protected JMenuBar createMenuBar() {
        JMenuBar result = new JMenuBar();
        JMenu fileMenu = Utilities.makeMenu("File", new String[]{"New", "Save", "SaveAs", "Open", "Quit"}, this);
        result.add(fileMenu);
        JMenu editMenu = Utilities.makeMenu("Edit", appFactory.getEditCommands(), this);
        result.add(editMenu);
        JMenu helpMenu = Utilities.makeMenu("Help", new String[]{"About", "Help"}, this);
        result.add(helpMenu);
        return result;
    }
}
