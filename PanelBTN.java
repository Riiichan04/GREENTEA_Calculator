package calculator;

import javax.swing.*;
import java.awt.*;

public class PanelBTN extends JPanel {
    private JButton[] btnList;
    private JPanel funcPanel;
    private JPanel numPanel;
    private JPanel expPanel;
    public PanelBTN(int x, int y, int w, int h) {
        this.setBounds(x, y, w, h);
        this.setLayout(null);
        drawButton();
    }
    public void drawButton() {
        //Một BTN:
        //Có Width = 1/5 Panel Tổng
        //Có Height = 3/25 Panel Tổng
        //Có tọa độ X + 1/4 Panel Tổng <=> (1/5 + 1/20) sau mỗi button
        //Có tọa độ Y + 1/5 Panel Tổng <=> (4/25 + 1/25) sau mỗi button

        funcPanel = new JPanel();
        funcPanel.setLayout(null);
        funcPanel.setBounds(0, 0, this.getWidth() * 3 / 4, this.getHeight() / 5);

        numPanel = new JPanel();
        numPanel.setLayout(null);
        numPanel.setBounds(0, this.getHeight() / 5, this.getWidth() * 3 / 4, this.getHeight() * 4 / 5);

        expPanel = new JPanel();
        expPanel.setLayout(null);
        expPanel.setBounds(this.getWidth() * 3 / 4, 0, this.getWidth() / 4, this.getHeight());

//        funcPanel.setBackground(new Color(255, 201, 252));
//        numPanel.setBackground(new Color(255, 201, 252));
//        expPanel.setBackground(new Color(255, 201, 252));

        String[] btnString = {" ( ", " ) ", "C", "7", "8", "9", "4", "5", "6", "1", "2", "3", "0", "-", " + ", " – ", " × ", " ÷ ", "="};
        btnList = new JButton[btnString.length];

        for (int i = 0; i < btnString.length; i++) {
            btnList[i] = new JButton(btnString[i]);
        }
        //Function BTN
        for (int i = 0, locX = 0; i < 3; i++, locX += this.getWidth() / 4) {
            btnList[i].setBounds(locX, 0, this.getWidth() / 5, this.getHeight() * 4 / 25);
            funcPanel.add(btnList[i]);
            if (i == 2) {
                btnList[i].setBackground(new Color(220, 0, 0));
                btnList[i].setForeground(Color.WHITE);
//                btnList[i].setFont(Font.BOLD);
            }
            else btnList[i].setBackground(new Color(0, 252, 42));
        }
        //Num BTN
        int count = 0;
        for (int i = 3, locX = 0, locY = 0; i < 14; i++, locX += this.getWidth() / 4) {
            if (count == 3) {
                count = 0;
                locX = 0;
                locY += this.getHeight() / 5;
            }
            if (i == 12) {
                btnList[i].setBounds(locX, locY, this.getWidth() / 2 - this.getWidth() / 20, this.getHeight() * 4 / 25);
                locX += this.getWidth() / 4;
                count += 2;
                numPanel.add(btnList[i]);
            } else {
                btnList[i].setBounds(locX, locY, this.getWidth() / 5, this.getHeight() * 4 / 25);
                count++;
                numPanel.add(btnList[i]);
            }

            btnList[i].setForeground(Color.WHITE);
            if (i == 13) {
                btnList[i].setBackground(new Color(0, 40, 100));
            }
            else btnList[i].setBackground(new Color(0, 40, 100));
        }
        //Exp BTN
        for (int i = 14, locY = 0; i < btnList.length; i++, locY += this.getHeight() / 5) {
            btnList[i].setBounds(0, locY, this.getWidth() / 5, this.getHeight() * 4 / 25);
            expPanel.add(btnList[i]);
            btnList[i].setBackground(new Color(255, 213, 0));
            btnList[i].setForeground(Color.BLACK);
            btnList[btnList.length-1].setBackground(new Color(255, 60, 0));
        }

        for (JButton jButton : btnList) {
            jButton.setFont(new Font("Verdana, Geneva, Tahoma, San_Serif", Font.BOLD, 18));
            jButton.setFocusable(false);
        }

        this.add(funcPanel);
        this.add(numPanel);
        this.add(expPanel);
    }
//    public void resizeBTN(int a, int b) {
//        this.setPreferredSize(new Dimension(a,b));
//        this.setSize(a, b);
//        funcPanel.setBounds(0, 0, this.getWidth() * 3 / 4, this.getHeight() / 5);
//        numPanel.setBounds(0, this.getHeight() / 5, this.getWidth() * 3 / 4, this.getHeight() * 4 / 5);
//        expPanel.setBounds(this.getWidth() * 3 / 4, 0, this.getWidth() / 4, this.getHeight());
//        for (int i = 0, locX = 0; i < 3; i++, locX += this.getWidth() / 4) {
//            btnList[i].setBounds(locX, 0, this.getWidth() / 5, this.getHeight() * 4 / 25);
//        }
//        int count = 0;
//        for (int i = 3, locX = 0, locY = 0; i < 14; i++, locX += this.getWidth() / 4) {
//            if (count == 3) {
//                count = 0;
//                locX = 0;
//                locY += this.getHeight() / 5;
//            }
//            if (i == 12) {
//                btnList[i].setBounds(locX, locY, this.getWidth() / 2 - this.getWidth() / 20, this.getHeight() * 4 / 25);
//                locX += this.getWidth() / 4;
//                count += 2;
//            } else {
//                btnList[i].setBounds(locX, locY, this.getWidth() / 5, this.getHeight() * 4 / 25);
//                count++;
//            }
//        }
//        for (int i = 14, locY = 0; i < btnList.length; i++, locY += this.getHeight() / 5) {
//            btnList[i].setBounds(0, locY, this.getWidth() / 5, this.getHeight() * 4 / 25);
//        }
//    }

    public JButton[] getBtnList() {
        return btnList;
    }
//    public void
}