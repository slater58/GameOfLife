package gameoflife;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GraphicalInterface {

    private JPanel ButtonPanel;
    private JButton[][] buttons;

    // Empty constructor
    public GraphicalInterface() {

    }

    /**
     * Creates a grid with user specified area
     * @param h height of the grid passed from the user
     * @param w Width of the grid passed from the user
     */
    public void createGridGui(int h, int w) {
        ButtonPanel = new JPanel();
        buttons = new JButton[h][w];
        ButtonPanel.setLayout(new GridLayout(h, w));
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                buttons[i][j] = new JButton();
                ButtonPanel.add(buttons[i][j]);

                buttons[i][j].setBackground(Color.white);

            }
        }
        JFrame window = new JFrame("Game of Life");
        window.add(ButtonPanel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setPreferredSize(new Dimension(1000, 1000));
        window.pack();
        window.setVisible(true);
    }

    /**
     * Checks and assigns boolean values in order to assign color to the grid buttons
     * @param array lifeGrid array passed from the grid class
     */
    public void setGridColor(boolean[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if (array[i][j]) {
                    buttons[i][j].setBackground(Color.black);
                } else {
                    buttons[i][j].setBackground(Color.white);
                }
            }
        }
    }
}
