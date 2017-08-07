package ru.caf82.result.services;

import ru.caf82.result.exceptions.InconveninentShapeException;

/**
 * Created by kinetik on 07.08.17.
 */
public class MathService {

    public static float dotProduct(double[] a, double[] b) throws InconveninentShapeException {
        if(a.length != b.length) {
            throw new InconveninentShapeException("Shape a:" + a.length + " is not equal shape b: "+b.length);
        }
        float sum = 0f;
        for(int i=0;i<a.length;i++) {
            sum += a[i]*b[i];
        }
        return sum;
    }
}
