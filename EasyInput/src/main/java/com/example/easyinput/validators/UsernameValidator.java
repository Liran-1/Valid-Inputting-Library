package com.example.easyinput.validators;

import java.util.regex.Pattern;

public class UsernameValidator {
    private Pattern allowedCharactersPattern;
    private int minLength;

    public UsernameValidator(String allowedCharactersRegex, int minLength) {
        this.allowedCharactersPattern = Pattern.compile(allowedCharactersRegex);
        this.minLength = minLength;
    }

    public boolean isValidUsername(String username) {
        return username.length() >= minLength &&
                allowedCharactersPattern.matcher(username).matches();
    }
}
