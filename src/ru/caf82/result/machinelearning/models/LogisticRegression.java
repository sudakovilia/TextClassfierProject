package ru.caf82.result.machinelearning.models;

import ru.caf82.result.exceptions.InconveninentShapeException;
import ru.caf82.result.exceptions.ModelNotFittedException;
import ru.caf82.result.services.MathService;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by kinetik on 07.08.17.
 */
public class LogisticRegression implements MlModel {
    /**
     * alpha - l2 regularizer coefficient
     * betta - l1 regularizer coefficient
     * maxIter - number of iteration until converge
     * learnRate - learning rate of the algorithm
     * initializer - random initializer for weights of the model
     * fitted - is used for understanding if the model has trained
     * parralize - change GD to SGD with parallel computation
     * bias - model bias
     */
    private float alpha;
    private float betta;
    private int maxIter;
    private boolean parralize = true;
    private float learnRate;
    private Random initializer = new Random();

    private double[] weights;
    private boolean fitted = false;

    public LogisticRegression(float alpha, float betta, int maxIter, float learnRate, boolean parralize) {
        this.alpha = alpha;
        this.betta = betta;
        this.maxIter = maxIter;
        this.learnRate = learnRate;
        this.parralize = parralize;
    }

    public LogisticRegression(float alpha, float betta, int maxIter, float learnRate) {
        this.alpha = alpha;
        this.betta = betta;
        this.maxIter = maxIter;
        this.learnRate = learnRate;
    }

    /**
     * @param X matrix objects (in row) and features (in columns)
     * @param y model answers
     * @return fitted model
     * the algorithm is the follow:
     * step 1. shape check. We don't check features number equality
     * step 2. weights initialization. Weights are initialized from normal distribution (mean = 0, std = 1)
     * step 3. y shift. Input has y values 0 and 1. We shift it to -1 and 1
     * step 4. GradientDescend:
     * while number_of_iteration < maximum_iteration and delta_loss < threshold:
     * old_loss = cross_entropy(X, y)
     * weights += learning_rate * delta_weight
     * delta_loss = abs(old_loss - cross_entropy(X, y))
     * step 5. set model fitted to true
     * @throws InconveninentShapeException
     */

    @Override
    public MlModel train(double[][] X, int[] y) throws InconveninentShapeException {
        if (X.length != y.length) {
            throw new InconveninentShapeException();
        }
        X = MathService.matrixNormalize(X);
        double[][] transformedX = new double[X.length][X[0].length + 1];
        for (int i = 0; i < X.length; i++) {
            System.arraycopy(X[i], 0, transformedX[i], 0, X[i].length);
            transformedX[i][X[0].length] = 1;
        }
        this.weights = new double[transformedX[0].length];
        for (int i = 0; i < transformedX[0].length; i++) {
            this.weights[i] = initializer.nextGaussian();
        }
        double lossChange;
        int iterCounter = 0;
        double prevLoss;
        double newLoss;
        do {
            prevLoss = lossFunction(transformedX, this.weights, y);
            double[] weightsDelta = lossFunctionDerivative(transformedX, this.weights, y, this.alpha, this.betta);
            for(int i = 0; i < this.weights.length; i++) {
                this.weights[i] -= weightsDelta[i] * this.learnRate;
            }
            newLoss = lossFunction(transformedX, this.weights, y);
            lossChange = prevLoss - newLoss;
            iterCounter ++;
        } while (lossChange > 0.00000000001 && iterCounter < this.maxIter);
        this.fitted = true;
        return this;
    }

    /**
     * The function return class for given vector
     *
     * @param X feature vector describing one object
     * @return y value, zero or one
     * @throws ModelNotFittedException
     * @throws InconveninentShapeException
     */

    @Override
    public int predict(double[] X) throws ModelNotFittedException, InconveninentShapeException {
        if (!fitted) {
            throw new ModelNotFittedException();
        }
        return this.predictProba(X) >= 0.5 ? 1 : 0;
    }

    /**
     * The function return probability of class one
     *
     * @param X feature vector describing an object
     * @return probability of class 1 for given vector
     * @throws ModelNotFittedException
     * @throws InconveninentShapeException
     */

    @Override
    public double predictProba(double[] X) throws ModelNotFittedException, InconveninentShapeException {
        if (!fitted) {
            throw new ModelNotFittedException();
        }
        X = MathService.vectorNormalize(X);
        double[] transformedX = new double[X.length + 1];
        System.arraycopy(X, 0, transformedX, 0, X.length);
        transformedX[X.length] = 1;
        return MathService.sigmoid(transformedX, this.weights);
    }

    public double[] getWeights() {
        return this.weights;
    }

    private double lossFunction(double[][] X, double[] W, int[] y) throws InconveninentShapeException {
        double lossValue = 0;
        double tmp;
        for(int i = 0; i < X.length; i++) {
            double yHat = MathService.sigmoid(X[i], W);
            if(yHat == 1) {
                yHat = 0.99;
            }
            if(yHat == 0) {
                yHat = 0.01;
            }
            tmp = -(y[i]*Math.log(yHat) + (1 - y[i])*Math.log(1 - yHat));
            lossValue += tmp;
        }
        return lossValue / X.length;
    }

    private double[] lossFunctionDerivative(double[][] X, double[] W,
                                            int[] y, float alpha, float betta) throws InconveninentShapeException {
        double[] weightDerivatives = new double[W.length];
        double yHat;
        for(int j = 0; j < X.length; j++) {
            yHat = MathService.sigmoid(X[j], W);
            for(int i = 0; i < W.length; i++) {
                weightDerivatives[i] += X[j][i] * (yHat - y[j]) + 2 * alpha * W[i]
                        + betta * (W[i] > 0 ? 1 : W[i] == 0 ? 0 : -1);
            }
        }
        for(int k = 0; k < weightDerivatives.length; k++) {
            weightDerivatives[k] /= X.length;
        }
        return weightDerivatives;
    }

}
