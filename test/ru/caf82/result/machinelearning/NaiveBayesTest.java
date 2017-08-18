package ru.caf82.result.machinelearning;

import org.junit.Assert;
import org.junit.Test;
import ru.caf82.result.exceptions.InconveninentShapeException;
import ru.caf82.result.exceptions.ModelNotFittedException;
import ru.caf82.result.machinelearning.models.ModelFactory;
import ru.caf82.result.machinelearning.models.NaiveBayes;

public class NaiveBayesTest {

    float alpha = 0f;
    ModelFactory modelFactory = new ModelFactory();

    @Test
    public void initializeTest() {
        NaiveBayes clf = modelFactory.getNaiveBayes(0);
        Assert.assertEquals(clf.getClass(), NaiveBayes.class);
    }

    @Test
    public void separatableTest() {
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
        NaiveBayes clf = modelFactory.getNaiveBayes();
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
