package life;

import CALab.Cell;

import java.util.List;
import java.util.Random;

/**
 * @author priyankagoel
 */
public class Agent extends Cell {
    protected int status;
    protected int ambience;

    private Random random;

    public Agent(int row, int col) {
        super(row, col);
        this.status = 0;
        this.ambience = 8;
        this.random = new Random();
    }

    @Override
    public void observe() {
        int count = 0;
        List<Cell> neighbours = getNeighbours();
        for (Cell neighbour: neighbours) {
            Agent agent = (Agent) neighbour;
            if(agent.getStatus() == 1) {
                count++;
            }
        }
        this.ambience = count;
    }

    @Override
    public void update() {
        if(Society.rebirth.contains(this.ambience)) {
            this.status = 1;
        } else {
            this.status = 0;
        }
    }

    @Override
    public void interact() {

    }

    @Override
    public int getStatus() {
        return this.status;
    }

    @Override
    public void reset(boolean randomly) {
        if(randomly) {
            double p = Society.percentAlive/100.0;
            if(flip(p)) {
                this.status = 1;
            } else {
                this.status = 0;
            }

        } else {
            this.status = 0;
        }
    }

    private boolean flip(double prob) {
        return random.nextDouble() < prob;
    }
}
