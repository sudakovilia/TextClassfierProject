package ru.caf82.result.machinelearning;


import org.junit.Assert;
import org.junit.Test;
import ru.caf82.result.exceptions.InconveninentShapeException;
import ru.caf82.result.exceptions.ModelNotFittedException;
import ru.caf82.result.machinelearning.models.LogisticRegression;

public class LogisticRegressionTests {

    float alpha = 0.0f;
    float betta = 0.2f;
    int maxIter = 200000;
    float learnRate = 0.001f;

    @Test
    public void initializeTests() {
        LogisticRegression clf = new LogisticRegression(alpha, betta, maxIter, learnRate);
        Assert.assertEquals(clf.getClass(), LogisticRegression.class);
    }

    @Test
    public void trainLinearSeparableTests() {
        double[][] X = {
            {0, 0},
            {0, 10},
            {10, 0},
            {10, 10},
        };
        int[] y = {0, 0, 0, 1};
        LogisticRegression clf = new LogisticRegression(alpha, betta, maxIter, learnRate);
        try {
            clf.train(X, y);
        } catch (InconveninentShapeException e) {
            e.printStackTrace();
        }
        try {
            System.out.println(clf.predictProba(new double[]{0, 0}));
            System.out.println(clf.predictProba(new double[]{10, 0}));
            System.out.println(clf.predictProba(new double[]{0, 10}));
            System.out.println(clf.predictProba(new double[]{10, 10}));
            System.out.println(clf.getWeights());
            Assert.assertEquals(0, clf.predict(new double[]{0, 0}));
            Assert.assertEquals(0, clf.predict(new double[]{0, 10}));
            Assert.assertEquals(0, clf.predict(new double[]{10, 0}));
            Assert.assertEquals(1, clf.predict(new double[]{10, 10}));
        } catch (ModelNotFittedException e) {
            e.printStackTrace();
        } catch (InconveninentShapeException e) {
            e.printStackTrace();
        }
    }
}
