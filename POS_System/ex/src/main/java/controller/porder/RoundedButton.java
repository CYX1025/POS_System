package controller.porder;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.JButton;

class RoundedButton extends JButton {
	private static final long serialVersionUID = 1L;
    private Color backgroundColor;       // 預設背景色
    private Color pressedColor;          // 按下去的顏色
    private Color textColor = Color.WHITE; // 文字顏色（可選擇變數化）

    public RoundedButton(Color backgroundColor, Color pressedColor) {
        super();
        this.backgroundColor = backgroundColor;
        this.pressedColor = pressedColor;

        setContentAreaFilled(false); // 關閉預設填色
        setFocusPainted(false);      // 關閉焦點框
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // 內距
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // 根據是否被點選來變換顏色
        g2.setColor(getModel().isArmed() ? pressedColor : backgroundColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);

        g2.setColor(textColor); // 設定文字顏色
        FontMetrics fm = g2.getFontMetrics();
        int stringWidth = fm.stringWidth(getText());
        int stringHeight = fm.getAscent();

        g2.drawString(
            getText(),
            (getWidth() - stringWidth) / 2,
            (getHeight() + stringHeight) / 2 - 4
        );

        g2.dispose();
    }
}


