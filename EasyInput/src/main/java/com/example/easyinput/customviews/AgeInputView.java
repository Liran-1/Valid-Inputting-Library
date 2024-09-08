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
import com.example.easyinput.validators.AgeValidation;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class AgeInputView extends TextInputLayout {

    private TextInputEditText age_ETXT_enterYourAge;

    private int minAgeRange = 0;
    private int maxAgeRange = 120;

    public AgeInputView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public AgeInputView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AgeInputView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.age_input_view, this, true);

        findViews();
        initViews();
    }

    private void findViews() {
        age_ETXT_enterYourAge = findViewById(R.id.age_ETXT_enterYourAge);
    }

    private void initViews() {
        updateHelperText();

        age_ETXT_enterYourAge.addTextChangedListener(new TextWatcher() {
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
                String ageText = s.toString().trim();
                String errorMessage = validateAge(ageText);
                setError(errorMessage);
            }
        });
    }

    public void setMinAgeRange(int minAgeRange) {
        validateAgeRange(minAgeRange, "Minimum age range");
        if (minAgeRange > maxAgeRange) {
            throw new IllegalArgumentException("Minimum age range cannot be greater than maximum age range");
        }
        this.minAgeRange = minAgeRange;
        updateHelperText();
    }

    public void setMaxAgeRange(int maxAgeRange) {
        validateAgeRange(maxAgeRange, "Maximum age range");
        if (maxAgeRange < minAgeRange) {
            throw new IllegalArgumentException(
                    "Maximum age range cannot be less than minimum age range");
        }
        this.maxAgeRange = maxAgeRange;
    }

    private void validateAgeRange(int ageInput, String inputTypeStr) {
        if (ageInput < Constants.AGE_MINIMUM_AGE || ageInput > Constants.AGE_MAXIMUM_AGE) {
            throw new IllegalArgumentException(String.format("%s must be between %d and %d",
                    inputTypeStr, Constants.AGE_MINIMUM_AGE, Constants.AGE_MAXIMUM_AGE));
        }
    }

    private String validateAge(String ageText) {
        setMaxAgeRange(70);
        setMinAgeRange(30);
        return AgeValidation.validate(ageText, minAgeRange, maxAgeRange);
    }

    private void updateHelperText() {
        setHelperText("Age must be between " + minAgeRange + " and " + maxAgeRange + ".");
    }
}