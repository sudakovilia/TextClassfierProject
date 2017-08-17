package ru.caf82.result.machinelearning.preprocessing;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ilysko on 03.08.17.
 */
public interface SequenceProcessor {
    public List<Map<String, Integer>> fitAndTransform(List<String> listOfTexts);

    public void fit(List<String> listOfTexts);

    public List<String> preprocess(String text);

    public List<Map<String, Integer>> transform(List<String> listOfTexts);
}
