package calculator;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
public class ResultPanel extends JPanel{
    JLabel mathLabel;
    JLabel resultLabel;
    public JLabel getMathLabel() {
        return mathLabel;
    }
    public void setTextResField(String s) {
        this.mathLabel.setText(s);
    }
    public JLabel getResultLabel() {
        return resultLabel;
    }
    public void setTextResultLabel(String s) {
        this.resultLabel.setText(s);
    }
    public ResultPanel(int x, int y, int w, int h) {

        mathLabel = new JLabel();
        resultLabel = new JLabel();

        this.setBounds(x, y, w, h);

        mathLabel.setBounds(0, 0, w, h/3);
        resultLabel.setBounds(0, h/3, w, h*2/3);

        mathLabel.setForeground(new Color(225, 225, 225));
        resultLabel.setForeground(Color.WHITE);

        mathLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        resultLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));

        mathLabel.setBorder(new LineBorder(Color.BLACK, 2));
        resultLabel.setBorder(new LineBorder(Color.BLACK, 2));

        this.setAlignmentX(RIGHT_ALIGNMENT);

        this.add(mathLabel);
        this.add(resultLabel);
        this.setLayout(null);
        this.setBackground(Color.GRAY);
        this.setBorder(new LineBorder(Color.BLACK, 2));

    }

}