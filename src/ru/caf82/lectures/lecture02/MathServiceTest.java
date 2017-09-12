package ru.caf82.lectures.lecture02;

public class MathServiceTest {
    public static void main(String[] args) {
        double[] f = {2, 2};
        double[] s = {5, 6};
        System.out.println(MathService.dotProduct(f, s));
    }
}

class MathService{
    public static double dotProduct(double[] a, double[] b){
        double result = 0;
        if (a.length == b.length){
            for (int i=0; i<a.length; i++){
                result += a[i]*b[i];
            }
        } else return 0;

        return result;
    }
     public static double sigmoid(double x){
        return 0;
     }
}