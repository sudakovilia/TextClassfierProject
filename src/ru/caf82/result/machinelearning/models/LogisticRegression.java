package ru.caf82.result.machinelearning.models;

import ru.caf82.result.exceptions.InconveninentShapeException;
import ru.caf82.result.exceptions.ModelNotFittedException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import ru.caf82.result.services.MathService;
/**
 * Created by kinetik on 07.08.17.
 */
public class LogisticRegression implements MlModel {

    private float alpha;
    private float betta;
    private int maxIter;
    private boolean parralize = true;
    private float learnRate;

    private float[] weights;
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

    @Override
    public MlModel train(float[][] X, int[] y) throws InconveninentShapeException {
        if(X.length!=y.length) {
            throw new InconveninentShapeException();
        }



        int iterCounter=1;
        double crossEntropyChange = 0;
        double prevCrossEntropy = 0;
        double newCrossEntropy = 0;
        do {
            prevCrossEntropy = crossEntropy(X, y);


            iterCounter+=1;
            if(iterCounter>=this.maxIter) {
                break;
            }
        } while (crossEntropyChange >= 0.01);
    }

    @Override
    public int predict(float[] X) throws ModelNotFittedException, InconveninentShapeException {
        if(!fitted){
            throw new ModelNotFittedException();
        }
        return MathService.dotProduct(X, this.weights) >= 0 ? 1 : 0;
    }

    @Override
    public double predictProba(float[] X) throws ModelNotFittedException, InconveninentShapeException {
        if(!fitted) {
            throw new ModelNotFittedException();
        }
        return Math.exp(MathService.dotProduct(X, this.weights))/(1+Math.exp(MathService.dotProduct(X, this.weights)));
    }


    private double indentCounter(float[] X, int y) throws  InconveninentShapeException {
        return y * MathService.dotProduct(X, this.weights);
    }

    private double crossEntropy(float[][] X, int[] y) throws InconveninentShapeException {
        double sum = 0;
        if(X.length!=y.length) {
            throw new InconveninentShapeException();
        }
        for(int i=0;i<y.length;i++) {
            sum += Math.log(1+Math.exp(-indentCounter(X[i], y[i])));
        }
        return sum/y.length;
    }

    private double[] deltaWeightsCounter(float[][] X, int[] y) throws InconveninentShapeException {
        double[] weightsUpdate = new double[this.weights.length];
        for(int i=0;i<X[0].length;i++) {
            double weightDelta = 2*this.alpha*this.weights[i] +
                    this.betta*(this.weights[i]>0 ? 1 : this.weights[i]==0 ? 0 : -1);
            int crossEntropySum = 0;
            for(int j=0; j<X.length;j++) {
                crossEntropySum += y[j]*X[j][i]/(1+Math.exp(-indentCounter(X[j], y[j])));
            }
            weightDelta += - crossEntropySum / X.length;
            weightDelta = -weightDelta;
            weightsUpdate[i] = weightDelta;
        }
        return weightsUpdate;
    }
}
