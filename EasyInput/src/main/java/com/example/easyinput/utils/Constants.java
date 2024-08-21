package com.example.easyinput.utils;

public class Constants {

    private Constants() {}

    public static final int MAXIMUM_AGE = 120;
    public static final int MINIMUM_AGE = 0;

    public static final String EMAIL_REGEX = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$";
    public static final String EMAIL_REGEX_2 = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";




    public static final String URL_PROTOCOL_REGEX = "^(https?:\\/\\/)";
    public static final String URL_PREFIX_REGEX = "(www\\.)?";
    public static final String URL_DOMAIN_REGEX = "([a-zA-Z0-9-]+)";
    public static final String URL_TLD_ONE_PART_REGEX = "\\.(com|org|net|edu|gov|mil|int|biz|info|name|pro|aero|coop|museum)$";
    public static final String URL_TLD_TWO_PART_REGEX = "\\.(com|org|net|edu|gov|mil|int|biz|info|name|pro|aero|coop|museum|[a-zA-Z]{2}(\\.[a-zA-Z]{2})?)$";


    public static final String USERNAME_REGEX = "^[a-zA-Z0-9._-]{3,}$";


    public static final String PASSWORD_LARGE_LETTERS_REGEX = ".*[A-Z].*";
    public static final String PASSWORD_SMALL_LETTERS_REGEX = ".*[a-z].*";
    public static final String PASSWORD_NUMBERS_REGEX = ".*\\d.*";
    public static final String PASSWORD_WHITELISTED_SPECIAL_CHARACTERS_REGEX = ".*[!@#$%^&*()].*";
    public static final String PASSWORD_BLACKLIST_CHARACTERS_REGEX = "^[^A-Za-z0-9!@#$%^&*()]*$";
    public static final int PASSWORD_MINIMUM_LENGTH = 8;
    public static final int PASSWORD_MAXIMUM_LENGTH = 30;

}
