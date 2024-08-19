package com.example.easyinput.validators;

import java.util.regex.Pattern;

public class PhoneValidator {
    private Pattern phonePattern;
    private int validLength;

    public PhoneValidator(String regexPattern, int validLength) {
        this.phonePattern = Pattern.compile(regexPattern);
        this.validLength = validLength;
    }

    public boolean isValidPhone(String phone) {
        return phone.length() == validLength &&
                phonePattern.matcher(phone).matches();
    }
}
