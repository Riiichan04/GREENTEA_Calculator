package calculator;

import javax.swing.*;

import java.awt.*;

import java.util.ArrayList;

import java.util.Stack;

public class Calculator extends JFrame {
    private ResultPanel resPanel;
    private BTNPanel btnPanel;
    private String mathRequire;
    private StringBuilder mathInput;
    private ArrayList<String> lastedCalc = new ArrayList<>();
    private ArrayList<String> lastedResult = new ArrayList<>();
    private HistoryPanel hisPanel;
    private int yAxis = this.getHeight()/50;
    private int posCal;
    private int posRes;
    private boolean isCalc;

    public Calculator() {

        setTitle("Calculator Simulator");
        setLayout(null);
        setSize(500, 660);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon("E:\\Coding\\NLU\\HK2Y1\\Matcha2023\\JSwing2023\\src\\imageIcon\\calcuIcon.png").getImage());
        setResizable(false);

        btnPanel = new BTNPanel(this.getWidth()/30, this.getHeight()/3 + this.getHeight()/100, 475,
                this.getHeight()*2/3 - this.getHeight()/25);

        resPanel = new ResultPanel(this.getWidth()/30, this.getHeight()/50,
                450, this.getHeight()/3 - this.getHeight()*2/25);
        hisPanel = new HistoryPanel(this.getWidth()/30, this.getHeight()/50,
                450, this.getHeight()*23/25);
        this.add(resPanel);
        this.add(btnPanel);
        this.add(hisPanel);
//        this.addComponentListener(new ComponentListener() {
//            @Override
//            public void componentResized(ComponentEvent e) {
//                btnPanel.setSize(getWidth()*18/20,
//                        getHeight()*2/3 - getHeight()/25);
//                btnPanel.resizeBTN(getWidth()*18/20,
//                        getHeight()*2/3 - getHeight()*2/25);
//                resPanel.repaintRes(getWidth()*17/20, getHeight()/3 - getHeight()/8);
//            }
//            @Override
//            public void componentMoved(ComponentEvent e) {
//            }
//            @Override
//            public void componentShown(ComponentEvent e) {
//            }
//            @Override
//            public void componentHidden(ComponentEvent e) {
//            }
//        });
        JButton menuBTN = new JButton("Lịch sử");
        menuBTN.setBounds(this.getWidth()/30, this.getWidth()/3 + this.getWidth()/20, this.getWidth()/5, this.getHeight()/25);
        menuBTN.setBackground(new Color(0, 255, 153));
        menuBTN.setFocusable(false);
        menuBTN.setFont(new Font("Verdana, Geneva, Tahoma, San_Serif", Font.BOLD, 14));

        JButton backBTN = new JButton("Phép tính trước");
        backBTN.setBounds(this.getWidth()/30 + this.getWidth()*2/9, this.getWidth()/3 + this.getWidth()/20, this.getWidth()/3, this.getHeight()/25);
        backBTN.setBackground(new Color(0, 255, 153));
        backBTN.setFocusable(false);
        backBTN.setFont(new Font("Verdana, Geneva, Tahoma", Font.BOLD, 14));

        history(menuBTN);
        backCalc(backBTN);

        this.add(menuBTN);
        this.add(backBTN);

        setupCalc();
        System.out.println(menuBTN.getBounds());
        System.out.println(backBTN.getBounds());
        setVisible(true);
    }

    public static void main(String[] args) {
        Calculator c = new Calculator();
    }
    public void setupCalc() {
        JButton[] listBtn = btnPanel.getBtnList();
        for (int i = 0; i <= 2; i++) {
            if(i == 2)  {
                listBtn[i].addActionListener(e->{
                    resPanel.setTextResField("");
                    resPanel.setTextResultLabel("");
                    for (int j = 14; j < listBtn.length; j++) {
                        listBtn[j].setEnabled(false);
                    }
                    listBtn[1].setEnabled(false);
                    listBtn[0].setEnabled(true);
                    listBtn[13].setEnabled(true);
                });
            }
            int value = i;
            listBtn[i].addActionListener(e -> {
                resPanel.setTextResultLabel(resPanel.getResultLabel().getText() + listBtn[value].getText());
            });

        }


        for (int i = 3; i <= 13; i++) {
            int value = i;
            listBtn[i].addActionListener(e -> {
                resPanel.setTextResultLabel(resPanel.getResultLabel().getText() + listBtn[value].getText());
            });
        }
        for (int i = 14; i < listBtn.length-1; i++) {
            int value = i;
            listBtn[i].addActionListener(e -> {
                resPanel.setTextResultLabel(resPanel.getResultLabel().getText() + listBtn[value].getText());
            });
        }
        listBtn[listBtn.length-1].addActionListener(e -> {
            mathRequire = resPanel.getResultLabel().getText();
            resPanel.getResultLabel().setText("= "+ calculator(mathRequire));
            listBtn[13].setEnabled(true);
            listBtn[1].setEnabled(false);
            for (int i = 14; i < listBtn.length-1; i++) {
                listBtn[i].setEnabled(false);
            }
        });

        for (JButton jButton : listBtn) {
            jButton.addActionListener(e -> {
                if (isCalc) {
                    resPanel.setTextResField("");
                    resPanel.setTextResultLabel("");
                    isCalc = false;
                }
            });
        }
    }

    public double calculator(String math) {
        //String: Phép toán cần tính
        //Stack: Các toán tử
        //ArrayList: Phép tính sẽ thực hiện

        //VD:
        //2+3+4/5
        //Stack /
        //ArrayList: 23+4+5 => Phép toán cần tính là: 23+4+5/

        //5*6+3-8/2
        //Stack /
        //ArrayList: 56*3+8-2 => Phép toán cần tính là: 56*3+8-2/
        double result = 0;
        Stack<String> tempStack = new Stack<>();
        int size = mathRequire.length()/2 + 1;
        String[] arrCheck; //Chứa số và phép tính
        ArrayList<String> arrMath = new ArrayList<>();      //Chứa phép tính cuối cùng cần phải thực hiện
        arrCheck = mathRequire.split("\\s+");

        int checkExpress = 0;
        for (String s : arrCheck) {
            if (isExp(s)) checkExpress++;
        }

        if (checkExpress == 0) {
            result = Double.parseDouble(arrCheck[arrCheck.length-1]);
            JLabel hisResult = new JLabel(mathRequire + " = " + result);
            hisResult.setForeground(Color.BLACK);
            hisResult.setBounds(this.getWidth()/30 + 600, yAxis, 450, this.getHeight()/25);
            hisResult.setFont(new Font("Verdana, Geneva, Tahoma, San_Serif", Font.PLAIN, 20));
            yAxis += this.getHeight()/50 + this.getHeight()/100;
            hisPanel.add(hisResult);
            isCalc = true;
            resPanel.getMathLabel().setText(mathRequire);
            resPanel.getResultLabel().setText("");

            lastedCalc.add(mathRequire);
            lastedResult.add("= " + result);
            posCal = lastedCalc.size()-1;
            posRes = lastedResult.size()-1;

            return result;
        }
        else {
            boolean isNgoac = false;
            int countExp = 0;
            //Xử lý tính toán chính
            for (String s : arrCheck) {
                if (isExp(s)) {
                    if (tempStack.isEmpty() || isNgoac) {
                        tempStack.push(s);
                        isNgoac = false;
                        countExp++;
                    } else {
                        for (int j = 0; j < countExp; j++) {
                            if (isUT(s) <= isUT(tempStack.peek())) arrMath.add(tempStack.pop());
                            else {
                                countExp++;
                                break;
                            }
                        }
                        tempStack.push(s);
                    }
                } else if (isONgoac(s)) {
                    countExp = 0;
                    isNgoac = true;
                    tempStack.push(s);
                } else if (isCNgoac(s)) {
                    for (int j = tempStack.size(); j > 0; j--) {
                        if (isONgoac(tempStack.peek())) {
                            tempStack.pop();
                            break;
                        } else arrMath.add(tempStack.pop());
                    }
                    countExp = tempStack.size();
                } else arrMath.add(s);
            }

            for (int i = 0; i < tempStack.size(); i++) {
                arrMath.add(tempStack.pop());
            }
            Stack<String> resStack = new Stack<>();
            for (String s : arrMath) {
                if (isExp(s)) {
                    resStack.push(s);
                    result = calcNum(resStack.pop(), Double.parseDouble(resStack.pop()), Double.parseDouble(resStack.pop()));
                    resStack.push("" + result);
                } else {
                    resStack.push(s);
                }
            }

            JLabel hisResult = new JLabel(mathRequire + " = " + result);
            hisResult.setForeground(Color.BLACK);
            hisResult.setBounds(this.getWidth()/30 + 600, yAxis, 450, this.getHeight()/25);
            hisResult.setFont(new Font("Verdana, Geneva, Tahoma, San_Serif", Font.PLAIN, 20));
            yAxis += this.getHeight()/50 + this.getHeight()/100;
            hisPanel.add(hisResult);
            isCalc = true;
            resPanel.getMathLabel().setText(mathRequire);
            resPanel.getResultLabel().setText("");

            lastedCalc.add(mathRequire);
            lastedResult.add("= " + result);
            posCal = lastedCalc.size()-1;
            posRes = lastedResult.size()-1;

            return result;
        }

    }

    public boolean isExp(String input) {
        return input.equals("+") || input.equals("–") || input.equals("×") || input.equals("÷");
    }
    public boolean isONgoac(String input) {return input.equals("(");}
    public boolean isCNgoac(String input) {return input.equals(")");}

    public double calcNum(String exp, double a,  double b) {
        if (exp.equals("+")) return b+a;
        if (exp.equals("–")) return b-a;
        if (exp.equals("×")) return b*a;
        if (exp.equals("÷")) return b/a;
        else throw new NumberFormatException("Cannot calculate");
    }

    public int isUT(String input) {
        if (input.equals(" + ") || input.equals(" - ")) return 1;
        else if (input.equals(" × ") || input.equals(" ÷ ")) return 10;
        else return 0;
    }

    public void history(JButton button) {
        button.addActionListener(e -> {
            if (!hisPanel.isVisible()) {
                hisPanel.setVisible(true);
                this.setSize(1000, 660);
            }
            else {
                hisPanel.setVisible(false);
                this.setSize(500, 660);
            }
        });
    }

    public void backCalc(JButton button) {
        button.addActionListener(e -> {
            if (lastedResult.size() == 0) {
                resPanel.setTextResField("");
                resPanel.setTextResultLabel("");
            }
            else if (posCal == lastedCalc.size()-1) {
                resPanel.setTextResField(lastedCalc.get(--posCal));
                resPanel.setTextResultLabel(lastedResult.get(--posRes));
            }
            else {
                if (posCal < 0) {
                    posCal = lastedCalc.size() - 1;
                    posRes = lastedResult.size() - 1;
                }
                resPanel.setTextResField(lastedCalc.get(posCal--));
                resPanel.setTextResultLabel(lastedResult.get(posRes--));
            }
        });
    }

    public void  mathCalculator() {

    }


}