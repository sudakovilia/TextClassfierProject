package ru.caf82.result.machinelearning.models;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class ModelFactory {

    private float DEFAULT_ALPHA = 0f;
    private float DEFAULT_BETTA = 0f;
    private int DEFAULT_MAX_ITER = 2000;
    private float DEFAULT_LEARNING_RATE = 1f;
    private boolean DEFAULT_PARALL = false;


    public ModelFactory() {}

    public LogisticRegression getLogisticRegression(){
        return new LogisticRegression(DEFAULT_ALPHA, DEFAULT_BETTA, DEFAULT_MAX_ITER,
                DEFAULT_LEARNING_RATE, DEFAULT_PARALL);
    }

    public LogisticRegression getLogisticRegression(float alpha, float betta, int maxIter, float learnRate){
        return new LogisticRegression(alpha, betta, maxIter,
                learnRate, DEFAULT_PARALL);
    }

    public LogisticRegression getLogisticRegression(float alpha, float betta, int maxIter,
                                                    float learnRate, boolean parallel){
        return new LogisticRegression(alpha, betta, maxIter,
                learnRate, parallel);
    }

    public LogisticRegression getLogisticRegression(String filename) {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            LogisticRegression logReg = (LogisticRegression) ois.readObject();
            return logReg;
        } catch (Exception ex) {
            return this.getLogisticRegression();
        }
    }
}
