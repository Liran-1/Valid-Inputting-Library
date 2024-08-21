package com.example.easyinput.customviews;

import android.app.DatePickerDialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.easyinput.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DateInputView extends TextInputLayout {

    private TextInputEditText date_ETXT_enterDate;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
    //    private String dateFormat = "dd/MM/yyyy"; // Default date format
    private Calendar minDate;
    private Calendar maxDate;

    public DateInputView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public DateInputView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public DateInputView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }


    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.date_input_view, this, true);
        findViews();
        initViews(context);
    }


    private void findViews() {
        date_ETXT_enterDate = findViewById(R.id.date_ETXT_enterDate);
    }

    private void initViews(Context context) {
        date_ETXT_enterDate.setOnClickListener(v -> {
            hideKeyboard(context, v);
            showDatePickerDialog();
        });

        date_ETXT_enterDate.addTextChangedListener(new TextWatcher() {
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
                String dateText = s.toString().trim();
                String errorMessage = validateDate(dateText);
                setError(errorMessage);
            }
        });
    }

    // Method to hide the keyboard
    public void hideKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

//    private void showDatePickerDialog(Context context) {
//        Calendar calendar = Calendar.getInstance();
//        int year = calendar.get(Calendar.YEAR);
//        int month = calendar.get(Calendar.MONTH);
//        int day = calendar.get(Calendar.DAY_OF_MONTH);
//
//        new DatePickerDialog(context, (view, year1, month1, dayOfMonth) -> {
//            Calendar selectedDate = Calendar.getInstance();
//            selectedDate.set(year1, month1, dayOfMonth);
//            date_ETXT_enterDate.setText(dateFormat.format(selectedDate.getTime()));
//        }, year, month, day).show();
//    }

    public boolean isDateValid() {
        String dateText = date_ETXT_enterDate.getText().toString();
        try {
            dateFormat.parse(dateText);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }


    private String validateDate(String dateText) {
        if (dateText.isEmpty()) {
            return "Date is required";
        }

//        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(dateText);
        } catch (ParseException e) {
            return "Invalid date format";
        }

        Calendar inputDate = Calendar.getInstance();
        try {
            inputDate.setTime(dateFormat.parse(dateText));
        } catch (ParseException e) {
            return "Invalid date";
        }

        if (minDate != null && inputDate.before(minDate)) {
            return "Date must be after " + formatDate(minDate);
        }

        if (maxDate != null && inputDate.after(maxDate)) {
            return "Date must be before " + formatDate(maxDate);
        }

        return null; // No error
    }

    private String formatDate(Calendar date) {
        SimpleDateFormat sdf = new SimpleDateFormat(String.valueOf(dateFormat), Locale.getDefault());
        return sdf.format(date.getTime());
    }

    public void setMinDate(Calendar minDate) {
        this.minDate = minDate;
    }

    public void setMaxDate(Calendar maxDate) {
        this.maxDate = maxDate;
    }

    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getContext(),
                (view, year1, month1, dayOfMonth) -> {
                    String date = String.format("%02d-%02d-%04d", dayOfMonth, month1 + 1, year1);
                    if (date_ETXT_enterDate != null) {
                        date_ETXT_enterDate.setText(date);
                    }
                },
                year, month, day
        );

        if (minDate != null) {
            datePickerDialog.getDatePicker().setMinDate(minDate.getTimeInMillis());
        }

        if (maxDate != null) {
            datePickerDialog.getDatePicker().setMaxDate(maxDate.getTimeInMillis());
        }

        datePickerDialog.show();
    }

}