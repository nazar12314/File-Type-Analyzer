package com.company.dataObjects;

public class Pattern {

    private int priority;
    private String pattern;
    private String output;

    public Pattern(int priority, String pattern, String output) {
        this.priority = priority;
        this.pattern = pattern;
        this.output = output;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    @Override
    public String toString() {
        return "Pattern{" +
                "priority=" + priority +
                ", pattern='" + pattern + '\'' +
                ", output='" + output + '\'' +
                '}';
    }
}
