# Custom Validation Library for Android

This library provides a set of custom validation views for Android using Java. It includes views for validating age, email, date, password (with confirmation), phone number, URL, and username. Each view comes with real-time validation and customizable settings. The `TextInputEditText` fields work with `TextWatcher` and automatically validate as the user types, eliminating the need for manual validation triggers.

## Features

- **AgeInputView**: Validates age within a specified range.
- **EmailInputView**: Validates email format.
- **DateInputView**: Provides a date picker dialog with range validation.
- **PasswordInputView**: Validates passwords with real-time confirmation and configurable complexity requirements.
- **PhoneInputView**: Validates phone numbers with country prefixes and a dropdown for countries.
- **URLInputView**: Validates URL format with optional protocol and www prefix checks.
- **UsernameInputView**: Validates usernames to ensure they contain only valid characters.

## Setup

1. **Add the library to your project**

   To use the custom validation views, copy the `customviews` package into your project or include the Java files in your project's source code.

2. **Include dependencies**

   Ensure you have the following dependencies in your `build.gradle` file:

```kotlin
implementation(libs.material)
```

## Usage

### 1. AgeValidationView

**XML Layout:**

```xml
<com.example.easyinput.customviews.AgeInputView
    android:id="@+id/age_view"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"/>
```

**Java/Kotlin:**

```java
AgeInputView ageView = findViewById(R.id.age_view);
ageView.setAgeLimits(18, 65);
```

### 2. EmailValidationView

**XML Layout:**

```xml
<com.example.easyinput.customviews.EmailInputView
    android:id="@+id/email_view"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"/>
```

**Java/Kotlin:**

```java
EmailInputView emailView = findViewById(R.id.email_view);
```

### 3. DateValidationView

**XML Layout:**

```xml
<com.example.easyinput.customviews.DateInputView
    android:id="@+id/date_view"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"/>
```

**Java/Kotlin:**

```java
DateInputView dateView = findViewById(R.id.date_view);
Calendar startDate = Calendar.getInstance();
Calendar endDate = Calendar.getInstance();
endDate.add(Calendar.YEAR, 1); \# For example, one year from now
dateView.setMinDate(startDate);
dateView.setMaxDate(endDate);
```

### 4. PasswordValidationView

**XML Layout:**

```xml
<com.example.easyinput.customviews.PasswordInputView
    android:id="@+id/password_view"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"/>
```

**Java/Kotlin:**

```java
PasswordInputView passwordView = findViewById(R.id.password_view);
passwordView.setPasswordRequirements(true, true, true, true);
```

### 5. PhoneValidationView

**XML Layout:**

```xml
<com.example.easyinput.customviews.PhoneInputView
    android:id="@+id/phone_view"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"/>
```

**Java/Kotlin:**

```java
PhoneInputView phoneView = findViewById(R.id.phone_view);
```

### 6. URLValidationView

**XML Layout:**

```xml
<com.example.easyinput.customviews.UrlInputView
    android:id="@+id/url_view"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"/>
```

**Java/Kotlin:**

```java
UrlInputView urlView = findViewById(R.id.url_view);
urlView.setURLRequirements(true, true);
```

### 7. UsernameValidationView

**XML Layout:**

```xml
<com.example.easyinput.customviews.UsernameInputView
    android:id="@+id/username_view"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"/>
```

**Java/Kotlin:**

```java
UsernameInputView usernameView = findViewById(R.id.username_view);
```

## Validation

The validation occures automatically as you type.

## Customization

- **AgeValidationView**: `setMinAgeRange(int minAgeRange)`, `setMaxAgeRange(int maxAgeRange)`
- **PasswordValidationView**: `PasswordInputView setPasswordRequirements(boolean requireUpperCaseLetters, boolean requireLowerCaseLetters, boolean requireDigits, boolean requireSpecialCharacters)` or for specific requirements `setRequireUpperCaseLetters(boolean requireUpperCaseLetters)`, `setRequireLowerCaseLetters(boolean requireLowerCaseLetters)`, `setRequireDigits(boolean requireDigits)`, `setRequireSpecialCharacters(boolean requireSpecialCharacters)`
- **URLValidationView**: `setURLRequirements(boolean requireProtocol, boolean requireWWW)`

## License

This library is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.

## Contributing

Feel free to submit issues or pull requests. Your contributions are welcome!

## Contact

For any questions or feedback, please contact [LiranProgramming@gmail.com](mailto:LiranProgramming@gmail.com).
