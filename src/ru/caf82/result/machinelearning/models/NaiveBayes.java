package ru.caf82.result.machinelearning.models;

import ru.caf82.result.exceptions.InconveninentShapeException;
import ru.caf82.result.exceptions.ModelNotFittedException;
import ru.caf82.result.services.MathService;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class NaiveBayes implements MlModel{

    private float alpha;
    private double[][] weights;
    private boolean fitted;
    private boolean parallel;

    public NaiveBayes(float alpha, boolean parallel) {
        this.fitted = false;
        this.parallel = parallel;
        this.alpha = alpha;
    }


    @Override
    public MlModel train(double[][] X, int[] y) throws InconveninentShapeException {

        weights = new double[2][X[0].length];
        for(int j = 0; j < X[0].length; j++) {
            int pos = 0;
            int neg = 0;
            int total = 0;
            for(int k = 0; k < X.length; k++) {
                if(X[k][j] != 0){
                    total += X[k][j];
                    if(y[k] == 0) {
                        neg += X[k][j];
                    } else {
                        pos += X[k][j];
                    }
                }
            }
            weights[0][j] = (neg + this.alpha)/(total + this.alpha * X[0].length);
            weights[1][j] = (pos + this.alpha)/(total + this.alpha * X[0].length);
        }

        fitted = true;

        return this;
    }

    @Override
    public int predict(double[] X) throws ModelNotFittedException, InconveninentShapeException {
        double productZero = MathService.dotProduct(X, this.weights[0]) / MathService.getRowSum(X);
        double productOne = MathService.dotProduct(X, this.weights[1]) / MathService.getRowSum(X);
        return productOne > productZero ? 1 : 0;
    }

    @Override
    public double[] predictProba(double[] X) throws ModelNotFittedException, InconveninentShapeException {
        return new double[]{MathService.dotProduct(X, this.weights[0]) / MathService.getRowSum(X),
                MathService.dotProduct(X, this.weights[1]) / MathService.getRowSum(X)};
    }

    @Override
    public void saveToFile(String filename) throws IOException {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(this);
        } catch (IOException ex) {
            throw new IOException("Косяк в записи");
        }
    }

    public float getAlpha() {
        return alpha;
    }

    public double[][] getWeights() {
        return weights;
    }

    public boolean isFitted() {
        return fitted;
    }
}
