package CALab;

//Nathan: 3/16/24 got professors code and made a first attempt at implementation.

import mvc.*;
import java.awt.*;

public class GridView  extends View {

    private CellView[][] cellViews;

    public GridView(Model model) {
        //Nathan: call View's constructor
        super(model);

        //Nathan: just for simplicity
        int d = ((Grid)model).getDim();

        this.setLayout(new GridLayout(d, d));
        cellViews = new CellView[d][d];

        for (int i = 0; i < d; i++) {
            for (int j = 0; j < d; j++) {
                cellViews[i][j] = new CellView(((Grid)model).getCell(i, j));
                this.add(cellViews[i][j]);

                //set cell.row and cell.col here;
                //Nathan: this is what the professor asked for, but it doesn't seem right...
                //((Grid)model).getCell(i, j).row = i;
                //((Grid)model).getCell(i, j).col = j;
            }
        }
    }

    public void update() {
        //Nathan: just for simplicity
        int d = ((Grid)model).getDim();

        // call update method of each CellView
        for (int i = 0; i < d; i++) {
            for (int j = 0; j < d; j++) {
                cellViews[i][j].update();
            }
        }
    }

}