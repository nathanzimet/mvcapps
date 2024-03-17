package life;

import mvc.Command;

/**
 * @author priyankagoel
 */
public class PopulateCommand extends Command {
    private Society society;
    public PopulateCommand(Society society) {
        super(society);
        this.society = society;
    }
    public void execute() {
        society.repopulate(true);
    }
}
