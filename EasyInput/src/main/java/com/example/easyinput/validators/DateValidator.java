package com.example.easyinput.validators;

public class DateValidator {
        public boolean isValidDay(int day) {
            return day >= 1 && day <= 31;
        }

        public boolean isValidMonth(int month) {
            return month >= 1 && month <= 12;
        }

        public boolean isValidDate(int day, int month, int year) {
            // Simplified validation, real-world scenarios may need more checks
            if (month == 2) {
                return day >= 1 && day <= 28; // Leap year validation is omitted for simplicity
            } else if (month == 4 || month == 6 || month == 9 || month == 11) {
                return day >= 1 && day <= 30;
            } else {
                return day >= 1 && day <= 31;
            }
        }

}
