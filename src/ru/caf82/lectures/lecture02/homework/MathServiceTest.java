package ru.caf82.lectures.lecture02.homework;

import java.util.Arrays;

class MathServiceTest {
    public static void main(String[] args) {
        //task #2
        double[] firstArray = {0.05, .78};
        double[] secondArray = {0.146, .0871};
        System.out.println("Dot product = "
                + MathService.dotProduct(firstArray, secondArray));

        //task #3
        System.out.println("Sigmoid = "
                + MathService.sigmoid(0.075238));

        //task #4
        System.out.println("Sigmoid applied to two vectors = "
                + MathService.sigmoidToVectors(firstArray, secondArray));

        //task #5
        double[] vector = {1, 2, 0, 3};
        System.out.println(Arrays.toString(vector));
        double[] newVector = MathService.normalizeVector(vector);
        System.out.println(Arrays.toString(newVector));

        double[][] matrix = {{1,2,0,3},{1,2,0,3},{1,2,0,3}};
        System.out.println(Arrays.deepToString(matrix));
        double[][] newMatrix = MathService.normalizeMatrix(matrix);
        System.out.println(Arrays.deepToString(newMatrix));

    }
}

