package minesweeperaixp;

import static minesweeperaixp.MinesweeperAIXP.boxes;
import static minesweeperaixp.MinesweeperAIXP.columnsY;
import static minesweeperaixp.MinesweeperAIXP.rowsX;

public class AI {

    public AI() {
    }

    public void numberedBoxCheck() {
        for (int y = 0; y < columnsY; y++) {
            for (int x = 0; x < rowsX; x++) {
                if (boxes[x][y].getStatus() == -1) {   //For Unchecked Boxes
                }
            }
        }
    }
}
