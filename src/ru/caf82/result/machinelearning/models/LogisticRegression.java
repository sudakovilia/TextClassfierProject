package ru.caf82.result.machinelearning.models;

import ru.caf82.result.exceptions.InconveninentShapeException;
import ru.caf82.result.exceptions.ModelNotFittedException;
import ru.caf82.result.services.MathService;

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
     *
     * @param X matrix objects (in row) and features (in columns)
     * @param y model answers
     * @return fitted model
     * the algorithm is the follow:
     *      step 1. shape check. We don't check features number equality (task //TODO)
     *      step 2. weights initialization. Weights are initialized from normal distribution (mean = 0, std = 1)
     *      step 3. y shift. Input has y values 0 and 1. We shift it to -1 and 1
     *      step 4. GradientDescend:
     *          while number_of_iteration < maximum_iteration and delta_loss < threshold:
     *              old_loss = cross_entropy(X, y)
     *              weights += learning_rate * delta_weight
     *              delta_loss = abs(old_loss - cross_entropy(X, y))
     *      step 5. set model fitted to true
     * @throws InconveninentShapeException
     */

    @Override
    public MlModel train(double[][] X, int[] y) throws InconveninentShapeException {
        if(X.length != y.length) {
            throw new InconveninentShapeException();
        }
        double[][] transformedX = new double[X.length][X[0].length+1];
        for(int i = 0; i<X.length; i++) {
            System.arraycopy(X[i], 0, transformedX[i], 0, X[i].length);
            transformedX[i][transformedX.length] = 1;
        }
        this.weights = new double[X[0].length];
        for(int i = 0; i < transformedX[0].length + 1; i++) {
            this.weights[i] = initializer.nextGaussian();
        }
        for(int j = 0; j < y.length; j++) {
            y[j] = y[j] == 1 ? 1 : -1;
        }
        int iterCounter = 1;
        double crossEntropyChange;
        double prevCrossEntropy;
        double newCrossEntropy;
        do {
            prevCrossEntropy = crossEntropy(transformedX, y);
            double[] weightDelta = deltaWeightsCounter(transformedX, y);
            for(int i = 0; i < weightDelta.length; i++) {
                this.weights[i] += this.learnRate * weightDelta[i];
            }
            newCrossEntropy = crossEntropy(transformedX, y);
            crossEntropyChange = Math.abs(newCrossEntropy - prevCrossEntropy);
            iterCounter ++;
            if(iterCounter>=this.maxIter) {
                break;
            }
        } while (crossEntropyChange >= 0.01);
        this.fitted = true;
        return this;
    }

    /**
     *  The function return class for given vector
     * @param X feature vector describing one object
     * @return y value, zero or one
     * @throws ModelNotFittedException
     * @throws InconveninentShapeException
     */

    @Override
    public int predict(double[] X) throws ModelNotFittedException, InconveninentShapeException {
        if(!fitted){
            throw new ModelNotFittedException();
        }
        double[] transformedX = new double[X.length+1];
        System.arraycopy(X, 0, transformedX, 0, X.length);
        transformedX[X.length] = 1;
        return MathService.dotProduct(transformedX, this.weights) >= 0 ? 1 : 0;
    }

    /**
     *  The function return probability of class one
     * @param X feature vector describing an object
     * @return probability of class 1 for given vector
     * @throws ModelNotFittedException
     * @throws InconveninentShapeException
     */

    @Override
    public double predictProba(double[] X) throws ModelNotFittedException, InconveninentShapeException {
        if(!fitted) {
            throw new ModelNotFittedException();
        }
        double[] transformedX = new double[X.length+1];
        System.arraycopy(X, 0, transformedX, 0, X.length);
        return Math.exp(MathService.dotProduct(transformedX, this.weights)) /(1 +
                Math.exp(MathService.dotProduct(transformedX, this.weights)));
    }

    /**
     *  The function compute indent for an object y*<w, x>
     * @param X the feature vector describing an object
     * @param y the object class
     * @return y*<w, x>
     * @throws InconveninentShapeException
     */

    private double indentCounter(double[] X, int y) throws  InconveninentShapeException {
        return y * MathService.dotProduct(X, this.weights);
    }


    /**
     *  The function count cross-entropy log function in the context:
     *      1/n * sum(ln(1 + e^(-y[i] * <w,x[i]>)) for i in 0..n-1
     * @param X matrix object (row) - feature (column)
     * @param y array of the model answers
     * @return
     * @throws InconveninentShapeException
     */

    private double crossEntropy(double[][] X, int[] y) throws InconveninentShapeException {
        for(int j = 0; j < y.length; j++) {
            y[j] = y[j] == 1 ? 1 : -1;
        }
        double sum = 0;
        if(X.length != y.length) {
            throw new InconveninentShapeException();
        }
        for(int i = 0; i < y.length; i++) {
            sum += Math.log(1 + Math.exp(-indentCounter(X[i], y[i])));
        }
        return sum / y.length;
    }

    /**
     * The function calculate gradient descend step
     * @param X matrix of object with 1 column
     * @param y result classes (classes should be 1 and -1)
     * @return weights delta step
     * @throws InconveninentShapeException
     */

    private double[] deltaWeightsCounter(double[][] X, int[] y) throws InconveninentShapeException {
        double[] weightsUpdate = new double[this.weights.length];
        for(int i = 0; i < X[0].length; i++) {
            double weightDelta = 2 * this.alpha*this.weights[i] +
                    this.betta * (this.weights[i] > 0 ? 1 : this.weights[i] == 0 ? 0 : -1);
            int crossEntropySum = 0;
            for(int j=0; j < X.length; j++) {
                crossEntropySum += y[j] * X[j][i] / (1 + Math.exp(- indentCounter(X[j], y[j])));
            }
            weightDelta += - crossEntropySum / X.length;
            weightDelta = - weightDelta;
            weightsUpdate[i] = weightDelta;
        }
        return weightsUpdate;
    }
}
