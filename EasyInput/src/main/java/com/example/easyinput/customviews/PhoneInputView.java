package com.example.easyinput.customviews;

import android.content.Context;
import android.content.res.Resources;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.easyinput.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;

public class PhoneInputView extends LinearLayout {

    private TextInputEditText phone_ETXT_enterYourPhone;
    private Spinner phone_SPN_countrySpinner;
    private Map<String, String> countryPrefixes;
    private String currentPrefix = "";
    private String previousPrefix = "";

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
        initViews(context);
    }

    private void findViews() {
        phone_SPN_countrySpinner = findViewById(R.id.phone_SPN_countrySpinner);
        phone_ETXT_enterYourPhone = findViewById(R.id.phone_ETXT_enterYourPhone);
    }


    private void initViews(Context context) {
        initSpinnerWithCountryCodes(context);

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

        phone_SPN_countrySpinner.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, android.view.View view, int position, long id) {
                String selectedPrefix = getCountryPrefix(position);
                if (!selectedPrefix.equals(currentPrefix)) {
                    updatePhoneNumberPrefix(selectedPrefix);
                    previousPrefix = currentPrefix;
                    currentPrefix = selectedPrefix;
                }
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {
                // No action needed
            }
        });
    }

    private void initSpinnerWithCountryCodes(Context context) {
        Resources resources = context.getResources();
        String[] countryNames = resources.getStringArray(R.array.country_names);
        String[] shortCountryNames = resources.getStringArray(R.array.short_country_names);
        String[] countryPrefixesArray = resources.getStringArray(R.array.country_prefixes);

        if (countryNames.length != countryPrefixesArray.length || shortCountryNames.length != countryPrefixesArray.length) {
            throw new IllegalStateException("Country names and prefixes arrays must have the same length.");
        }

        countryPrefixes = new HashMap<>();
        for (int i = 0; i < countryNames.length; i++) {
            countryPrefixes.put(shortCountryNames[i], countryPrefixesArray[i]);
        }

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context,
                R.array.short_country_names, android.R.layout.simple_spinner_item);
//                new ArrayAdapter<>(
//                context,
//                android.R.layout.simple_spinner_item,
//                countryNames
//        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        phone_SPN_countrySpinner.setAdapter(adapter);
    }

//    private String getPhoneNumber() {
//        String phone = phone_ETXT_enterYourPhone.getText().toString();
//        String country = (String) countrySpinner.getSelectedItem();
//        String prefix = CountryPrefixes.COUNTRY_PREFIXES.get(country);
//        String fullPhone = prefix + phone;
//    }

    private String getCountryPrefix(int position) {
        String[] countryPrefixesArray = getResources().getStringArray(R.array.country_prefixes);
        return countryPrefixesArray[position];
    }

    private void updatePhoneNumberPrefix(String prefix) {
        String phoneNumber = phone_ETXT_enterYourPhone.getText().toString();
        // Remove the old prefix if it exists
        if (phoneNumber.startsWith(currentPrefix)) {
            phoneNumber = phoneNumber.substring(previousPrefix.length());
            formatPhoneNumber(phoneNumber);
        }

        phone_ETXT_enterYourPhone.setText(prefix + phoneNumber);
        phone_ETXT_enterYourPhone.setSelection(phone_ETXT_enterYourPhone.getText().length()); // Move cursor to the end of the text
    }

    private String formatPhoneNumber(String phoneNumber) {
        StringBuilder formattedPhoneNumber = new StringBuilder(phoneNumber);
        if ((phoneNumber.length() - currentPrefix.length()) > 3 &&
                (phoneNumber.charAt(3 + currentPrefix.length())) != '-') {
            formattedPhoneNumber.insert(3 + + currentPrefix.length(), '-');
        } else if ((phoneNumber.length() - currentPrefix.length()) == 4 && phoneNumber.charAt(3 + + currentPrefix.length()) == '-') {
            formattedPhoneNumber.delete(3 + + currentPrefix.length(), 4 + + currentPrefix.length());
        }
        return formattedPhoneNumber.toString();
    }

}