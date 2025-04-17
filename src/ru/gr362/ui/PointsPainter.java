package ru.gr362.ui;

import ru.gr362.converting.Converter;

import java.awt.*;
import java.util.LinkedHashMap;

public class PointsPainter implements Painter{
    private LinkedHashMap<Double, Double> points = new LinkedHashMap<>();
    private Converter converter;
    private Color color;

    private int width;
    private int height;

    public PointsPainter(Converter converter) {
        this.converter = converter;
    }

    public void setConverter(Converter converter) {
        this.converter = converter;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public LinkedHashMap<Double, Double> getPoints() {
        return points;
    }

    public void addPoint(double newX, double newY) {
        for (var x: points.keySet()) {
            if (newX == x) {
                System.out.println("Точка уже добавлена");
                return;
            }
        }
        points.put(newX, newY);
    }

    public void deletePoint(double x, double y) {
        points.remove(x, y);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(color);
        int sz = 10;

        for (var entry: points.entrySet()) {
            int x = converter.xCrt2Scr(entry.getKey());
            int y = converter.yCrt2Scr(entry.getValue());

            g.fillOval(x - sz / 2, y - sz / 2, sz, sz);
        }
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }
}