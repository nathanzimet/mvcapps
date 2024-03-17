package life;

import mvc.Command;

/**
 * @author priyankagoel
 */
public class ClearCommand extends Command {
    private Society society;
    public ClearCommand(Society society) {
        super(society);
        this.society = society;
    }
    public void execute() {
        society.repopulate(false);
    }
}

