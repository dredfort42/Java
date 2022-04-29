package edu.school21.preprocessor;

public class PreProcessorToUpperImpl implements PreProcessor {
    @Override
    public String preProcess(String initString) {
        return initString.toUpperCase();
    }
}
