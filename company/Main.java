package com.company;

import com.company.algorithms.KMPalgorithm;
import com.company.dataObjects.Pattern;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class Main {

    public static void main(String[] args) throws Exception {
        String filePath = args[0];
        String patternsInput = args[1];

//      Managing patterns

        File fileWithPatterns = new File(patternsInput);
        Scanner scanner = new Scanner(fileWithPatterns);
        List<Pattern> patterns = new ArrayList();

        while (scanner.hasNextLine()) {
            String[] patternInfo = scanner.nextLine().split(";");
            int priority = Integer.parseInt(patternInfo[0]);
            String pattern = patternInfo[1].replace("\"", "");
            String output = patternInfo[2].replace("\"", "");
            Pattern patternObject = new Pattern(priority, pattern, output);
            patterns.add(patternObject);
        }

//      Managing files

        File rootDirectory = new File(filePath);
        File[] files = rootDirectory.listFiles();
        AlgorithmController algorithmController = new AlgorithmController();
        algorithmController.setAlgorithm(new KMPalgorithm());
        int threadsAmount = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(threadsAmount);

//      File null pointer check

        assert files != null;

//      Processing files

        for (File file : files) {

            Future<Pattern> futureResult = executorService.submit(new ProcessTheFile(file, algorithmController, patterns));
            Pattern result = futureResult.get();

            if (result != null) {
                System.out.printf("%s: %s\n", file.getName(), result.getOutput());
            } else {
                System.out.printf("%s: Unknown file type\n", file.getName());
            }

        }

        executorService.shutdown();

    }
}
