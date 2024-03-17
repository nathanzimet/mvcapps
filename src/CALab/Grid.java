package CALab;

import mvc.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * @author priyankagoel
 */
public abstract class Grid extends Model implements Serializable {

    private static final int[] ROW_OFFSET = new int[]{-1, -1, -1, 0, 0, 1, 1, 1};
    private static final int[] COL_OFFSET = new int[]{-1, 0, 1, -1, 1, -1, 0, 1};
    private int time;
    private int dim;
    private Cell[][] cells;

    public Grid() {
        this.time = 0;
        this.dim = 20;
        this.cells = new Cell[dim][dim];
        populate();

    }

    protected void populate() {
        for(int i=0; i<this.dim; i++) {
            for(int j=0; j<this.dim; j++) {
                this.cells[i][j] = makeCell(i, j);
            }
        }
        for(int i=0; i<this.dim; i++) {
            for(int j=0; j<this.dim; j++) {
                cells[i][j].setNeighbours(this.getNeighbours(this.cells[i][j]));
            }
        }

    }

    protected abstract Cell makeCell(int row, int col);
    protected void repopulate(boolean randomly) {
        //Random random = new Random();
        for(int i=0; i<this.dim; i++) {
            for(int j=0; j<this.dim; j++) {
                this.cells[i][j].reset(randomly);
            }
        }
    }
    protected void updateLoop(int cycles) {
        observe();
        for(int cycle=0; cycle<cycles; cycle++) {
            interact();
            update();
            observe();
            time++;
            System.out.println("time = " + time);
        }
    }
    private List<Cell> getNeighbours(Cell asker) {
        List<Cell> neighbours = new ArrayList<>();
        int row = asker.getRow();
        int col = asker.getCol();
        for(int k=0; k<8; k++) {
            int nRow = (row + ROW_OFFSET[k] + dim) % dim;
            int nCol = (col + COL_OFFSET[k] + dim) % dim;
            neighbours.add(this.cells[nRow][nCol]);
        }
        return neighbours;
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
    }


}
