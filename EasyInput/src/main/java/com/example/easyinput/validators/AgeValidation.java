package com.example.easyinput.validators;

public class AgeValidation {

    private int lowerLimit = 0;
    private int upperLimit = 120;
    private int minRange = 0;
    private int maxRange = 120;

    public boolean isValidAge(int age) {
        return age >= lowerLimit && age <= upperLimit;
    }

    public void setAgeLimits(int lower, int upper) {
        this.lowerLimit = lower;
        this.upperLimit = upper;
    }

    public void setAgeRange(int min, int max) {
        this.minRange = min;
        this.maxRange = max;
    }

    public boolean isWithinRange(int age) {
        return age >= minRange && age <= maxRange;
    }
}
