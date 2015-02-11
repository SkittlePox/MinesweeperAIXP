package minesweeperaixp;

import static minesweeperaixp.MinesweeperAIXP.boxes;
import static minesweeperaixp.MinesweeperAIXP.columnsY;
import static minesweeperaixp.MinesweeperAIXP.rowsX;

public class AI {

    public AI() {

    }

    public void scan() {    //Scans the board to give precentages
        for (int b = 0; b < columnsY; b++) {
            for (int a = 0; a < rowsX; a++) {
                if (boxes[a][b].getStatus() != -1) {
                    
                }
            }
        }
    }
}
