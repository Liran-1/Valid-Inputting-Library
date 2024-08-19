package com.example.easyinput.customviews;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.easyinput.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class UsernameInputView extends TextInputLayout {


    private TextInputEditText user_ETXT_enterYourUsername;

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

            }
        });
    }

    private void findViews() {
        user_ETXT_enterYourUsername = findViewById(R.id.username_ETXT_enterUsername);
    }


}