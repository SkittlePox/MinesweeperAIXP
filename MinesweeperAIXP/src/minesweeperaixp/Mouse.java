package minesweeperaixp;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.image.*;
import java.util.*;

public class Mouse {

    public Robot robot;
    int x;
    int y;

    public Mouse() throws AWTException {
        robot = new Robot();    //Initializing Robot object
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public BufferedImage getImage(Rectangle captureSize) {
        return robot.createScreenCapture(captureSize);
    }
    
    public void move(int xx, int yy) {
        robot.mouseMove(xx, yy);
    }
    
    public void leftClick() {
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }
}
