package com.example.easyinput.validators;

import java.util.Locale;

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

    public static String validate(String ageText, int minAgeRange, int maxAgeRange) {
        int age;
        try {
            age = Integer.parseInt(ageText);
        } catch (NumberFormatException e) {
            return "Invalid age format";
        }

        if (age < minAgeRange || age > maxAgeRange) {
            return String.format(Locale.getDefault(),"Age must be within the range %d to %d", minAgeRange, maxAgeRange);
        }

        return null; // No error
    }
}
