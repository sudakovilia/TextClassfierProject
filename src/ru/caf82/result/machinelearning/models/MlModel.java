package ru.caf82.result.machinelearning.models;

import ru.caf82.result.exceptions.InconveninentShapeException;
import ru.caf82.result.exceptions.ModelNotFittedException;

/**
 * Created by kinetik on 07.08.17.
 */
public interface MlModel{

    MlModel train(float[][] X, int[] y);

    int predict(float[] X) throws ModelNotFittedException, InconveninentShapeException;

    double predictProba(float[] X) throws ModelNotFittedException, InconveninentShapeException;
}
