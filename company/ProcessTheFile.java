package com.company;

import com.company.dataObjects.Pattern;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.Callable;

class ProcessTheFile implements Callable {

    File file;
    AlgorithmController algorithmController;
    List<Pattern> patterns;

    ProcessTheFile(File file, AlgorithmController algorithmController, List<Pattern> patterns) {
        this.file = file;
        this.algorithmController = algorithmController;
        this.patterns = patterns;
    }

    @Override
    public Object call() {

        String string = "";

        try (InputStream inputStream = new FileInputStream(file)) {

            byte[] bytes = inputStream.readAllBytes();

            for (byte elem : bytes) {
                string += (char) elem;
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        Pattern biggestPriorityPattern = null;

        for (Pattern pattern : patterns) {
            boolean patternMatch = algorithmController.isSubstring(string, pattern.getPattern());
            if (patternMatch) {
                if (biggestPriorityPattern == null) {
                    biggestPriorityPattern = pattern;
                } else {
                    biggestPriorityPattern = biggestPriorityPattern.getPriority() > pattern.getPriority()
                            ?
                            biggestPriorityPattern
                            :
                            pattern;
                }
            }
        }

        return biggestPriorityPattern;

    }
}
