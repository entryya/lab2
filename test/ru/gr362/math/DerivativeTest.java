package ru.gr362.math;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class DerivativeTest {
    @Test
    public void testConstructor() {
        var p1 = new Polynomial(2.0, -3.0, 1.0);
        var exp1 = new Polynomial(-3.0, 2.0);
        var d1 = new Derivative(p1);
        assertEquals(exp1, d1);

        var p2 = new Polynomial(5.0);
        var exp2 = new Polynomial(0.0);
        var d2 = new Derivative(p2);
        assertEquals(exp2, d2);

        var p3 = new Polynomial(1.0, 2.0, -1.0, 3.0);
        var exp3 = new Polynomial(2.0, -2.0, 9.0);
        var d3 = new Derivative(p3);
        assertEquals(exp3, d3);
    }
}