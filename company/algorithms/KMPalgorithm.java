package com.company.algorithms;

import com.company.SubstringAlgorithm;

public class KMPalgorithm implements SubstringAlgorithm {

    @Override
    public boolean isSubstring(String string, String pattern) {

        boolean isMatch = false;
        int[] prefixFunctionResult = this.prefixFunction(pattern);
        int stringLength = string.length();
        int patternLength = pattern.length();
        int i = 0;
        int j = 0;

        while (i < stringLength) {

            if (string.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;

                if (j == patternLength) {
                    isMatch = true;
                    j = prefixFunctionResult[j - 1];
                }
            } else if (i < stringLength && pattern.charAt(j) != string.charAt(i)) {

                if (j > 0) {
                    j = prefixFunctionResult[j - 1];
                } else {
                    i = i + 1;
                }

            }
        }

        return isMatch;
    }

    int[] prefixFunction(String substring) {
        int n = substring.length();
        int[] p = new int[n];
        int j;

        for (int i = 1; i < n; i++) {

            j = p[i - 1];

            while (j > 0 && substring.charAt(j) != substring.charAt(i)) {
                j = p[j - 1];
            }

            if (substring.charAt(j) == substring.charAt(i)) {
                p[i] = p[i - 1] + 1;
            }
        }

        return p;
    }
}
