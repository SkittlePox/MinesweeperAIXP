package minesweeperaixp;

import static minesweeperaixp.MinesweeperAIXP.columnsY;
import static minesweeperaixp.MinesweeperAIXP.rowsX;

public class Box {

    private int x, y, number, status = -1, n = 10, s = 10, e = 10, w = 10, ne = 10, nw = 10, se = 10, sw = 10;
    double chance = 0;
    double unaccountedBoxes;
    double surroundingBoxes;
    double chancePerBox;
    private boolean isMine, isAccountedFor = false;
    /*
     For status:
     -1 is unchecked
     0 is zero mines nearby
     1 is one mine nearby
     2 is two mines nearby
     3 is three mines nearby
     4 is four mines nearby
     5 is five mines nearby
     6 is six mines nearby
     7 is seven mines nearby
     8 is eight mines nearby
     9 is a mine that is flagged
    
     10 is a mine that is uninitialized (Only for surrounding boxes)
     */

    public Box(int xx, int yy, int num, int stat) {
        x = xx;
        y = yy;
        number = num;
        status = stat;
    }

    public int surroundingBoxes() {  //Checks the surrounding area for boxes
        int a = 0;
        if (nw == -1) {
            a++;
        }
        if (n == -1) {
            a++;
        }
        if (ne == -1) {
            a++;
        }
        if (w == -1) {
            a++;
        }
        if (e == -1) {
            a++;
        }
        if (sw == -1) {
            a++;
        }
        if (s == -1) {
            a++;
        }
        if (se == -1) {
            a++;
        }
        return a;
    }

    public void flagcheck() {
        unaccountedBoxes = status;
        if (nw == 9) {
            unaccountedBoxes--;
        }
        if (n == 9) {
            unaccountedBoxes--;
        }
        if (ne == 9) {
            unaccountedBoxes--;
        }
        if (w == 9) {
            unaccountedBoxes--;
        }
        if (e == 9) {
            unaccountedBoxes--;
        }
        if (sw == 9) {
            unaccountedBoxes--;
        }
        if (s == 9) {
            unaccountedBoxes--;
        }
        if (se == 9) {
            unaccountedBoxes--;
        }
        if (unaccountedBoxes < 0) {
            unaccountedBoxes = 0;
        }
    }

    public void setNorth(int north) {
        n = north;
    }

    public void setSouth(int south) {
        s = south;
    }

    public void setEast(int east) {
        e = east;
    }

    public void setWest(int west) {
        w = west;
    }

    public void setNorthEast(int northeast) {
        ne = northeast;
    }

    public void setNorthWest(int northwest) {
        nw = northwest;
    }

    public void setSouthEast(int southeast) {
        se = southeast;
    }

    public void setSouthWest(int southwest) {
        sw = southwest;
    }

    public void setChance(double c) {
        chance = c;
    }

    public void addChance(double c) {
        chance += c;
    }

    public double getChance() {
        return chance;
    }

    public int getNorth() {
        return n;
    }

    public int getSouth() {
        return s;
    }

    public int getEast() {
        return e;
    }

    public int getWest() {
        return w;
    }

    public int getNorthEast() {
        return ne;
    }

    public int getNorthWest() {
        return nw;
    }

    public int getSouthEast() {
        return se;
    }

    public int getSouthWest() {
        return sw;
    }

    public double getChancePerBox() {
        flagcheck();
        surroundingBoxes = surroundingBoxes();
        chancePerBox = (unaccountedBoxes / surroundingBoxes) * 100;
        if (unaccountedBoxes == 0 || status == 9) {
            return 0;
        } else {
            return chancePerBox;
        }
    }

    public void setChancePerBox(double c) {
        chancePerBox = c;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getNum() {
        return number;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int stat) {
        status = stat;
    }

    public void mineConfirmed() {
        isMine = true;
    }

    public boolean isAMine() {
        return isMine;
    }

    public void cleared() {
        isAccountedFor = true;
    }

    public boolean isClear() {
        flagcheck();
        return unaccountedBoxes == 0 && status != -1;
    }
}
