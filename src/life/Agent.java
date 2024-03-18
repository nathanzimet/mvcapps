package life;

import CALab.Cell;

import java.util.Random;
import java.util.Set;

/**
 * @author priyankagoel
 *
 * 3/18/2024 Dexter: Added code to change the cells color as well as the cell status,
 *                   Revised update method so cells with ambiance of two stay the same
 *                   Wrote nextState method
 *
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
        Set<Cell> neighbours = getNeighbours();
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
            this.color = color.GREEN;
        }
        else if(Society.death.contains(this.ambience)) {
            this.status = 0;
            this.color = color.RED;
        }
        notifySubscribers();
    }

    @Override
    public void interact() {

    }

    @Override
    public int getStatus() {
        return this.status;
    }

    @Override
    public void nextState() {
        if(this.status == 0) {
            this.status = 1;
            this.color = color.GREEN;
        } else {
            this.status = 0;
            this.color = color.RED;
        }
    }

    @Override
    public void reset(boolean randomly) {
        if(randomly) {
            double p = Society.percentAlive/100.0;
            if(flip(p)) {
                this.status = 1;
                this.color = color.GREEN;
            } else {
                this.status = 0;
                this.color = color.RED;
            }

        } else {
            this.status = 0;
            this.color = color.RED;
        }
        notifySubscribers();
    }

    private boolean flip(double prob) {
        return random.nextDouble() < prob;
    }
}
