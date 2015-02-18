package minesweeperaixp;

import static minesweeperaixp.MinesweeperAIXP.boxes;
import static minesweeperaixp.MinesweeperAIXP.columnsY;
import static minesweeperaixp.MinesweeperAIXP.goHome;
import static minesweeperaixp.MinesweeperAIXP.mouse;
import static minesweeperaixp.MinesweeperAIXP.moveToBox;
import static minesweeperaixp.MinesweeperAIXP.pause;
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
                        if (boxes[x - 1][y - 1].getChancePerBox() >= 100) {
                            boxes[x][y].mineConfirmed();
                        }
                    }
                    if (boxes[x][y].getNorth() <= 8) {
                        boxes[x][y].addChance(boxes[x][y - 1].getChancePerBox());
                        if (boxes[x][y - 1].getChancePerBox() >= 100) {
                            boxes[x][y].mineConfirmed();
                        }
                    }
                    if (boxes[x][y].getNorthEast() <= 8) {
                        boxes[x][y].addChance(boxes[x + 1][y - 1].getChancePerBox());
                        if (boxes[x + 1][y - 1].getChancePerBox() >= 100) {
                            boxes[x][y].mineConfirmed();
                        }
                    }
                    if (boxes[x][y].getWest() <= 8) {
                        boxes[x][y].addChance(boxes[x - 1][y].getChancePerBox());
                        if (boxes[x - 1][y].getChancePerBox() >= 100) {
                            boxes[x][y].mineConfirmed();
                        }
                    }
                    if (boxes[x][y].getEast() <= 8) {
                        boxes[x][y].addChance(boxes[x + 1][y].getChancePerBox());
                        if (boxes[x + 1][y].getChancePerBox() >= 100) {
                            boxes[x][y].mineConfirmed();
                        }
                    }
                    if (boxes[x][y].getSouthWest() <= 8) {
                        boxes[x][y].addChance(boxes[x - 1][y + 1].getChancePerBox());
                        if (boxes[x - 1][y + 1].getChancePerBox() >= 100) {
                            boxes[x][y].mineConfirmed();
                        }
                    }
                    if (boxes[x][y].getSouth() <= 8) {
                        boxes[x][y].addChance(boxes[x][y + 1].getChancePerBox());
                        if (boxes[x][y + 1].getChancePerBox() >= 100) {
                            boxes[x][y].mineConfirmed();
                        }
                    }
                    if (boxes[x][y].getSouthEast() <= 8) {
                        boxes[x][y].addChance(boxes[x + 1][y + 1].getChancePerBox());
                        if (boxes[x + 1][y + 1].getChancePerBox() >= 100) {
                            boxes[x][y].mineConfirmed();
                        }
                    }
                }
                if (boxes[x][y].getStatus() == 9) {
                    boxes[x][y].setChance(0);
                    boxes[x][y].setChancePerBox(0);
                }
            }
        }
    }

    public void flagMines() {
        pause(2);
        for (int y = 0; y < columnsY; y++) {
            for (int x = 0; x < rowsX; x++) {
                if (boxes[x][y].isAMine() && boxes[x][y].getStatus() != 9) {
                    moveToBox(x, y);
                    mouse.rightClick();
                }
            }
        }
        goHome();
    }

    public void clearNums() {
        pause(2);
        for (int y = 0; y < columnsY; y++) {
            for (int x = 0; x < rowsX; x++) {
                if (boxes[x][y].isClear()) {
                    if (boxes[x][y].getNorthWest() != 10) {
                        moveToBox(x - 1, y - 1);
                        mouse.leftClick();
                    }
                    if (boxes[x][y].getNorth() != 10) {
                        moveToBox(x, y - 1);
                        mouse.leftClick();
                    }
                    if (boxes[x][y].getNorthEast() != 10) {
                        moveToBox(x + 1, y - 1);
                        mouse.leftClick();
                    }
                    if (boxes[x][y].getWest() != 10) {
                        moveToBox(x - 1, y);
                        mouse.leftClick();
                    }
                    if (boxes[x][y].getEast() != 10) {
                        moveToBox(x + 1, y);
                        mouse.leftClick();
                    }
                    if (boxes[x][y].getSouthWest() != 10) {
                        moveToBox(x - 1, y + 1);
                        mouse.leftClick();
                    }
                    if (boxes[x][y].getSouth() != 10) {
                        moveToBox(x, y + 1);
                        mouse.leftClick();
                    }
                    if (boxes[x][y].getSouthEast() != 10) {
                        moveToBox(x + 1, y + 1);
                        mouse.leftClick();
                    }
                }
            }
        }
    }
}
