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

        g.drawLine(0, xAxis, width, xAxis); // ось X
        g.drawLine(yAxis, 0, yAxis, height); // ось Y

        g.setFont(new Font("Cambria", Font.PLAIN, 12));
        g.setColor(Color.RED);
        FontMetrics m = g.getFontMetrics();
        for (int i = (int)Math.ceil(converter.getxMin()); i <= converter.getxMax(); i++) {
            int x = converter.xCrt2Scr(i);
            g.drawLine(x, xAxis - 5, x, xAxis + 5); // деление
            String label = Integer.toString(i);
            int labelWidth = m.stringWidth(label);
            g.drawString(label, x - labelWidth / 2, xAxis + 20);
        }

        // Метки по Y
        for (int i = (int)Math.ceil(converter.getyMin()); i <= converter.getyMax(); i++) {
            int y = converter.yCrt2Scr(i);
            g.drawLine(yAxis - 5, y, yAxis + 5, y); // деление
            String label = Integer.toString(i);
            int labelHeight = m.getHeight();
            g.drawString(label, yAxis + 8, y + labelHeight / 2 - 4);
        }

        /*
         // Здесь выводить оси не через центр, а с учетом координаты "0"
        g.drawLine(0, height / 2, width, height / 2);
        g.drawLine(width / 2, 0, width / 2, height);

        // Пример отрисовки текста
        g2.setStroke(new BasicStroke(1));
        g.setColor(Color.RED);
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
