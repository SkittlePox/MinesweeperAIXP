package minesweeperaixp;

//import java.awt.*;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.*;
import java.util.*;

/**
 *
 * @author Ben
 */
public class MinesweeperAIXP {

    public static Scanner scan = new Scanner(System.in);
    public static Random rand = new Random();
    static String input;    //Used for scanner inputs
    static boolean prompt = true;   //Used to control whether or not commands can be taken

    static int rowsX = 9, columnsY = 9;
    static Box[][] boxes = new Box[rowsX][columnsY];        //Creates a 2D array of boxes with the given dimensions
    static AI ai;   //Creates AI object

    static int ScreenWidth, ScreenHeight;   //Screen DImensions
    static int widthCrop, heightCrop; //Number of Pixels to crop out of the search scope

    static Mouse mouse;     //Mouse object
    static BufferedImage image;     //Image Object

    static int rootX, rootY, homeX, homeY;
    static int gray128 = 128, gray192 = 192, blue255 = 255, blue128 = 128, green128 = 128, red255 = 255, red128 = 128, black0 = 0;

    public static void main(String[] args) throws Throwable {
        ai = new AI();  //Initializes AI object
        mouse = new Mouse();    //Initializing Mouse object

        callibrateScreenSize(); //Callibrates screen size

        pause(1.5);

        System.out.println("\nTaking a screenshot in 5...");
        pause(1);
        System.out.println("4...");
        pause(1);
        System.out.println("3...");
        pause(1);
        System.out.println("2...");
        pause(1);
        System.out.println("1...");
        pause(1);

        image = takeScreenShot();   //Sets an image a screenshot

        System.out.println("Click");

        findSquares();
        guess();
        analyze(false);
        printStatus();
        while (prompt) {
            takeCommand();
        }
    }

    public static void findSquares() {  //Finds Minesweeper
        boolean found = false;
        pause(1);
        System.out.println("\nSearching for squares\n");
        pause(1.5);

        for (int y = 0; y < 300; y++) {   //Repeats for each y pixel
            for (int x = 0; x < 200; x++) { //Repeats for each x pixel
                int color = image.getRGB(x, y);
                int blue = color & 0xFF;          // mask first 8 bits
                int green = (color >> 8) & 0xFF;  // shift right by 8 bits, then mask first 8 bits
                int red = (color >> 16) & 0xFF;   // shift right by 16 bits, then mask first 8 bits

                System.out.println(x + " " + y + " Red: " + red + " Green: " + green + "  Blue: " + blue);  //Prints info about current pixel

                if (isSameColor(red, green, blue, gray128, gray128, gray128, 0)) {  //Tests for gray128 aka the root color
                    System.out.println("\nRoot color found at " + x + " " + y);
                    pause(.75);
                    found = true;
                    rootX = x + 12; //Sets actual box location
                    rootY = y + 54; //Sets actual box location
                    homeX = rootX + 173;
                    homeY = rootY + 30;
                    System.out.println("\nRoot square planted at " + rootX + " " + rootY + "\n");
                    pause(1);
                    break;  //Breaks out of first loop
                }
            }

            if (found) {
                break;  //Breaks out of second for loop
            }
        }
        if (found) {
            int c = 0;  //The box number
            int mapX = rootX, mapY = rootY;
            for (int b = 0; b < columnsY; b++) {
                mapX = rootX;   //Resets mapX to rootX so that the x value does not continue further than wanted
                for (int a = 0; a < rowsX; a++) {
                    boxes[a][b] = new Box(mapX, mapY, c, -1);   //Creates new box
                    pause(.05);
                    System.out.println("Box " + boxes[a][b].getNum() + " at " + boxes[a][b].getX() + " " + boxes[a][b].getY() + " with status of " + boxes[a][b].getStatus());
                    mouse.move(boxes[a][b].getX(), boxes[a][b].getY()); //Moves mouse to newly created box
                    c++;
                    mapX += 16;   //Goes to the next column right
                }
                mapY += 16;   //Goes to the next row down
            }
            goHome();
            System.out.println("\nMinesweeper found");
        } else {
            System.out.println("Minesweeper not found");
        }
    }

    public static void callibrateScreenSize() { //Simply gets screen size

        try {
            Rectangle captureSize = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            ScreenWidth = captureSize.width;
            ScreenHeight = captureSize.height;
            System.out.println("Width is " + ScreenWidth);
            System.out.println("Height is " + ScreenHeight);
        } catch (Exception e) {
            e.printStackTrace();
        }
        pause(1);
        if (ScreenHeight == 1080) { //Changes the amount of screen to crop out for the search
            heightCrop = 700;
            widthCrop = 1600;
            System.out.println("\nAdjusting screen crop size to 320 x 380");
        } else if (ScreenHeight == 768) {   //Changes the amount of screen to crop out for the search
            heightCrop = 400;
            widthCrop = 1000;
            System.out.println("\nAdjusting screen crop size to 366 x 368");
        }
    }

    public static BufferedImage takeScreenShot() {  //Takes a screeenshot
        Rectangle captureSize = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        BufferedImage image = mouse.getImage(captureSize);
        return image;
    }

    public static void takeCommand() {
        input = scan.nextLine().toLowerCase();
        switch (input) {
            case "quit":
                prompt = false;
                break;
            case "guess":
                guess();
                break;
            case "analyze":
                analyze(false);
                printStatus();
                break;
            case "reanalyze":
                analyze(true);
                printStatus();
                break;
            case "print":
                printStatus();
                break;
        }
    }

    public static void printStatus() {
        System.out.println();
        for (int d = 0; d < rowsX + 1; d++) {
            System.out.print("---");
        }
        System.out.println();
        for (int c = 0; c < columnsY; c++) {
            System.out.print("|");
            for (int g = 0; g < rowsX; g++) {
                if (boxes[g][c].getStatus() == -1) {
                    System.out.print("[ ]");
                } else if (boxes[g][c].getStatus() == 0) {
                    System.out.print("   ");
                } else {
                    System.out.print("[" + boxes[g][c].getStatus() + "]");
                }
            }
            System.out.print("|\n");
        }
        for (int d = 0; d < rowsX + 1; d++) {
            System.out.print("---");
        }
    }

    public static void guess() {    //Guesses a box
        System.out.println("\nGuessing");
        pause(2);
        int r = rand.nextInt(rowsX);    //Gets random x
        int c = rand.nextInt(columnsY); //Gets random y
        System.out.println(r + " " + c + " Was guessed");
        if (boxes[r][c].getStatus() == -1) {    //If the box is unchecked
            moveToBox(r, c);
            mouse.leftClick();
            if (!bombCheck(boxes[r][c])) {
                analyze(false);
                if (isLonely(boxes[r][c])) {
                    guess();
                }
            } else {
                System.out.println("Game Over");
            }
        } else {
            System.out.print(" but has already been checked");
            guess();
        }
    }

    public static boolean bombCheck(Box box) {  //Checks if you hit a bomb
        image = takeScreenShot();
        int color = image.getRGB(box.getX(), box.getY());
        int blue = color & 0xFF;          // mask first 8 bits
        int green = (color >> 8) & 0xFF;  // shift right by 8 bits, then mask first 8 bits
        int red = (color >> 16) & 0xFF;   // shift right by 16 bits, then mask first 8 bits
        return isSameColor(red, green, blue, black0, black0, black0, 0);
    }

    public static boolean isLonely(Box box) {   //Tests if box has bordering uchecked boxes
        return box.getNorth() == -1 || box.getSouth() == -1 || box.getEast() == -1 || box.getWest() == -1;
    }

    public static void analyze(boolean re) {
        pause(2);
        goHome();
        System.out.println("\nAnalyzing board\n");
        image = takeScreenShot();   //Takes new screenshot
        for (int y = 0; y < columnsY; y++) {
            for (int x = 0; x < rowsX; x++) {
                if (boxes[x][y].getStatus() == -1 || re) {
                    int color = image.getRGB(boxes[x][y].getX(), boxes[x][y].getY());
                    int blue = color & 0xFF;          // mask first 8 bits
                    int green = (color >> 8) & 0xFF;  // shift right by 8 bits, then mask first 8 bits
                    int red = (color >> 16) & 0xFF;   // shift right by 16 bits, then mask first 8 bits

                    System.out.println("Testing Box " + boxes[x][y].getNum() + " at " + boxes[x][y].getX() + " " + boxes[x][y].getY() + " with colors " + red + " " + green + " " + blue);

                    if (isSameColor(red, green, blue, gray192, gray192, gray192, 0)) {  //Tests for gray192
                        System.out.println("Testing for 0");
                        color = image.getRGB(boxes[x][y].getX() - 9, boxes[x][y].getY());
                        blue = color & 0xFF;          // mask first 8 bits
                        green = (color >> 8) & 0xFF;  // shift right by 8 bits, then mask first 8 bits
                        red = (color >> 16) & 0xFF;   // shift right by 16 bits, then mask first 8 bits
                        if (isSameColor(red, green, blue, gray128, gray128, gray128, 0)) {
                            boxes[x][y].setStatus(0);
                            boxes[x][y].setChance(0);
                        }
                    } else if (isSameColor(red, green, blue, 0, 0, blue255, 0)) {   //Tests for blue255
                        boxes[x][y].setStatus(1);
                        boxes[x][y].setChance(0);
                    } else if (isSameColor(red, green, blue, 0, green128, 0, 0)) {  //Tests for green128
                        boxes[x][y].setStatus(2);
                        boxes[x][y].setChance(0);
                    } else if (isSameColor(red, green, blue, red255, 0, 0, 0)) {  //Tests for green128
                        boxes[x][y].setStatus(3);
                        boxes[x][y].setChance(0);
                    } else if (isSameColor(red, green, blue, 0, 0, blue128, 0)) {  //Tests for blue128
                        boxes[x][y].setStatus(4);
                        boxes[x][y].setChance(0);
                    } else if (isSameColor(red, green, blue, red128, 0, 0, 0)) {  //Tests for red128
                        boxes[x][y].setStatus(5);
                        boxes[x][y].setChance(0);
                    }
                }
            }
        }
        for (int y = 0; y < columnsY; y++) {
            for (int x = 0; x < rowsX; x++) {
                if (y >= 1) {
                    boxes[x][y].setNorth(boxes[x][y - 1].getStatus());
                }
                if (y <= columnsY - 2) {
                    boxes[x][y].setSouth(boxes[x][y + 1].getStatus());
                }
                if (x <= rowsX - 2) {
                    boxes[x][y].setEast(boxes[x + 1][y].getStatus());
                }
                if (x >= 1) {
                    boxes[x][y].setWest(boxes[x - 1][y].getStatus());
                }
                if (x <= rowsX - 2 && y >= 1) {
                    boxes[x][y].setNorthEast(boxes[x + 1][y - 1].getStatus());
                }
                if (x >= 1 && y >= 1) {
                    boxes[x][y].setNorthWest(boxes[x - 1][y - 1].getStatus());
                }
                if (x <= rowsX - 2 && y <= columnsY - 2) {
                    boxes[x][y].setSouthEast(boxes[x + 1][y + 1].getStatus());
                }
                if (x >= 1 && y <= columnsY - 2) {
                    boxes[x][y].setSouthWest(boxes[x - 1][y + 1].getStatus());
                }
                System.out.println("Box " + boxes[x][y].getNum() + ": North-" + boxes[x][y].getNorth()
                        + " South-" + boxes[x][y].getSouth() + " East-" + boxes[x][y].getEast()
                        + " West-" + boxes[x][y].getWest() + " NorthEast-" + boxes[x][y].getNorthEast()
                        + " NorthWest-" + boxes[x][y].getNorthWest() + " SouthEast-" + boxes[x][y].getSouthEast()
                        + " SouthWest-" + boxes[x][y].getSouthWest());
            }
        }
    }

    public static void moveToBox(int x, int y) {
        mouse.move(boxes[x][y].getX(), boxes[x][y].getY());
    }

    public static void goHome() {
        mouse.move(homeX, homeY);
    }

    public static Box findBoxNum(int n) {
        for (int b = 0; b < columnsY; b++) {
            for (int a = 0; a < rowsX; a++) {
                if (boxes[a][b].getNum() == n) {
                    return boxes[a][b];
                }
            }
        }
        return null;
    }

    public static boolean isSameColor(int r1, int g1, int b1, int r2, int g2, int b2, int t) {
        return r1 <= r2 + t && r1 >= r2 - t && g1 <= g2 + t && g1 >= g2 - t && b1 <= b2 + t && b1 >= b2 - t;
    }

    public static void pause(double seconds) {
        seconds = seconds * 1000;
        long secondsL = (long) seconds;
        try {
            Thread.sleep(secondsL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
