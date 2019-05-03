import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class Cell  implements ActionListener{
    private JButton button;
    private Board board;
    private int value;
    private int id;
    private boolean notChecked;

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

    public void displayValue(){
        if(value==-1){
            button.setText("\u26A0");
            button.setBackground(Color.BLUE);
        }else if(value!=0){
            button.setText(String.valueOf(value));
        }
    }

    public void checkCell(){
        button.setEnabled(false);
        displayValue();
        notChecked = false;
        if(value == 0) board.scanForEmptyCells();
        if(value == -1)
        {
            board.fail();
            int n = JOptionPane.YES_NO_OPTION;
            int f = JOptionPane.showConfirmDialog(null, "You want to reset?", "restart box", n);
            if(f == 0)
            {
                Start.main(null);
            }
            else
            {
                System.exit(0);
            }
        }
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
        button.setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        checkCell();
       }
    }


