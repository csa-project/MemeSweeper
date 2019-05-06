import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class Cell  implements ActionListener{
    private JButton button;
    private Board board;
    int value = 0;
    private int id;
    private boolean notChecked;
    public static int score = 0;


    public Cell(Board board){
        button = new JButton();
        button.addActionListener(this);
        button.setPreferredSize(new Dimension(20,20));
        button.setMargin(new Insets(0,0,0,0));
        this.board = board;
        notChecked = true;
        button.setBackground(Color.BLACK);
    }

    public JButton getButton() {
        return button;
    }

    public int getValue() {
        return value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setValue(int value) {
        this.value = value;
    }
    public void setScore(int s){
        s = score;
        s= 0;
    }
    public int getScore(){
        return score;
    }

    public void displayValue(){
        if(value==-1){
            button.setText("\u26A0");
            button.setBackground(Color.RED);
        }else if(value!=0){
            button.setText(String.valueOf(value));
            button.setBackground(Color.BLUE);
        }
    }

    public void checkCell(){
        button.setEnabled(false);
        displayValue();
        button.setBackground(Color.BLUE);
        notChecked = false;
        if(value == 0) board.scanForEmptyCells();
        if(value == -1) board.fail();
    }

    public void incrementValue(){
        value++;
    }

    public boolean isNotChecked(){
        return notChecked;
    }

    public boolean isEmpty(){
        return isNotChecked() && value==0;
    }

    public void reveal(){
        displayValue();
        score = value + score;
        button.setEnabled(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        checkCell();
    }


}

