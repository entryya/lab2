package ru.gr362.converting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConverterTest {
    private Converter converter;

    @BeforeEach
    void setUp() {
        converter = new Converter(-10, 10, -10, 10, 200, 200);
    }

    @Test
    void xCrt2Scr() {
        assertEquals(100, converter.xCrt2Scr(0));
        assertEquals(0, converter.xCrt2Scr(-10));
        assertEquals(200, converter.xCrt2Scr(10));
    }

    @Test
    void yCrt2Scr() {
        assertEquals(100, converter.yCrt2Scr(0));
        assertEquals(0, converter.yCrt2Scr(10));
        assertEquals(200, converter.yCrt2Scr(-10));
    }

    @Test
    void xScr2Crt() {
        assertEquals(0.0, converter.xScr2Crt(100));
        assertEquals(-10.0, converter.xScr2Crt(0));
        assertEquals(10.0, converter.xScr2Crt(200));
    }

    @Test
    void yScr2Crt() {
        assertEquals(0.0, converter.yScr2Crt(100));
        assertEquals(10.0, converter.yScr2Crt(0));
        assertEquals(-10.0, converter.yScr2Crt(200));
    }

    @Test
    void testGettersAndSetters() {
        converter.setxMin(-5);
        converter.setxMax(5);
        converter.setyMin(-5);
        converter.setyMax(5);
        converter.setWidth(100);
        converter.setHeight(100);

        assertEquals(-5, converter.getxMin());
        assertEquals(5, converter.getxMax());
        assertEquals(-5, converter.getyMin());
        assertEquals(5, converter.getyMax());
        assertEquals(100, converter.getWidth());
        assertEquals(100, converter.getHeight());
    }
}