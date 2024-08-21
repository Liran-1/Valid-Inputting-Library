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

public class AgeInputView extends TextInputLayout {

    private TextInputEditText age_ETXT_enterYourAge;


    private int minAge = 0;
    private int maxAge = 120;
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

    public void setMinAge(int minAge) {
        validateAgeRange(minAge, "Minimum age");
        if (minAge > maxAge) {
            throw new IllegalArgumentException("Minimum age cannot be greater than maximum age");
        }
        this.minAge = minAge;
    }

    public void setMaxAge(int maxAge) {
        validateAgeRange(maxAge, "Maximum age");
        if (maxAge < minAge) {
            throw new IllegalArgumentException("Maximum age cannot be less than minimum age");
        }
        this.maxAge = maxAge;
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
        if (ageInput < Constants.MINIMUM_AGE || ageInput > Constants.MAXIMUM_AGE) {
            throw new IllegalArgumentException(String.format("%s must be between %d and %d",
                    inputTypeStr, Constants.MINIMUM_AGE, Constants.MAXIMUM_AGE));
        }
    }

    private String validateAge(String ageText) {

        int age;
        try {
            age = Integer.parseInt(ageText);
        } catch (NumberFormatException e) {
            return "Invalid age format";
        }

        if (age < minAge || age > maxAge) {
            return String.format("Age must be between %d and %d", minAge, maxAge);
        }

        if (age < minAgeRange || age > maxAgeRange) {
            return String.format("Age must be within the range %d to %d", minAgeRange, maxAgeRange);
        }

        return null; // No error
    }

    private void updateHelperText() {
        setHelperText("Age must be between " + minAge + " and " + maxAge + ".");
    }
}