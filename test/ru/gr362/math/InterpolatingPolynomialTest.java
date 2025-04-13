package ru.gr362.math;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class InterpolatingPolynomialTest {

    @Test
    public void testConstructor() {
        HashMap<Double, Double> points = new HashMap<>();

        points.put(-1.0, 0.0);
        points.put(0.0, -1.0);
        points.put(1.0, 0.0);
        var r1 = new Polynomial(-1.0, 0.0, 1.0);
        var p1 = new InterpolatingPolynomial(points);
        assertEquals(r1, p1);

        points.clear();
        points.put(-1.0, -5.0);
        points.put(0.0, -3.0);
        points.put(1.0, -1.0);
        var r2 = new Polynomial(-3.0, 2.0);
        var p2 = new InterpolatingPolynomial(points);
        assertEquals(r2, p2);

        points.clear();
        points.put(0.2, 0.4);
        points.put(0.4, 0.2);
        points.put(1.0, 2.0);
        var r3 = new Polynomial(1.0, -4.0, 5.0);
        var p3 = new InterpolatingPolynomial(points);
        assertEquals(r3, p3);
    }

    @Test
    void getPoints() {
        HashMap<Double, Double> points = new HashMap<Double, Double>();
        Random r = new Random();
        int pointsCount = 50;
        for (int i = 0; i < pointsCount; i++) {
            var x = -10 + 20.0 * i / (pointsCount - 10);
            var y = r.nextDouble() * 20.0 - 10;
            points.put(x, y);
        }
        var a = new InterpolatingPolynomial(points);

        HashMap<Double, Double> copy = a.getPoints();

        assertEquals(points, copy);
    }

    @Test
    void changePoints() {
        var p1 = new InterpolatingPolynomial();
        var p2 = new InterpolatingPolynomial();

        p2.addPoint(2.0, 1.0);
        p2.addPoint(0.0, 0.0);
        assertNotEquals(p1, p2);

        p2.deletePoint(0.0, 0.0);
        assertNotEquals(p1, p2);
    }
}