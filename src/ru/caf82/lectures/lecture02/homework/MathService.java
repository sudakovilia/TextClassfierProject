package ru.caf82.lectures.lecture02.homework;

class MathService {
    static double dotProduct(double[] a, double[] b) {
        if (a.length == b.length) {
            double result = 0;
            for (int i = 0; i < a.length; i++) {
                result += a[i] * b[i];
            }
            return result;
        }
        return 0;
    }

    static double sigmoid(double x) {
        return 1 / (1 + Math.exp(-x));
    }

    static double sigmoidToVectors (double[] first, double[] second){
        return MathService.sigmoid(MathService.dotProduct(first, second));
    }

    private static double sampleMean(double... sample) {
        double sum = 0;
        for (double i : sample) sum += i;
        return sum / sample.length;
    }

    private static double sampleVariance(double... sample) {
        double sampleMean = MathService.sampleMean(sample);
        double sum = 0;
        for (double i : sample) {
            sum += Math.pow(i - sampleMean, 2);
        }

        return sum/(sample.length - 1);
    }

    static double[] normalizeVector(double... vector){
        double vectorMean = MathService.sampleMean(vector);
        double vectorVariance = MathService.sampleVariance(vector);

        double[] vectorCopy = vector.clone();

        for (int i = 0; i < vectorCopy.length; i++){
            vectorCopy[i] = (vectorCopy[i] - vectorMean)/Math.sqrt(vectorVariance);
        }

        return vectorCopy;
    }

    static double[][] normalizeMatrix(double[][] matrix){
        double[][] matrixCopy = matrix.clone();

        for (int i = 0; i < matrixCopy.length; i++){
            matrixCopy[i] = MathService.normalizeVector(matrixCopy[i]);
        }
        return matrixCopy;
    }

}