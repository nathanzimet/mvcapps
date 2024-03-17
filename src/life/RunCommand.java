package life;

import mvc.Command;

/**
 * @author priyankagoel
 */
public class RunCommand extends Command {
    private int cycles;
    private Society society;
    public RunCommand(Society society, int cycles) {
        super(society);
        this.cycles = cycles;
        this.society = society;
    }
    public void execute() {
        this.society.updateLoop(this.cycles);
    }
}