package CALab;

import mvc.Publisher;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author priyankagoel
 */
public abstract class Cell extends Publisher implements Serializable {
    private Grid myGrid;
    private int row;
    private int col;

    private List<Cell> neighbours;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.neighbours = new ArrayList<>();
    }
    public void setNeighbours(List<Cell> neighbours) {
        this.neighbours = neighbours;
    }

    protected List<Cell> getNeighbours() {
         return this.neighbours;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
    public abstract void observe();
    public abstract void update();
    public abstract void interact();
    public abstract int getStatus();

    public abstract void reset(boolean randomly);
}
