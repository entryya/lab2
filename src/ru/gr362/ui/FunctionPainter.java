package ru.gr362.ui;

import ru.gr362.converting.Converter;
import java.awt.*;

public class FunctionPainter implements Painter {
    private Function function;
    private Converter converter;
    private Color color;

    private int width;
    private int height;

    public FunctionPainter(Converter converter) {
        this.converter = converter;
        width = converter.getWidth();
        height = converter.getHeight();
    }

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

        int prevX = -1;
        int prevY = -1;

        for (int xScr = 0; xScr < width; xScr++) {
            double x = converter.xScr2Crt(xScr);
            double y = function.invoke(x);
            int yScr = converter.yCrt2Scr(y);

            if (xScr > 0) {
                g.drawLine(prevX, prevY, xScr, yScr);
            }

            prevX = xScr;
            prevY = yScr;
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