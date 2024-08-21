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

public class EmailInputView extends TextInputLayout {

    private TextInputEditText email_ETXT_enterYourEmail;

    public EmailInputView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public EmailInputView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public EmailInputView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.email_input_view, this, true);

        findViews();
        initViews();
    }

    private void initViews() {
        email_ETXT_enterYourEmail.addTextChangedListener(new TextWatcher() {
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
                String emailText = s.toString().trim();
                String errorMessage = validateEmail(emailText);
                setError(errorMessage);
            }
        });
    }

    private void findViews() {
        email_ETXT_enterYourEmail = findViewById(R.id.email_ETXT_enterYourEmail);
    }

    private String validateEmail(String emailText) {
        if (emailText.isEmpty()) {
            return "Email is required";
        }

        // Check for a valid email format
        if (!emailText.matches(Constants.EMAIL_REGEX_2)) {
            return "Invalid email format";
        }

        return null; // No error
    }


}
