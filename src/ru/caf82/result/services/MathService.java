package ru.caf82.result.services;

import ru.caf82.result.exceptions.InconveninentShapeException;

import java.util.Arrays;

/**
 * Created by kinetik on 07.08.17.
 */
public class MathService {

    public static float dotProduct(double[] a, double[] b) throws InconveninentShapeException {
        if(a.length != b.length) {
            throw new InconveninentShapeException("Shape a:" + a.length + " is not equal shape b: "+b.length);
        }
        float sum = 0f;
        for(int i=0;i<a.length;i++) {
            sum += a[i]*b[i];
        }
        return sum;
    }

    public static double sigmoid(double x) {
        return 1/(1+Math.exp(-x));
    }

    public static double sigmoid(double[] x, double[] w) throws InconveninentShapeException {
        return sigmoid(MathService.dotProduct(x, w));
    }

    public static double[][] matrixNormalize(double[][] X) {
        double meanValue;
        double stdValue = 0;
        for(int i = 0; i < X.length; i++) {
            meanValue = Arrays.stream(X[i]).sum() / X[i].length;
            for(double value : X[i]) {
                stdValue += (value - meanValue) * (value - meanValue);
            }
            stdValue /= (X[i].length - 1);
            if(stdValue != 0) {
                for(int j = 0; j < X[i].length; j++) {
                    X[i][j] = (X[i][j] - meanValue) / Math.sqrt(stdValue);
                }
            }
        }
        return X;
    }

    public static double[] vectorNormalize(double[] X) {
        double meanValue;
        double stdValue = 0;
        meanValue = Arrays.stream(X).sum() / X.length;
        for(double value : X) {
            stdValue += (value - meanValue) * (value - meanValue);
        }
        stdValue /= (X.length - 1);
        if(stdValue == 0) {
            return X;
        }
        for(int j = 0; j < X.length; j++) {
            X[j] = (X[j] - meanValue) / Math.sqrt(stdValue);
        }
        return X;
    }

    public static float[][] getUniMatrix(int n) {
        float[][] eMatrix = new float[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                eMatrix[i][j] = i == j ? 1f : 0f;
            }
        }
        return eMatrix;
    }

    public static double getRowSum(double[] X) {
        double sum = 0;
        for(double i : X) {
            sum += i;
        }
        return sum;
    }

    /**
     * Обращение с помощью метода гаусса-жордано
     * @param A
     */
    public static float[][] inversion(float[][] A){
        double temp;
        int lengthMatrix = A.length;
        float[][] E = getUniMatrix(lengthMatrix);
        for (int k = 0; k < lengthMatrix; k++) {
            temp = A[k][k];
            if(temp != 0) {
                for (int j = 0; j < lengthMatrix; j++) {
                    A[k][j] /= temp;
                    E[k][j] /= temp;
                }
            }
            for (int i = k + 1; i < lengthMatrix; i++) {
                temp = A[i][k];
                for (int j = 0; j < lengthMatrix; j++) {
                    A[i][j] -= A[k][j] * temp;
                    E[i][j] -= E[k][j] * temp;
                }
            }
        }
        for (int k = lengthMatrix - 1; k > 0; k--) {
            for (int i = k - 1; i >= 0; i--) {
                temp = A[i][k];
                for (int j = 0; j < lengthMatrix; j++) {
                    A[i][j] -= A[k][j] * temp;
                    E[i][j] -= E[k][j] * temp;
                }
            }
        }
        return E;
    }
}
