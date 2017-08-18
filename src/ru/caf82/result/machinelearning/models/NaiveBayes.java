package ru.caf82.result.machinelearning.models;

import ru.caf82.result.exceptions.InconveninentShapeException;
import ru.caf82.result.exceptions.ModelNotFittedException;

public class NaiveBayes implements MlModel{




    @Override
    public MlModel train(double[][] X, int[] y) throws InconveninentShapeException {
        return null;
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
    public void saveToFile() {

    }

    @Override
    public MlModel loadFromFile() {
        return null;
    }
}
