package ru.gr362.ui;

import java.awt.*;

public interface Painter {

    void paint(Graphics g);

    int getWidth();
    int getHeight();

    void setWidth(int width);
    void setHeight(int height);
}
