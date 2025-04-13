package ru.gr362.ui;

import ru.gr362.converting.Converter;

import java.awt.*;

public class FunctionPainter implements Painter {
    private Function function;
    private Converter converter;
    private Color color = Color.decode("#006400");

    private int width;
    private int height;

    private final double STEP = 0.01;

    public void setFunction(Function function) {
        this.function = function;
    }

    public void setConverter(Converter converter) {
        this.converter = converter;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void paint(Graphics g) {
        if (function == null || converter == null) {
            return;
        }

        g.setColor(color);

        double xMin = converter.getxMin();
        double xMax = converter.getxMax();

        Double prevX = null, prevY = null;

        for (double x = xMin; x <= xMax; x += STEP) {
            double y = function.invoke(x);

            int xs = converter.xCrt2Scr(x);
            int ys = converter.yCrt2Scr(y);

            if (prevX != null && prevY != null) {
                int prevXs = converter.xCrt2Scr(prevX);
                int prevYs = converter.yCrt2Scr(prevY);
                g.drawLine(prevXs, prevYs, xs, ys);
            }

            prevX = x;
            prevY = y;
        }
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
        this.height =height;
    }
}