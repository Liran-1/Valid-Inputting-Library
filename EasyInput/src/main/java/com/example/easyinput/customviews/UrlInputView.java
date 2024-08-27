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

import java.util.regex.Pattern;

public class UrlInputView extends TextInputLayout {


    private TextInputEditText url_ETXT_enterYourUrl;
    public boolean mustContainProtocol = true;
    public boolean mustContainWWWPrefix = true;

    public UrlInputView setMustContainProtocol(boolean mustContainProtocol) {
        this.mustContainProtocol = mustContainProtocol;
        return this;
    }

    public UrlInputView setMustContainWWWPrefix(boolean mustContainWWWPrefix) {
        this.mustContainWWWPrefix = mustContainWWWPrefix;
        return this;
    }

    public UrlInputView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public UrlInputView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public UrlInputView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.url_input_view, this, true);

        findViews();

        initViews();
    }

    private void findViews() {
        url_ETXT_enterYourUrl = findViewById(R.id.url_ETXT_enterYourUrl);
    }

    private void initViews() {
        url_ETXT_enterYourUrl.addTextChangedListener(new TextWatcher() {
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
                String url = s.toString().trim();
                String errorMessage = validateUrl(url);
                setError(errorMessage);


//                if (url.isEmpty()) {
//                    url_TIL_enterYourUrl.setError(null);
//                    return;
//                }
//
//                if (mustContainProtocol && !hasValidProtocol(url)) {
//                    url_TIL_enterYourUrl.setError("URL must start with http://, https://, or ftp://");
//                } else if (mustContainWWWPrefix && !hasValidWWWPrefix(url)) {
//                    url_TIL_enterYourUrl.setError("URL must start with www.");
//                } else if (!hasValidDomain(url)) {
//                    url_TIL_enterYourUrl.setError("Invalid domain. Must be a valid domain name.");
//                } else if (!isValidPath(url)) {
//                    url_TIL_enterYourUrl.setError("Invalid URL path.");
//                } else {
//                    url_TIL_enterYourUrl.setError(null); // Clear any previous errors
//                }
            }
        });
    }

    private String validateUrl(String url) {
        setMustContainProtocol(false);
        if (url.isEmpty()) {
            return null;
        }
        if (mustContainProtocol && !hasValidProtocol(url)) {
            return "URL must start with http://, https://, or ftp://";
        }
        if (mustContainWWWPrefix && !hasValidWWWPrefix(url)) {
            return "URL must start with www.";
        }
        if (!hasValidDomain(url)) {
            return "Invalid domain. Must be a valid domain name.";
        }
//        if (!isValidPath(url)) {
//            return "Invalid URL path.";
//        }
        return null; // No error
    }

    private boolean hasValidProtocol(String url) {
        return url.matches("^(https?|ftp):\\/\\/.*");
    }

    private boolean hasValidWWWPrefix(String url) {
        String protocolWithWWWPrefix = Constants.URL_PROTOCOL_REGEX + "?" + Constants.URL_PREFIX_REGEX + ".*";
        return url.matches(protocolWithWWWPrefix);
//        return url.matches("^(https?|ftp):\\/\\/www\\..*") || url.matches("^(www\\..*)");
    }

    private boolean hasValidDomain(String url) {
        Pattern protocolWithWWWPrefixWithDomain = Pattern.compile(
                Constants.URL_PROTOCOL_REGEX + "?" + Constants.URL_PREFIX_REGEX + "?" +
                        Constants.URL_DOMAIN_REGEX +
                        "(" + Constants.URL_TLD_ONE_PART_REGEX + "|" + Constants.URL_TLD_TWO_PART_REGEX + ")");

        return url.matches(String.valueOf(protocolWithWWWPrefixWithDomain));
//        return url.matches("^(https?|ftp):\\/\\/(?:www\\.)?[a-zA-Z0-9-]+\\.[a-zA-Z]{2,}$") ||
//                url.matches("^(?:www\\.)?[a-zA-Z0-9-]+\\.[a-zA-Z]{2,}$");
    }

//    private boolean isValidPath(String url) {
//        // Match optional protocol and www. prefix
//        return url.matches("^(https?|ftp):\\/\\/(?:www\\.)?[a-zA-Z0-9-]+\\.[a-zA-Z]{2,}(?:\\/[^\\s]*)?$") ||
//                url.matches("^(?:www\\.)?[a-zA-Z0-9-]+\\.[a-zA-Z]{2,}(?:\\/[^\\s]*)?$") ||
//                url.matches("^[a-zA-Z0-9-]+\\.[a-zA-Z]{2,}(?:\\/[^\\s]*)?$");
//    }

}