package ru.gr362.math;

import org.jetbrains.annotations.NotNull;
import ru.gr362.ui.Function;

import java.util.Collections;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class Polynomial implements Function {
    protected SortedMap<Integer, Double> coef;

    public Polynomial() {
        coef = new TreeMap<Integer, Double>();
        clearIncorrect();
    }

    public Polynomial(@NotNull Map<Integer, Double> coef) {
        this.coef = new TreeMap<Integer, Double>(coef);
        clearIncorrect();
    }

    public Polynomial(double ...coef) {
        var tm = new TreeMap<Integer, Double>();
        for (int i = 0; i < coef.length; i++) {
            tm.put(i, coef[i]);
        }
        this(tm);
    }

    private void clearIncorrect() {
        for (var k: coef.keySet().toArray()) {
            if (coef.get((int)k) == 0.0 || (int)k < 0)
                coef.remove((int)k);
        }
        if (coef.isEmpty()) coef.put(0, 0.0);
    }

    public SortedMap<Integer, Double> getCoef() {
        return new TreeMap<>(coef);
    }

    public String toString(String variable) {
        var sb = new StringBuilder();
        for (var pow: coef.reversed().keySet()) {
            if (coef.get(pow) < 0.0) sb.append('-');
            else if (Collections.max(coef.keySet()).intValue() != pow)
                sb.append('+');
            if (Math.abs(coef.get(pow)) != 1.0 || pow == 0) sb.append(Math.abs(coef.get(pow)));
            if (pow != 0) sb.append(variable);
            if (pow > 1) sb.append("^").append(pow);
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return toString("x");
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Polynomial)) return false;
        return ((Polynomial) other).coef.equals(coef);
    }

    @Override
    public int hashCode() {
        return coef.hashCode() * 31;
    }

    public int getPower() {
        return coef.lastKey();
    }

    public Polynomial plus(Polynomial other) {
        var sum = new TreeMap<>(coef);
        for (var c: other.coef.keySet()) {
            if (sum.containsKey(c))
                sum.put(c, other.coef.get(c) + sum.get(c));
            else
                sum.put(c, other.coef.get(c));
        }
        return new Polynomial(sum);
    }

    public Polynomial minus(Polynomial other) {
        var sum = new TreeMap<>(coef);
        for (var c: other.coef.keySet()) {
            if (sum.containsKey(c))
                sum.put(c, -other.coef.get(c) + sum.get(c));
            else
                sum.put(c, -other.coef.get(c));
        }
        return new Polynomial(sum);
    }

    public Polynomial times(Polynomial other){
        var prod = new TreeMap<Integer, Double>();
        for (int tc: coef.keySet()){
            for (int oc: other.coef.keySet()){
                if (prod.containsKey(oc+tc)) {
                    prod.put(tc + oc, coef.get(tc) * other.coef.get(oc) + prod.get(tc + oc));
                } else {
                    prod.put(tc + oc, coef.get(tc) * other.coef.get(oc));
                }
            }
        }
        return new Polynomial(prod);
    }

    public Polynomial times(double k) {
        var prod = new TreeMap<>(coef);
        prod.replaceAll((c, v) -> v * k);
        return new Polynomial(prod);
    }

    public Polynomial div(double k) {
        var prod = new TreeMap<>(coef);
        prod.replaceAll((c, v) -> v / k);
        return new Polynomial(prod);
    }

    public Double invoke(double x) {
        var result = 0.0;
        for (var pow: coef.keySet())
            result += Math.pow(x, pow) * coef.get(pow);
        return result;
    }

    public Polynomial differentiate() {
        var result = new TreeMap<Integer, Double>();
        for (var degree : coef.keySet()) {
            double coeff = coef.get(degree);

            result.put(degree - 1, coeff * degree);

        }
        return new Polynomial(result);
    }
}