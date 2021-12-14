package gameoflife;

public class GameOfLife {

    public static void main(String[] args) throws InterruptedException {
        Grid g = new Grid();

        // area of the grid 
        g.createGrid(30, 30);

        // stops the gui for 2 seconds
        Thread.sleep(2000);

        // program will stop upon closing gui
        while (true) {
            g.lifeCheck();
            Thread.sleep(200);
        }
    }

}
