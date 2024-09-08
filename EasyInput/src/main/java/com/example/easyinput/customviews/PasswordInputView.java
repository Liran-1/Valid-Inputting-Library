package com.example.easyinput.customviews;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.easyinput.R;
import com.example.easyinput.validators.PasswordValidator;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class PasswordInputView extends LinearLayout {

    private TextInputLayout password_TIL_enterYourPassword;
    private TextInputLayout password_TIL_confirmYourPassword;
    private TextInputEditText password_ETXT_enterYourPassword;
    private TextInputEditText password_ETXT_confirmYourPassword;
    private boolean requireUpperCaseLetters = true,
            requireLowerCaseLetters = true,
            requireDigits = true,
            requireSpecialCharacters = true;

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
        updateHelperText();
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

    public PasswordInputView setRequireUpperCaseLetters(boolean requireUpperCaseLetters) {
        this.requireUpperCaseLetters = requireUpperCaseLetters;
        passwordValidator.setRequireUpperCaseLetters(requireUpperCaseLetters);
        return this;
    }

    public PasswordInputView setRequireLowerCaseLetters(boolean requireLowerCaseLetters) {
        this.requireLowerCaseLetters = requireLowerCaseLetters;
        passwordValidator.setRequireLowerCaseLetters(requireLowerCaseLetters);
        return this;
    }

    public PasswordInputView setRequireDigits(boolean requireDigits) {
        this.requireDigits = requireDigits;
        passwordValidator.setRequireDigits(requireDigits);
        return this;
    }

    public PasswordInputView setRequireSpecialCharacters(boolean requireSpecialCharacters) {
        this.requireSpecialCharacters = requireSpecialCharacters;
        passwordValidator.setRequireSpecialCharacters(requireSpecialCharacters);
        return this;
    }

    public PasswordInputView setPasswordRequirements(boolean requireUpperCaseLetters, boolean requireLowerCaseLetters,
                                                     boolean requireDigits, boolean requireSpecialCharacters) {
        this.requireUpperCaseLetters = requireUpperCaseLetters;
        passwordValidator.setRequireUpperCaseLetters(requireUpperCaseLetters);

        this.requireLowerCaseLetters = requireLowerCaseLetters;
        passwordValidator.setRequireLowerCaseLetters(requireLowerCaseLetters);

        this.requireDigits = requireDigits;
        passwordValidator.setRequireDigits(requireDigits);

        this.requireSpecialCharacters = requireSpecialCharacters;
        passwordValidator.setRequireSpecialCharacters(requireSpecialCharacters);
        return this;
    }


    private void updateHelperText() {
        password_TIL_enterYourPassword.setHelperText("Password " +
                ((requireUpperCaseLetters || requireLowerCaseLetters ||
                        requireDigits || requireSpecialCharacters) ? "must contain" : "") +
                (requireUpperCaseLetters ? " upper case letters," : "") +
                (requireLowerCaseLetters ? " lower case letters," : "") +
                (requireDigits ? " digits," : "") +
                (requireSpecialCharacters ? " special characters" : "") +
                ".");
    }
}