import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.awt.event.*;

public class Board {
    private Cell[][] cells;
    private int cellID = 0;
    private int side = 8;
    private int limit = side - 2;


    public void setBoard() {
        JFrame frame = new JFrame();
        frame.add(addCells());

        plantMines();
        setCellValues();

        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public JPanel addCells() {
        JPanel panel = new JPanel(new GridLayout(side, side));
        cells = new Cell[side][side];
        for (int i = 0; i < side; i++) {
            for (int j = 0; j < side; j++) {
                cells[i][j] = new Cell(this);
                cells[i][j].setId(getID());
                panel.add(cells[i][j].getButton());
            }
        }
        return panel;
    }

    public void plantMines() {
        ArrayList<Integer> loc = generateMinesLocation(12);
        for (int i : loc) {
            getCell(i).setValue(-1);
        }
    }

    /*Chooses random places for mines*/
    public ArrayList<Integer> generateMinesLocation(int q) {
        ArrayList<Integer> loc = new ArrayList<Integer>();
        int random;
        for (int i = 0; i < q; ) {
            random = (int) (Math.random() * (side * side));
            if (!loc.contains(random)) {
                loc.add(random);
                i++;
            }
        }
        return loc;
    }

    /*This method counts number of mines around particular cells and sets their values*/
    public void setCellValues() {
        for (int i = 0; i < side; i++) {
            for (int j = 0; j < side; j++) {
                if (cells[i][j].getValue() != -1) {
                    if (j >= 1 && cells[i][j - 1].getValue() == -1) cells[i][j].incrementValue();
                    if (j <= limit && cells[i][j + 1].getValue() == -1) cells[i][j].incrementValue();
                    if (i >= 1 && cells[i - 1][j].getValue() == -1) cells[i][j].incrementValue();
                    if (i <= limit && cells[i + 1][j].getValue() == -1) cells[i][j].incrementValue();
                    if (i >= 1 && j >= 1 && cells[i - 1][j - 1].getValue() == -1) cells[i][j].incrementValue();
                    if (i <= limit && j <= limit && cells[i + 1][j + 1].getValue() == -1) cells[i][j].incrementValue();
                    if (i >= 1 && j <= limit && cells[i - 1][j + 1].getValue() == -1) cells[i][j].incrementValue();
                    if (i <= limit && j >= 1 && cells[i + 1][j - 1].getValue() == -1) cells[i][j].incrementValue();
                }
            }
        }
    }

    /*This method starts a chain reaction. When the user clicks on a particular cell, if the cell is empty (value = 0) this
    method looks for another empty cell next to an activated one. If it finds one, it will call checkCell and in effect,
    start next scan on its closest area.
     */
    public void scanForEmptyCells() {
        for (int i = 0; i < side; i++) {
            for (int j = 0; j < side; j++) {
                if (!cells[i][j].isNotChecked()) {
                    if (j >= 1 && cells[i][j - 1].isEmpty()) cells[i][j - 1].checkCell();
                    if (j <= limit && cells[i][j + 1].isEmpty()) cells[i][j + 1].checkCell();
                    if (i >= 1 && cells[i - 1][j].isEmpty()) cells[i - 1][j].checkCell();
                    if (i <= limit && cells[i + 1][j].isEmpty()) cells[i + 1][j].checkCell();
                    if (i >= 1 && j >= 1 && cells[i - 1][j - 1].isEmpty()) cells[i - 1][j - 1].checkCell();
                    if (i <= limit && j <= limit && cells[i + 1][j + 1].isEmpty()) cells[i + 1][j + 1].checkCell();
                    if (i >= 1 && j <= limit && cells[i - 1][j + 1].isEmpty()) cells[i - 1][j + 1].checkCell();
                    if (i <= limit && j >= 1 && cells[i + 1][j - 1].isEmpty()) cells[i + 1][j - 1].checkCell();
                }
            }
        }
    }

    public int getID() {
        int id = cellID;
        cellID++;
        return id;
    }

    public Cell getCell(int id) {
        for (Cell[] a : cells) {
            for (Cell b : a) {
                if (b.getId() == id) return b;
            }
        }
        return null;
    }

    public void fail(){
        for (Cell[] a : cells) {
            for (Cell b : a) {
                b.reveal();
            }
        }
        int score3;
        score3 =  Cell.score;

        int n = JOptionPane.YES_NO_OPTION;
        int f = JOptionPane.showConfirmDialog(null, "Your Score Was " + score3 + " Points! \n" +
                "Would You Like To Play Again? ","Restart Box",  n);
        Cell score = new Cell();
        if(f == 0)
        {
            score.setScore(0);
            Start.main(null);
        }
        else
        {
            System.exit(0);
        }
    }
}