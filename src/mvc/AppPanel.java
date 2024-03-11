package mvc;

import stopLight.ChangeCommand;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class AppPanel extends JPanel implements ActionListener, Subscriber {
    public Model model;
    public View view;
    public ControlPanel controlPanel;
    public AppFactory appFactory;
    public String[] editCommands;
    public String about;
    public String[] help;
    public String title;
    public JMenuBar menu;


    public AppPanel(AppFactory appFactory) {
        this.appFactory = appFactory;
        model = appFactory.makeModel();
        view = appFactory.makeView(model);
        controlPanel = new ControlPanel();
        model.subscribe(view);

        editCommands = appFactory.getEditCommands();
        help = appFactory.getHelp();
        about = appFactory.about();
        title = appFactory.getTitle();

        //appFactory.makeEditCommand(model, editCommands, this );

        this.setLayout(new GridLayout(1,2));
        this.add(controlPanel);
        this.add(view);

        menu = new JMenuBar();
        JMenu fileMenu = Utilities.makeMenu("File", new String[]{"New", "Save", "Open", "Quit"}, this);
        menu.add(fileMenu);
        JMenu editMenu = Utilities.makeMenu("Edit", editCommands, this);
        menu.add(editMenu);
        JMenu helpMenu = Utilities.makeMenu("Help", new String[]{"Help", "About"}, this);
        menu.add(helpMenu);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container cp = frame.getContentPane();
        cp.add(this);
        frame.setJMenuBar(menu);
        frame.setTitle(title);
        frame.setSize(500, 500);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String cmmd = e.getActionCommand();
        try {
            switch (cmmd) {
                case "Save": {
                    String fName = Utilities.getFileName((String) null, false);
                    ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fName));
                    //            os.writeObject(this.light);
                    os.close();
                    break;
                }

                case "Open": {

                    if (Utilities.confirm("Are you sure? Unsaved changes will be lost!")) {
                        String fName = Utilities.getFileName((String) null, true);
                        ObjectInputStream is = new ObjectInputStream(new FileInputStream(fName));
                        //           light = (Stoplight) is.readObject();
                        //           view.setLight(light);
                        is.close();
                    }

                    break;

                }

                case "New": {
                    //            light = new Stoplight();
                    //            view.setLight(light);
                    break;
                }

                case "Quit": {
                    System.exit(0);
                    break;
                }

                case "About": {
                    Utilities.inform(about);
                    break;
                }

                case "Help": {
                    Utilities.inform(help);
                    break;


                }

                default: {
                    Command newCommand = appFactory.makeEditCommand(model, cmmd, this);
                    if (newCommand != null){
                        newCommand.execute();
                    }
                    else {
                        System.out.println("Apppanel action performed is broken, figure out how to throw exception");
                        throw new Exception("Unrecognized command: " + cmmd);
                    }
                }
            }

        } catch (Exception ex) {
            Utilities.error(ex);
        }


    }
    public void update() {

    }

    public void display() {

    }
}
