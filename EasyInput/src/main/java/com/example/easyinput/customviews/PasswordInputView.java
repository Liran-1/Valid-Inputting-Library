package com.example.easyinput.customviews;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.easyinput.R;
import com.example.easyinput.utils.Constants;
import com.example.easyinput.validators.PasswordValidator;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

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
        initViews();
    }

    private void findViews() {
        password_TIL_enterYourPassword = findViewById(R.id.password_TIL_enterYourPassword);
        password_TIL_confirmYourPassword = findViewById(R.id.password_TIL_confirmYourPassword);
        password_ETXT_enterYourPassword = findViewById(R.id.password_ETXT_enterYourPassword);
        password_ETXT_confirmYourPassword = findViewById(R.id.password_ETXT_confirmYourPassword);
    }

    private void initViews() {
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
                updatePasswordField(s.toString().trim());

                String confirmPassword = password_ETXT_confirmYourPassword.getText().toString();
                updateConfirmPasswordField(confirmPassword);
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
                updateConfirmPasswordField(s.toString().trim());
            }
        });
    }

    private void updatePasswordField(String passwordText) {
        String errorMessage = passwordValidator.validatePassword(passwordText);
        password_TIL_enterYourPassword.setError(errorMessage);
    }

    private void updateConfirmPasswordField(String confirmPasswordText) {
        String firstPasswordText = password_ETXT_enterYourPassword.getText().toString().trim();
        if (firstPasswordText.isEmpty()) {
            password_TIL_confirmYourPassword.setError(null);
        }
        String errorMessage = passwordValidator.validateConfirmPassword(firstPasswordText, confirmPasswordText);
        password_TIL_confirmYourPassword.setError(errorMessage);
    }

    public PasswordInputView setMustContainUpperCaseLetters(boolean mustContainUpperCaseLetters) {
        this.mustContainUpperCaseLetters = mustContainUpperCaseLetters;
        passwordValidator.setMustContainUpperCaseLetters(mustContainUpperCaseLetters);
        return this;
    }

    public PasswordInputView setMustContainLowerCaseLetters(boolean mustContainLowerCaseLetters) {
        this.mustContainLowerCaseLetters = mustContainLowerCaseLetters;
        passwordValidator.setMustContainLowerCaseLetters(mustContainLowerCaseLetters);
        return this;
    }

    public PasswordInputView setMustContainDigits(boolean mustContainDigits) {
        this.mustContainDigits = mustContainDigits;
        passwordValidator.setMustContainDigits(mustContainDigits);
        return this;
    }

    public PasswordInputView setMustContainSpecialCharacters(boolean mustContainSpecialCharacters) {
        this.mustContainSpecialCharacters = mustContainSpecialCharacters;
        passwordValidator.setMustContainSpecialCharacters(mustContainSpecialCharacters);
        return this;
    }
}