package ru.caf82.result.machinelearning.models;

import ru.caf82.result.exceptions.InconveninentShapeException;
import ru.caf82.result.exceptions.ModelNotFittedException;

import java.io.IOException;

/**
 * Created by kinetik on 07.08.17.
 */
public interface MlModel{

    MlModel train(double[][] X, int[] y) throws InconveninentShapeException;

    int predict(double[] X) throws ModelNotFittedException, InconveninentShapeException;

    double predictProba(double[] X) throws ModelNotFittedException, InconveninentShapeException;

    void saveToFile(String filename) throws IOException;

}
