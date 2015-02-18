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
                    boxes[x][y].setChance(0);
                    if (boxes[x][y].getNorthWest() != 10) {
                        boxes[x][y].addChance(boxes[x - 1][y - 1].getChancePerBox());
                    }
                    if (boxes[x][y].getNorth() != 10) {
                        boxes[x][y].addChance(boxes[x][y - 1].getChancePerBox());
                    }
                    if (boxes[x][y].getNorthEast() != 10) {
                        boxes[x][y].addChance(boxes[x + 1][y - 1].getChancePerBox());
                    }
                    if (boxes[x][y].getWest() != 10) {
                        boxes[x][y].addChance(boxes[x - 1][y].getChancePerBox());
                    }
                    if (boxes[x][y].getEast() != 10) {
                        boxes[x][y].addChance(boxes[x + 1][y].getChancePerBox());
                    }
                    if (boxes[x][y].getSouthWest() != 10) {
                        boxes[x][y].addChance(boxes[x - 1][y + 1].getChancePerBox());
                    }
                    if (boxes[x][y].getSouth() != 10) {
                        boxes[x][y].addChance(boxes[x][y + 1].getChancePerBox());
                    }
                    if (boxes[x][y].getSouthEast() != 10) {
                        boxes[x][y].addChance(boxes[x + 1][y + 1].getChancePerBox());
                    }
                }
            }
        }
    }
}
