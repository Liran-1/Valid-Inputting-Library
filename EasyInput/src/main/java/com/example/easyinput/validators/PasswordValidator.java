package com.example.easyinput.validators;

import com.example.easyinput.utils.Constants;

import java.util.regex.Pattern;

public class PasswordValidator {

    private boolean requireUppercase = true;
    private boolean requireLowercase = true;
    private boolean requireDigit = true;
    private boolean requireSpecialChar = true;

    public String validatePassword(String password) {
        if (password.matches(Constants.PASSWORD_BLACKLIST_CHARACTERS_REGEX)) {
            return "Password contains illegal characters";
        }
        if (password.length() < Constants.PASSWORD_MINIMUM_LENGTH) {
            return "Password is too short";
        }
        if (requireUppercase && !Pattern.compile(Constants.PASSWORD_UPPERCASE_LETTERS_REGEX).matcher(password).find()) {
            return "Password must contain upper case!";
        }
        if (requireLowercase && !Pattern.compile(Constants.PASSWORD_LOWERCASE_LETTERS_REGEX).matcher(password).find()) {
            return "Password must contain lower case!";
        }
        if (requireDigit && !Pattern.compile(Constants.PASSWORD_DIGITS_REGEX).matcher(password).find()) {
            return "Password must contain digits!";
        }
//        if (requireSpecialChar && !Pattern.compile("[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]").matcher(password).find()) {
        if (requireSpecialChar && !Pattern.compile(Constants.PASSWORD_WHITELISTED_SPECIAL_CHARACTERS_REGEX).matcher(password).find()) {
            return "Password must contain special characters";
        }
        return null;
    }

    public String validateConfirmPassword(String firstPassword, String confirmPassword) {
        if (!firstPassword.equals(confirmPassword)) {
            return "Passwords do not match";
        }
        return null;
    }

}
