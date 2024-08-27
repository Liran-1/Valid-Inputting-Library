package com.example.easyinput.validators;

import java.util.regex.Pattern;

public class UrlValidator {

        private static final Pattern URL_PATTERN =
                Pattern.compile("^(https?|ftp)://[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}(?:/[a-zA-Z0-9._%+-]*)*$");

        public boolean isValidUrl(String url) {
            return URL_PATTERN.matcher(url).matches();
        }



}
