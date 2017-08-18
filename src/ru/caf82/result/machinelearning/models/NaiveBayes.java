package ru.caf82.result.machinelearning.models;

import ru.caf82.result.exceptions.InconveninentShapeException;
import ru.caf82.result.exceptions.ModelNotFittedException;

import java.io.IOException;

public class NaiveBayes implements MlModel{

    private int alpha;
    private double[][] weights;
    private boolean fitted;

    public NaiveBayes(int alpha) {
        this.fitted = false;
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
                if(X[k][j]!=0){
                    total++;
                    if(y[k] == 0) {
                        neg++;
                    } else {
                        pos++;
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
        return 0;
    }

    @Override
    public double predictProba(double[] X) throws ModelNotFittedException, InconveninentShapeException {
        return 0;
    }

    @Override
    public void saveToFile(String filename) throws IOException {

    }


}
