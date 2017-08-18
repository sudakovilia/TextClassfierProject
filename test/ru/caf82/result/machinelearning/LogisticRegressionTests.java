package ru.caf82.result.machinelearning;


import org.junit.Assert;
import org.junit.Test;
import ru.caf82.result.exceptions.InconveninentShapeException;
import ru.caf82.result.exceptions.ModelNotFittedException;
import ru.caf82.result.machinelearning.models.LogisticRegression;
import ru.caf82.result.machinelearning.models.ModelFactory;

public class LogisticRegressionTests {

    float alpha = 0.0f;
    float betta = 0.0f;
    int maxIter = 2000;
    float learnRate = 0.7f;
    private ModelFactory modelFactory = new ModelFactory();

    @Test
    public void initializeTests() {
        LogisticRegression clf = modelFactory.getLogisticRegression(alpha, betta, maxIter, learnRate);
        Assert.assertEquals(clf.getClass(), LogisticRegression.class);
    }

    @Test
    public void trainLinearSeparableTests() {
        double[][] xTest = new double[10000][2];
        int[] yTest = new int[10000];
        int fillCounter = 0;
        for(int i = 0; i < 100; i++) {
            for(int j = 0; j < 100; j++) {
                xTest[fillCounter] = new double[]{i, j};
                yTest[fillCounter] = i > j ? 1 : 0;
                fillCounter++;
            }
        }
        LogisticRegression clf = modelFactory.getLogisticRegression(alpha, betta, maxIter, learnRate);
        try {
            clf.train(xTest, yTest);
        } catch (InconveninentShapeException e) {
            e.printStackTrace();
        }
        try {
            float scoreSum = 0;
            for(int i = 0; i < xTest.length; i++) {
                int yPred = clf.predict(xTest[i]);
                scoreSum += yPred == yTest[i] ? 1 : 0;
            }
            Assert.assertTrue(scoreSum / xTest.length > 0.65);
        } catch (ModelNotFittedException e) {
            e.printStackTrace();
        } catch (InconveninentShapeException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void trainLinearSeparableTestsInvert() {
        double[][] xTest = new double[10000][2];
        int[] yTest = new int[10000];
        int fillCounter = 0;
        for(int i = 0; i < 100; i++) {
            for(int j = 0; j < 100; j++) {
                xTest[fillCounter] = new double[]{i, j};
                yTest[fillCounter] = i > j ? 0 : 1;
                fillCounter++;
            }
        }
        LogisticRegression clf = modelFactory.getLogisticRegression(alpha, betta, maxIter, learnRate);
        try {
            clf.train(xTest, yTest);
        } catch (InconveninentShapeException e) {
            e.printStackTrace();
        }
        try {
            float scoreSum = 0;
            for(int i = 0; i < xTest.length; i++) {
                int yPred = clf.predict(xTest[i]);
                scoreSum += yPred == yTest[i] ? 1 : 0;
            }
            Assert.assertTrue(scoreSum / xTest.length > 0.65);
        } catch (ModelNotFittedException e) {
            e.printStackTrace();
        } catch (InconveninentShapeException e) {
            e.printStackTrace();
        }
    }
}
