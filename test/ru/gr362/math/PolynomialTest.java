package ru.gr362.math;

import org.junit.jupiter.api.Test;

import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class PolynomialTest {
    @Test
    void getCoef() {
        var expected = new TreeMap<Integer, Double>();
        var actual = new TreeMap<Integer, Double>();
        expected.put(0, 0.0);
        var p1 = new Polynomial();
        assertEquals(expected, p1.getCoef());
        expected.clear();

        actual.put(0, -1.0);
        actual.put(1, 2.0);
        expected.put(0, -1.0);
        expected.put(1, 2.0);
        var p2 = new Polynomial(actual);
        assertEquals(expected, p2.getCoef());
        actual.clear();
        expected.clear();

        actual.put(0, 2.0);
        actual.put(1, 0.0);
        actual.put(2, -4.0);
        expected.put(0, 2.0);
        expected.put(2, -4.0);
        var p3 = new Polynomial(actual);
        assertEquals(expected, p3.getCoef());

        actual.clear();
        expected.clear();
        actual.put(0, 2.0);
        actual.put(-1, 4.0);
        actual.put(2, -4.0);
        expected.put(0, 2.0);
        expected.put(2, -4.0);
        var p4 = new Polynomial(actual);
        assertEquals(expected, p4.getCoef());
    }

    @Test
    void testToString() {
        var actual = new TreeMap<Integer, Double>();
        var p1 = new Polynomial();
        assertEquals("0.0", p1.toString());

        actual.put(0, 1.0);
        var p2 = new Polynomial(actual);
        assertEquals("1.0", p2.toString());

        actual.clear();
        actual.put(0, -1.0);
        var p3 = new Polynomial(actual);
        assertEquals("-1.0", p3.toString());

        actual.clear();
        var p4 = new Polynomial(2.5, 1.0);
        assertEquals("x+2.5", p4.toString());

        actual.clear();
        var p5 = new Polynomial(-2.5, -1.0);
        assertEquals("-x-2.5", p5.toString());
    }

    @Test
    void testGetPower() {
        var actual = new TreeMap<Integer, Double>();
        actual.put(4, 5.0);
        actual.put(7, 0.0);
        actual.put(2, 2.0);
        var t1 = new Polynomial(actual);
        assertEquals(4, t1.getPower());
    }

    @Test
    void testMath() {
        var t1 = new TreeMap<Integer, Double>();
        t1.put(1, 2.0);
        t1.put(2, 2.0);
        var p1 = new Polynomial(t1);  // 2.0x^2 + 2x

        var t2 = new TreeMap<Integer, Double>();
        t2.put(3, 5.0);
        t2.put(0, 1.0);
        t2.put(1, 3.0);
        var p2 = new Polynomial(t2); // 5.0x^3 + 3.0x + 1.0

        assertEquals("5.0x^3+2.0x^2+5.0x+1.0", p1.plus(p2).toString());
        assertEquals("-5.0x^3+2.0x^2-x-1.0", p1.minus(p2).toString());
        assertEquals("10.0x^5+10.0x^4+6.0x^3+8.0x^2+2.0x", p1.times(p2).toString());
        assertEquals("4.0x^2+4.0x", p1.times(2).toString());
        assertEquals("x^2+x", p1.div(2).toString());
        assertEquals(12.0, p1.invoke(2));
    }

    @Test
    void testEquals() {
        var c1 = new Polynomial(1.0, 2.0);
        var c2 = new Polynomial(1.0, 2.0);
        var c3 = new Polynomial(2.0, 1.0);
        var c4 = 5;
        assertTrue(c1.equals(c2));
        assertFalse(c1.equals(c4));
        assertFalse(c1.equals(c3));
    }

    @Test
    void testHashCode() {
        var c1 = new Polynomial(0.0, 1.0);
        var c2 = new Polynomial(0.0, 1.0);
        var c3 = new Polynomial(1.0, 0.0);

        assertEquals(c1.hashCode(), c2.hashCode());
        assertNotEquals(c1.hashCode(), c3.hashCode());
    }

    @Test
    void calcDerivative() {
        var p1 = new Polynomial(2.0, -3.0, 1.0);
        var exp1 = new Polynomial(-3.0, 2.0);
        var d1 = p1.differentiate();
        assertEquals(exp1, d1);

        var p2 = new Polynomial(5.0);
        var exp2 = new Polynomial(0.0);
        var d2 = p2.differentiate();
        assertEquals(exp2, d2);

        var p3 = new Polynomial(1.0, 2.0, -1.0, 3.0);
        var exp3 = new Polynomial(2.0, -2.0, 9.0);
        var d3 = p3.differentiate();
        assertEquals(exp3, d3);
    }
}