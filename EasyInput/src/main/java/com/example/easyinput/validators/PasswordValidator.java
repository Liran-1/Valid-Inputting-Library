package com.example.easyinput.validators;

import com.example.easyinput.utils.Constants;

import java.util.regex.Pattern;

public class PasswordValidator {

    private boolean mustContainUpperCaseLetters = true;
    private boolean mustContainLowerCaseLetters = true;
    private boolean mustContainDigits = true;
    private boolean mustContainSpecialCharacters = true;

    public void setMustContainUpperCaseLetters(boolean mustContainUpperCaseLetters) {
        this.mustContainUpperCaseLetters = mustContainUpperCaseLetters;
    }

    public void setMustContainLowerCaseLetters(boolean mustContainLowerCaseLetters) {
        this.mustContainLowerCaseLetters = mustContainLowerCaseLetters;
    }

    public void setMustContainDigits(boolean mustContainDigits) {
        this.mustContainDigits = mustContainDigits;
    }

    public void setMustContainSpecialCharacters(boolean mustContainSpecialCharacters) {
        this.mustContainSpecialCharacters = mustContainSpecialCharacters;
    }

    public String validatePassword(String password) {
        if(password.isEmpty()){
            return null;
        }
        if (!password.matches(Constants.PASSWORD_BLACKLIST_CHARACTERS_REGEX)) {
            return "Password contains illegal characters";
        }
        if (password.length() < Constants.PASSWORD_MINIMUM_LENGTH) {
            return "Password is too short";
        }
        if (mustContainUpperCaseLetters && !Pattern.compile(Constants.PASSWORD_UPPERCASE_LETTERS_REGEX).matcher(password).find()) {
            return "Password must contain upper case!";
        }
        if (mustContainLowerCaseLetters && !Pattern.compile(Constants.PASSWORD_LOWERCASE_LETTERS_REGEX).matcher(password).find()) {
            return "Password must contain lower case!";
        }
        if (mustContainDigits && !Pattern.compile(Constants.PASSWORD_DIGITS_REGEX).matcher(password).find()) {
            return "Password must contain digits!";
        }
//        if (requireSpecialChar && !Pattern.compile("[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]").matcher(password).find()) {
        if (mustContainSpecialCharacters && !Pattern.compile(Constants.PASSWORD_WHITELISTED_SPECIAL_CHARACTERS_REGEX).matcher(password).find()) {
            return "Password must contain special characters";
        }
        return null;
    }

    public String validateConfirmPassword(String firstPassword, String confirmPassword) {
        if (firstPassword.isEmpty() || confirmPassword.isEmpty()) {
            return null;
        }
        if (!firstPassword.equals(confirmPassword)) {
            return "Passwords do not match";
        }
        return null;
    }



}
