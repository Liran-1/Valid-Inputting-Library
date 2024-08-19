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

public class PhoneInputView extends TextInputLayout {

    private TextInputEditText phone_ETXT_enterYourPhone;

    public PhoneInputView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public PhoneInputView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PhoneInputView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.phone_input_view, this, true);

        findViews();
        initViews();
    }

    private void initViews() {
        this.phone_ETXT_enterYourPhone.addTextChangedListener(new TextWatcher() {
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
                String formattedPhoneNumber = formatPhoneNumber(s.toString());
                if (!formattedPhoneNumber.equals(s.toString())) {
                    phone_ETXT_enterYourPhone.setText(formattedPhoneNumber);
                    phone_ETXT_enterYourPhone.setSelection(formattedPhoneNumber.length());   // Move cursor to the end of the text
                }
            }
        });
    }

    private void findViews() {
        phone_ETXT_enterYourPhone = findViewById(R.id.phone_ETXT_enterYourPhone);
    }

    private String formatPhoneNumber(String phoneNumber) {
        StringBuilder formattedPhoneNumber = new StringBuilder(phoneNumber);
        if (phoneNumber.length() > 3 && phoneNumber.charAt(3) != '-') {
            formattedPhoneNumber.insert(3, '-');
        } else if (phoneNumber.length() == 4 && phoneNumber.charAt(3) == '-') {
            formattedPhoneNumber.delete(3, 4);
        }
        return formattedPhoneNumber.toString();
    }

}