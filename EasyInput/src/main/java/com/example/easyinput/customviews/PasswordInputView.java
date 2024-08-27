package com.example.easyinput.customviews;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.easyinput.R;
import com.example.easyinput.utils.Constants;
import com.example.easyinput.validators.PasswordValidator;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class PasswordInputView extends LinearLayout {

    private TextInputLayout password_TIL_enterYourPassword;
    private TextInputLayout password_TIL_confirmYourPassword;
    private TextInputEditText password_ETXT_enterYourPassword;
    private TextInputEditText password_ETXT_confirmYourPassword;
    private boolean mustContainUpperCaseLetters = true,
            mustContainLowerCaseLetters = true,
            mustContainDigits = true,
            mustContainSpecialCharacters = true;

    private PasswordValidator passwordValidator = new PasswordValidator();

    public PasswordInputView(Context context) {
        super(context);
        init(context);
    }

    public PasswordInputView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PasswordInputView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public PasswordInputView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.password_input_view, this, true);

        findViews();
        initVIews();
    }

    private void initVIews() {
        password_ETXT_enterYourPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // No action needed before text changes
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // No action needed during text changes
            }

            @Override
            public void afterTextChanged(Editable s) {
                String passwordText = s.toString().trim();
                String errorMessage = passwordValidator.validatePassword(passwordText);
                password_TIL_enterYourPassword.setError(errorMessage);
            }
        });

        password_ETXT_confirmYourPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String firstPasswordText = password_ETXT_enterYourPassword.getText().toString().trim();
                if (firstPasswordText.isEmpty()) {
                    return;
                }
                String confirmPasswordText = s.toString().trim();
                String errorMessage = passwordValidator.validateConfirmPassword(firstPasswordText, confirmPasswordText);
                password_TIL_confirmYourPassword.setError(errorMessage);
            }
        });
    }

    private String validateConfirmPassword(String confirmPasswordText) {
        String firstPassword = Objects.requireNonNull(password_ETXT_enterYourPassword.getText()).toString();
        if (!firstPassword.equals(confirmPasswordText)) {
            return "Passwords do not match";
        }
        return null;
    }

    private String validatePassword(String password) {
//        if (password.isEmpty()) {
//            return false;
//        } else
//            if (password.matches(Constants.PASSWORD_BLACKLIST_CHARACTERS_REGEX)) {
//            return false;
//        } else
        if (password.length() < Constants.PASSWORD_MINIMUM_LENGTH) {
            return "Password is too short";
        }
        if (mustContainDigits && !password.matches(Constants.PASSWORD_DIGITS_REGEX)) {
            return "Password must contain digits!";
        }
        if (mustContainUpperCaseLetters && !password.matches(Constants.PASSWORD_UPPERCASE_LETTERS_REGEX)) {
            return "Password must contain upper case!";
        }
        if (mustContainLowerCaseLetters && !password.matches(Constants.PASSWORD_LOWERCASE_LETTERS_REGEX)) {
            return "Password must contain lower case!";
        }
        if (mustContainSpecialCharacters && !password.matches(Constants.PASSWORD_WHITELISTED_SPECIAL_CHARACTERS_REGEX)) {
            return "Password must contain special characters";
        }
        return null;
    }


    private void findViews() {
        password_TIL_enterYourPassword = findViewById(R.id.password_TIL_enterYourPassword);
        password_TIL_confirmYourPassword = findViewById(R.id.password_TIL_confirmYourPassword);
        password_ETXT_enterYourPassword = findViewById(R.id.password_ETXT_enterYourPassword);
        password_ETXT_confirmYourPassword = findViewById(R.id.password_ETXT_confirmYourPassword);
    }

    public PasswordInputView setMustContainUpperCaseLetters(boolean mustContainUpperCaseLetters) {
        this.mustContainUpperCaseLetters = mustContainUpperCaseLetters;
        return this;
    }

    public PasswordInputView setMustContainLowerCaseLetters(boolean mustContainLowerCaseLetters) {
        this.mustContainLowerCaseLetters = mustContainLowerCaseLetters;
        return this;
    }

    public PasswordInputView setMustContainDigits(boolean mustContainDigits) {
        this.mustContainDigits = mustContainDigits;
        return this;
    }

    public PasswordInputView setMustContainSpecialCharacters(boolean mustContainSpecialCharacters) {
        this.mustContainSpecialCharacters = mustContainSpecialCharacters;
        return this;
    }
}