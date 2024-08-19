package com.example.easyinput.validators;

import java.util.regex.Pattern;

public class AddressValidator {
    private static final Pattern NUMERIC_PATTERN = Pattern.compile("\\d+");

    public boolean isValidAddress(String street, String city, String floor, String apartment, String entrance) {
        return !street.isEmpty() && !city.isEmpty() &&
                (floor.isEmpty() || NUMERIC_PATTERN.matcher(floor).matches()) &&
                (apartment.isEmpty() || NUMERIC_PATTERN.matcher(apartment).matches());
    }
}
