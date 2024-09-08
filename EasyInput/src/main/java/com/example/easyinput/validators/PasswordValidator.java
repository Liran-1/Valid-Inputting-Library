package com.example.easyinput.validators;

import com.example.easyinput.utils.Constants;

import java.util.regex.Pattern;

public class PasswordValidator {

    private boolean requireUpperCaseLetters = true;
    private boolean requireLowerCaseLetters = true;
    private boolean requireDigits = true;
    private boolean requireSpecialCharacters = true;

    public void setRequireUpperCaseLetters(boolean requireUpperCaseLetters) {
        this.requireUpperCaseLetters = requireUpperCaseLetters;
    }

    public void setRequireLowerCaseLetters(boolean requireLowerCaseLetters) {
        this.requireLowerCaseLetters = requireLowerCaseLetters;
    }

    public void setRequireDigits(boolean requireDigits) {
        this.requireDigits = requireDigits;
    }

    public void setRequireSpecialCharacters(boolean requireSpecialCharacters) {
        this.requireSpecialCharacters = requireSpecialCharacters;
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
        if (requireUpperCaseLetters && !Pattern.compile(Constants.PASSWORD_UPPERCASE_LETTERS_REGEX).matcher(password).find()) {
            return "Password must contain upper case!";
        }
        if (requireLowerCaseLetters && !Pattern.compile(Constants.PASSWORD_LOWERCASE_LETTERS_REGEX).matcher(password).find()) {
            return "Password must contain lower case!";
        }
        if (requireDigits && !Pattern.compile(Constants.PASSWORD_DIGITS_REGEX).matcher(password).find()) {
            return "Password must contain digits!";
        }
//        if (requireSpecialChar && !Pattern.compile("[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]").matcher(password).find()) {
        if (requireSpecialCharacters && !Pattern.compile(Constants.PASSWORD_WHITELISTED_SPECIAL_CHARACTERS_REGEX).matcher(password).find()) {
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
