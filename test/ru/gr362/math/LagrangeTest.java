package ru.gr362.math;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class LagrangeTest {
    @Test
    public void testConstructor() {
        HashMap<Double, Double> points = new HashMap<>();
        points.put(0.0, 3.0);
        Lagrange lg = new Lagrange(points);

        double[] parametrs = new double[]{3.0};
        Polynomial pm = new Polynomial(parametrs);
        assertEquals(pm, lg);

        points.clear();
        points.put(-1.0, 0.0);
        points.put(0.0, -1.0);
        points.put(1.0, 0.0);

        lg = new Lagrange(points);
        parametrs = new double[]{-1.0, 0.0, 1.0};
        pm = new Polynomial(parametrs);
        assertEquals(pm, lg);
    }

    @Test
    public void testCreationTime() {
        HashMap<Double, Double> points = new HashMap<>();
        Random r = new Random();
        int pointsCount = 100;
        for (int i = 0; i < pointsCount; i++) {
            //var x = r.nextDouble() * 20.0 - 10;
            var x = -10 + 20.0 * i / (pointsCount - 10);
            var y = r.nextDouble() * 20.0 - 10;
            points.put(x, y);
        }

        var start1 = System.nanoTime();
        new Lagrange(points);
        var finishTime1 = System.nanoTime();
        System.out.println("Время построения полинома Лагранжа: " + ((finishTime1 - start1) / 1_000_000.0));

        var start2 = System.nanoTime();
        var ip = new InterpolatingPolynomial(points);
        var finishTime2 = System.nanoTime();
        System.out.println("Время построения полинома Ньютона: " + ((finishTime2 - start2) / 1_000_000.0));

        var start3 = System.nanoTime();
        ip.addPoint(-5.0, 2.0);
        var finishTime3 = System.nanoTime();
        System.out.println("Время добавления точки в полином Ньютона: " + ((finishTime3 - start3) / 1_000_000.0));
    }
}