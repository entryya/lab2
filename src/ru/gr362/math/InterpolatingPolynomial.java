package ru.gr362.math;

import ru.gr362.ui.Function;

import java.util.*;

public class InterpolatingPolynomial extends Polynomial implements Function{
    private LinkedHashMap<Double, Double> points;
    private HashMap<List<Double>, Double> dividedDifferences;
    private Polynomial prodOfDiffs = new Polynomial(1.0);

    public InterpolatingPolynomial() {
        this(Map.of(0.0, 0.0));
    }

    public InterpolatingPolynomial(Map<Double, Double> points) {
        this.points = new LinkedHashMap<>(points);
        this.dividedDifferences = new HashMap<>();
        super();
        create();
    }

    public LinkedHashMap<Double, Double> getPoints() {
        return new LinkedHashMap<>(points);
    }

    private void create() {
        prodOfDiffs = new Polynomial(1.0);
        dividedDifferences.clear();
        coef.clear();

        var xValues = new ArrayList<>(points.keySet());

        for (int i = 0; i < points.size(); i++) {
            plusPolynomialTerm(i, xValues);
        }
    }

    private void plusPolynomialTerm(int index, List<Double> xValues) {
        Polynomial term;

        if (index == 0) {
            term = new Polynomial(computeDividedDifference(xValues, 0, index));
        } else {
            prodOfDiffs = prodOfDiffs.times(new Polynomial(-xValues.get(index - 1), 1.0));
            term = prodOfDiffs.times(computeDividedDifference(xValues, 0, index));
        }

        this.coef = this.plus(term).coef;
    }

    private double computeDividedDifference(List<Double> xValues, int from, int to) {
        List<Double> key = xValues.subList(from, to + 1);

        if (dividedDifferences.containsKey(key)) {
            return dividedDifferences.get(key);
        }

        double result;
        if (from == to) {
            result = points.get(xValues.get(from));
        } else {
            double left = computeDividedDifference(xValues, from + 1, to);
            double right = computeDividedDifference(xValues, from, to - 1);
            result = (left - right) / (xValues.get(to) - xValues.get(from));
        }

        dividedDifferences.put(new ArrayList<>(key), result);
        return result;
    }

    public void addPoint(double newX, double newY) {
        for (var x: points.keySet()) {
            if (newX == x) {
                System.out.println("Точка уже добавлена");
                return;
            }
        }
        points.put(newX, newY);

        var xValues = new ArrayList<>(points.keySet());
        plusPolynomialTerm(points.size() - 1, xValues);
    }

    public void deletePoint(double x, double y) {
        if (points.containsKey(x)) {
            points.remove(x, y);
            create();
        }
    }

    /*@Override
    public Double invoke(double x) {
        return super.invoke(x);
    }*/
}