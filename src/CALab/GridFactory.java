package CALab;

/**
Nathan: 3/16/24 Added the file, and let IntelliJ autocomplete methods required by the interface
Dexter: 3.17.2024 Wrote all of the methods
*/
import life.ClearCommand;
import life.PopulateCommand;
import life.RunCommand;
import life.Society;
import mvc.AppFactory;
import mvc.Command;
import mvc.Model;
import mvc.View;

public class GridFactory implements AppFactory {

    @Override
    public Model makeModel() {
        return new Society();
    }

    @Override
    public View makeView(Model m) {
        return new GridView(m);
    }

    @Override
    public String getTitle() {
        return "CA Lab";
    }

    @Override
    public String[] getHelp() {
        return new String[]{"RUN1: Moves time forward one step", "RUN50: Moves time forward 50 steps", "POPULATE: Sets the state of each cell to a random value", "CLEAR: Clears the state of all cells"};
    }

    @Override
    public String about() {
        return "A CA is an infinite grid of cells. Each cell, C, is a simple \"machine\" that \n might represent the behavior of a region, household, individual, etc. At any time t, C is in one of N states. \n States can be anything: temperature, wealth, political affiliation. At time t + 1, C may transition into another state. \n The new state is determined by the states of C and its neighbors at time t.";
    }

    @Override
    public String[] getEditCommands() {
        return new String[]{"RUN1", "RUN50", "POPULATE", "CLEAR"};
    }

    @Override
    public Command makeEditCommand(Model model, String type, Object source) {
        if (type.equals("RUN1")){
            return new RunCommand((Society)model, 1);
        }
        else if(type.equals("RUN50")){
            return new RunCommand((Society)model, 50);
        }
        else if(type.equals("POPULATE")){
            return new PopulateCommand((Society)model);
        }
        else if(type.equals("CLEAR")){
            return new ClearCommand((Society)model);
        }
        return null;
    }
}
