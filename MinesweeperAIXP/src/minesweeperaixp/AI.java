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
                    if (boxes[x][y].getNorthWest() <= 8) {
                        boxes[x][y].addChance(boxes[x - 1][y - 1].getChancePerBox());
                    }
                    if (boxes[x][y].getNorth() <= 8) {
                        boxes[x][y].addChance(boxes[x][y - 1].getChancePerBox());
                    }
                    if (boxes[x][y].getNorthEast() <= 8) {
                        boxes[x][y].addChance(boxes[x + 1][y - 1].getChancePerBox());
                    }
                    if (boxes[x][y].getWest() <= 8) {
                        boxes[x][y].addChance(boxes[x - 1][y].getChancePerBox());
                    }
                    if (boxes[x][y].getEast() <= 8) {
                        boxes[x][y].addChance(boxes[x + 1][y].getChancePerBox());
                    }
                    if (boxes[x][y].getSouthWest() <= 8) {
                        boxes[x][y].addChance(boxes[x - 1][y + 1].getChancePerBox());
                    }
                    if (boxes[x][y].getSouth() <= 8) {
                        boxes[x][y].addChance(boxes[x][y + 1].getChancePerBox());
                    }
                    if (boxes[x][y].getSouthEast() <= 8) {
                        boxes[x][y].addChance(boxes[x + 1][y + 1].getChancePerBox());
                    }
                }
                if (boxes[x][y].getStatus() == 9) {
                    boxes[x][y].setChance(0);
                    boxes[x][y].setChancePerBox(0);
                }
            }
        }
    }
}
