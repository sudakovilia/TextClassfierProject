package ru.caf82.lectures.lecture02;

public class MathServiceTest {
    public static void main(String[] args) {
        double[] f = {2.05, 2.78};
        double[] s = {5.146, .0871};
        System.out.println("Dot product = " + String.format("%.3f", MathService.dotProduct(f, s)));
        System.out.println("Sigmoid = " + MathService.sigmoid(.05));
    }
}

class MathService {
    public static double dotProduct(double[] a, double[] b) {
        if (a.length == b.length) {
            double result = 0;
            for (int i = 0; i < a.length; i++) {
                result += a[i] * b[i];
            }
            return result;
        }
        return 0;
    }

    public static double sigmoid(double x) {
        return 1 / (1 + Math.exp(-x));
    }
}