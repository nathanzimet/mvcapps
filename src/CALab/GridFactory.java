package CALab;

//Nathan: 3/16/24 Added the file, and let IntelliJ autocomplete methods required by the interface

import mvc.AppFactory;
import mvc.Command;
import mvc.Model;
import mvc.View;

public class GridFactory implements AppFactory {

    @Override
    public Model makeModel() {
        return null;
    }

    @Override
    public View makeView(Model m) {
        return null;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public String[] getHelp() {
        return new String[0];
    }

    @Override
    public String about() {
        return null;
    }

    @Override
    public String[] getEditCommands() {
        return new String[0];
    }

    @Override
    public Command makeEditCommand(Model model, String type, Object source) {
        return null;
    }
}
