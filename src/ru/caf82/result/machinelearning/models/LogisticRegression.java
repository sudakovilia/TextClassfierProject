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

    private float[] weights;
    private boolean fitted = false;

    public LogisticRegression(float alpha, float betta, int maxIter, boolean parralize) {
        this.alpha = alpha;
        this.betta = betta;
        this.maxIter = maxIter;
        this.parralize = parralize;
    }

    public LogisticRegression(float alpha, float betta, int maxIter) {
        this.alpha = alpha;
        this.betta = betta;
        this.maxIter = maxIter;
    }

    @Override
    public MlModel train(float[][] X, int[] y) {
        throw new NotImplementedException();
    }

    @Override
    public int predict(float[] X) throws ModelNotFittedException, InconveninentShapeException {
        if(!fitted){
            throw new ModelNotFittedException();
        }
        return Math.round(MathService.dotProduct(X, this.weights));
    }

    @Override
    public float predictProba(float[] X) throws ModelNotFittedException, InconveninentShapeException {
        if(!fitted) {
            throw new ModelNotFittedException();
        }
        return MathService.dotProduct(X, this.weights);
    }

    private double logistic(float[] X, int y) throws InconveninentShapeException {
        float indent = y * MathService.dotProduct(X, this.weights);
        return 1./(1.+Math.exp(-indent));
    }

    private double crossEntropy(float[][] X, int[] y) throws InconveninentShapeException {
        double sum = 0;
        if(X.length!=y.length) {
            throw new InconveninentShapeException();
        }
        for(int i=0;i<y.length;i++) {
            sum = sum*i/(i+1) + Math.log(1+logistic(X[i], y[i]))/(i+1);
        }
        return sum;
    }
}
