package ru.caf82.result.machinelearning.preprocessing;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;
import java.util.Map;

/**
 * Created by ilysko on 03.08.17.
 */
public class TfidfVectorizer implements SequenceProcessor, Transformer {
    @Override
    public List<Map<String, Integer>> fitAndTransform(List<String> listOfTexts) {
        throw new NotImplementedException();
    }

    @Override
    public void fit(List<String> listOfTexts) {
        throw new NotImplementedException();
    }

    @Override
    public List<String> preprocess(String text) {
        throw new NotImplementedException();
    }

    @Override
    public List<Map<String, Integer>> transform(List<String> listOfTexts) {
        throw new NotImplementedException();
    }
}
