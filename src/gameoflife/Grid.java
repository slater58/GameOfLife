package gameoflife;

import java.util.Scanner;

public class Grid {

    int counter = 0;
    private boolean[][] lifeGrid;
    private boolean[][] intermediate;
    GraphicalInterface g = new GraphicalInterface();
    Scanner in = new Scanner(System.in);

    public Grid() throws InterruptedException {
        createGrid(30, 30);
        Thread.sleep(2000);      
    }
    /**
     * runs the game. the program will stop upon closing the gui
     * @throws InterruptedException
     */
    public void runGame() throws InterruptedException{
        while (true) {
            lifeCheck();
            Thread.sleep(200);
        }
    }

    /**
     * Method for letting the user assign buttons on the grid to true via console
     */
    public void assignGrid() {
        System.out.println("how many elements would you like to add");
        int numberOfAssignment = in.nextInt();
        for (int i = 0; i < numberOfAssignment; i++) {
            System.out.println("enter row");
            int row = in.nextInt();
            while (row > lifeGrid.length - 1 || row < 0) {
                System.out.println("enter a valid row inside the array less than  " + lifeGrid.length);
                row = in.nextInt();
            }
            System.out.println("enter column");
            int column = in.nextInt();
            while (column > lifeGrid.length - 1 || column < 0) {
                System.out.println("enter a valid column inside the array less than " + lifeGrid[0].length);
                column = in.nextInt();
            }
            lifeGrid[row][column] = true;
        }
    }

    /**
     * Creates the Array used for the grid and the intermediate array to hold values inbetween assignments
     * @param row Number of rows
     * @param column Number of columns
     */
    public void createGrid(int row, int column) {
        lifeGrid = new boolean[row][column];
        intermediate = new boolean[row][column];
        // assignGrid();
        // comment the above method and uncomment below for a glider
        lifeGrid[2][3] = true;
        lifeGrid[3][4] = true;
        lifeGrid[3][5] = true;
        lifeGrid[4][3] = true;
        lifeGrid[4][4] = true;

        // Loop to copy the lifeGrid array to intermediate before first run
        for (int x = 0; x < column; x++) {
            intermediate[x] = lifeGrid[x].clone();
        }

        g.createGridGui(row, column);
        g.setGridColor(lifeGrid);
    }

    /**
     * Checks individual elements in the array to see how many of the surrounding elements are true
     * @param i Row
     * @param j Column
     * @return Counter value to determine life, death, or survival
     */
    public int getNeighborCount(int i, int j) {
        counter = 0;
        for (int x = Math.max(0, i - 1); x < Math.min(i + 2, lifeGrid.length); x++) {
            for (int y = Math.max(0, j - 1); y < Math.min(j + 2, lifeGrid[0].length); y++) {
                if (lifeGrid[x][y]) {
                    counter++;
                }
            }
        }
        if (lifeGrid[i][j] == true) {
            counter--;
        }
        return counter;
    }
    
    /**
     * Loops through the entire lifeGrid array checking for life/death.
     * Assigns new values to intermediate array so lifeGrid isnt screwed up during runtime
     * copys intermediate to lifegrid to and calls the gui object to set new colors 
     */
    public void lifeCheck() {
        for (int i = 0; i < lifeGrid.length; i++) {
            for (int j = 0; j < lifeGrid[0].length; j++) {
                int check = getNeighborCount(i, j);
                // Birth
                if (!lifeGrid[i][j] && check == 3) {
                    intermediate[i][j] = true;
                }
                // Death
                if (lifeGrid[i][j] && check > 3 || check < 2) {
                    intermediate[i][j] = false;
                }
            }
        }
        for (int x = 0; x < lifeGrid.length; x++) {
            lifeGrid[x] = intermediate[x].clone();
        }
        g.setGridColor(lifeGrid);
    }

}
