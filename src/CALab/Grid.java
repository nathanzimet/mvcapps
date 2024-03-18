package CALab;

import mvc.Model;

import java.io.Serializable;

import java.util.HashSet;

import java.util.Set;


/**
 * @author priyankagoel
 *
 * March 17th: Dexter added field radius
 */
public abstract class Grid extends Model implements Serializable {
    private int time;
    private int dim;
    private Cell[][] cells;
    private int radius = 1;

    public Grid() {
        this.time = 0;
        this.dim = 20;
        this.cells = new Cell[dim][dim];
        populate();
    }

    public int getDim() { return dim; }
    public int getTime() { return time; }
    public Cell getCell(int row, int col) {
        return cells[row][col];
    }

    protected void populate() {
        for(int i=0; i<this.dim; i++) {
            for(int j=0; j<this.dim; j++) {
                this.cells[i][j] = makeCell(i, j);
            }
        }
        for(int i=0; i<this.dim; i++) {
            for(int j=0; j<this.dim; j++) {
                cells[i][j].setNeighbours(this.getNeighbours(this.cells[i][j], radius));
            }
        }
    }

    protected abstract Cell makeCell(int row, int col);
    public void repopulate(boolean randomly) {
        //Random random = new Random();
        for(int i=0; i<this.dim; i++) {
            for(int j=0; j<this.dim; j++) {
                this.cells[i][j].reset(randomly);
            }
        }
        notifySubscribers();
    }
    public void updateLoop(int cycles) {
        observe();
        for(int cycle=0; cycle<cycles; cycle++) {
            interact();
            update();
            observe();
            time++;
            System.out.println("time = " + time);
        }
    }

    /**
     * Implemented by Nathan.
     */
    private Set<Cell> getNeighbours(Cell asker, int radius) {
        Set<Cell> neighbors = new HashSet<>();
        int i = asker.getRow();
        int j = asker.getCol();
        for (int m = i - radius; m <= i + radius; m++) {
            for (int n = j - radius; n <= j + radius; n++) {
                neighbors.add(cells[(m + dim) % dim][(n + dim) % dim]);
            }
        }
        neighbors.remove(cells[i][j]);
        return neighbors;
    }
    private void interact() {
        for(int i=0; i<this.dim; i++) {
            for(int j=0; j<this.dim; j++) {
                this.cells[i][j].interact();
            }
        }
    }

    private void update() {
        for(int i=0; i<this.dim; i++) {
            for(int j=0; j<this.dim; j++) {
                this.cells[i][j].update();
            }
        }
    }
    private void observe() {
        for(int i=0; i<this.dim; i++) {
            for(int j=0; j<this.dim; j++) {
                this.cells[i][j].observe();
            }
        }
        notifySubscribers();
    }
}
