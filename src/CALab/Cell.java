package CALab;

import mvc.Publisher;

import java.awt.*;
import java.io.Serializable;

import java.util.*;
import java.util.List;

/**
 * @author priyankagoel
 */
public abstract class Cell extends Publisher implements Serializable {
    private Grid myGrid;
    private int row;
    private int col;

    private Cell partner;

    private Set<Cell> neighbours;

    protected Color color;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.neighbours = new HashSet<>();
        this.color = Color.RED;
    }
    public void setNeighbours(Set<Cell> neighbours) {
        this.neighbours = neighbours;
    }

    protected Set<Cell> getNeighbours() {
         return this.neighbours;
    }

    public void choosePartner() {
        if (this.partner == null && getNeighbours() != null) {
            this.partner = null;
            List<Cell> neigh = new ArrayList<>(neighbours);
            Collections.shuffle(neigh);
            for(Cell cell : neigh) {
                if(cell.partner != null) {
                    this.partner = cell;
                    cell.partner = this;
                    break;
                }
            }
        }
    }

    public void unpartner() {
        if (this.partner != null) {
            if (this.partner.partner != null) {
                this.partner.partner = null;
            }
            this.partner = null;
        }
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

    public abstract void nextState();

    public abstract void reset(boolean randomly);

    public Color getColor() {
        return this.color;
    }
}
