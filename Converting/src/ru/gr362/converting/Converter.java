package ru.gr362.converting;

public class Converter {
    private double xMin;
    private double xMax;
    private double yMin;
    private double yMax;

    private int width;
    private int height;

    public Converter(double xMin, double xMax, double yMin, double yMax) {
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
    }

    public Converter(double xMin, double xMax, double yMin, double yMax, int width, int height) {
        this(xMin, xMax, yMin, yMax);
        this.width = width;
        this.height = height;
    }

    public double getxMin() {
        return xMin;
    }

    public void setxMin(double xMin) {
        this.xMin = xMin;
    }

    public double getxMax() {
        return xMax;
    }

    public void setxMax(double xMax) {
        this.xMax = xMax;
    }

    public double getyMin() {
        return yMin;
    }

    public void setyMin(double yMin) {
        this.yMin = yMin;
    }

    public double getyMax() {
        return yMax;
    }

    public void setyMax(double yMax) {
        this.yMax = yMax;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    private int getxDen() {
        return (int)(width / (xMax - xMin));
    }

    private int getyDen() {
        return (int)(height / (yMax - yMin));
    }

    public int xCrt2Scr(double x) {
        return (int)(getxDen() * (x - xMin));
    }

    public int yCrt2Scr(double y) {
        return (int)(getyDen() * (yMax - y));
    }

    public double xScr2Crt(int x) {
        return xMin + (double)x / getxDen();
    }

    public double yScr2Crt(int y) {
        return yMax - (double)y / getyDen();
    }
}