package ru.gr362.ui;

import ru.gr362.converting.Converter;
import java.awt.*;

public class CartesianPainter implements Painter {
    private int width;
    private int height;
    private Converter converter;

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(2));

        int xAxis = converter.yCrt2Scr(0);
        int yAxis = converter.xCrt2Scr(0);

        g.drawLine(0, xAxis, width, xAxis);
        g.drawLine(yAxis, 0, yAxis, height);

        g.setFont(new Font("Cambria", Font.BOLD, 12));
        g.setColor(Color.RED);
        FontMetrics m = g.getFontMetrics();
        for (int i = (int)Math.ceil(converter.getxMin()); i <= converter.getxMax(); i++) {
            int x = converter.xCrt2Scr(i);
            g.drawLine(x, xAxis - 5, x, xAxis + 5);

            String label = Double.toString(i);
            int labelWidth = m.stringWidth(label);

            int labelX = (i == 0) ? x + 5 : x - labelWidth / 2;

            g.drawString(label, labelX, xAxis + 20);
        }

        for (int i = (int)Math.ceil(converter.getyMin()); i <= converter.getyMax(); i++) {
            if (i != 0) {
                int y = converter.yCrt2Scr(i);
                g.drawLine(yAxis - 5, y, yAxis + 5, y);

                String label = Double.toString(i);
                int labelHeight = m.getHeight();

                g.drawString(label, yAxis + 8, y + labelHeight / 2 - 4);
            }
        }

        /*
        // Пример отрисовки текста
        var sz = 6;
        g.drawLine(300, height / 2 - sz, 300, height / 2 + sz);
        var pos = -1.0;
        var strPos = "" + pos;
        g.setColor(Color.RED);
        var f = new Font("Cambria", Font.BOLD, 12);
        g.setFont(f);
        var m = g.getFontMetrics();
        var strRect = m.getStringBounds(strPos, g);
        g.drawString(
                strPos,
                (int)(300 - strRect.getWidth() / 2),
                (int)(height / 2 + sz + strRect.getHeight())
        );*/
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }

    public void setConverter(Converter converter) {
        this.converter = converter;
    }
}