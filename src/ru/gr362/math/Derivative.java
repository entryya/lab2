package ru.gr362.math;

import ru.gr362.ui.Function;

import java.util.Map;
import java.util.TreeMap;

public class Derivative extends Polynomial implements Function {

    public Derivative() {
        super();
    }

    public Derivative(Polynomial original) {
        super(differentiate(original));
    }

    private static Map<Integer, Double> differentiate(Polynomial p) {
        var result = new TreeMap<Integer, Double>();
        for (var entry : p.getCoef().entrySet()) {

            int degree = entry.getKey();
            double coeff = entry.getValue();

            if (degree > 0) {
                result.put(degree - 1, coeff * degree);
            }
        }
        return result;
    }
}
