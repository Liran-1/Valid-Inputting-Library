package com.example.easyinput.customviews;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.easyinput.R;
import com.example.easyinput.utils.Constants;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class UsernameInputView extends TextInputLayout {


    private TextInputEditText user_ETXT_enterYourUsername;

    private int minLength = 3; // Default minimum length



    public UsernameInputView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public UsernameInputView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public UsernameInputView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.username_input_view, this, true);

        findViews();
        initViews();
    }

    private void findViews() {
        user_ETXT_enterYourUsername = findViewById(R.id.username_ETXT_enterUsername);
    }

    private void initViews() {
        user_ETXT_enterYourUsername.addTextChangedListener(new TextWatcher() {
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
                String usernameText = s.toString().trim();
                String errorMessage = validateUsername(usernameText);
                setError(errorMessage);
            }
        });
    }


    public void setMinLength(int minLength) {
        if (minLength <= 0) {
            throw new IllegalArgumentException("Minimum length must be greater than 0");
        }
        this.minLength = minLength;
    }

    private String validateUsername(String usernameText) {
        if (usernameText.isEmpty()) {
            return "Username is required";
        }

        if (usernameText.length() < minLength) {
            return String.format("Username must be at least %d characters long", minLength);
        }

        if (!usernameText.matches(Constants.USERNAME_REGEX)) {
            return "Username can only contain letters with numbers, dots, dashes and underscores";
        }

        return null; // No error
    }


}