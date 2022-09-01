package com.company;

public class AlgorithmController {

    SubstringAlgorithm substringAlgorithm;

    void setAlgorithm(SubstringAlgorithm substringAlgorithm) {
        this.substringAlgorithm = substringAlgorithm;
    }

    boolean isSubstring(String string, String pattern) {
        return substringAlgorithm.isSubstring(string, pattern);
    }
}
