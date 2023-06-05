package calculator;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class HistoryPanel extends JPanel {
    private JTextField a;
    public HistoryPanel(int x, int y, int w, int h) {
        this.setBounds(x+500, y, w, h);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

//        Border titleBorder = BorderFactory.createTitledBorder(new LineBorder(Color.BLACK, 2), "Lịch sử").setTitleFont(Font.SANS_SERIF, Font.PLAIN, 22);
        TitledBorder titleBorder = new TitledBorder(new LineBorder(Color.BLACK, 2), "Lịch sử tính toán",
                5, 0, new Font("Nunito", Font.BOLD, 22));
        this.setBorder(titleBorder);
//        this.setBackground(Color.pink);
        this.setVisible(false);
    }


}
