package com.gaggle.givr;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    LoginState state;

    // Element References
    EditText emailField;
    EditText passwordField;
    EditText resetEmailField;
    Button submitButton;
    TextView forgotPasswordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Store elements as variables
        emailField = (EditText) findViewById(R.id.emailField);
        passwordField = (EditText) findViewById(R.id.passwordField);
        resetEmailField = (EditText) findViewById(R.id.resetEmailField);
        submitButton = (Button) findViewById(R.id.submitButton);
        forgotPasswordText = (TextView) findViewById(R.id.forgotPasswordText);

        // Retrieve and set state
        state = (LoginState) getIntent().getSerializableExtra("LoginState");
        setLoginState(state);

        // Set inputs to hide keyboard when use clicks away
        View.OnFocusChangeListener hideKeyboardListener = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        };
        emailField.setOnFocusChangeListener(hideKeyboardListener);
        passwordField.setOnFocusChangeListener(hideKeyboardListener);
        resetEmailField.setOnFocusChangeListener(hideKeyboardListener);

    }

    @Override
    public void onBackPressed() {
        if (state == LoginState.FORGOT_PASSWORD) {
            setLoginState(LoginState.LOGIN);
            return;
        }

        super.onBackPressed();
        overridePendingTransition(0, 0);
    }

    public void forgotPassword(View v) {
        setLoginState(LoginState.FORGOT_PASSWORD);
    }

    public void submit(View v) {
        String displayString = "Email Field: " + emailField.getText().toString()
                + ", Password Field: " + passwordField.getText().toString()
                + ", Reset Email Field: " + resetEmailField.getText().toString();

        System.out.println(displayString);
    }

    public enum LoginState {
        LOGIN,
        SIGNUP,
        FORGOT_PASSWORD
    }

    /// Helper methods ///
    private int getSubmitButtonText(LoginState state) {
        switch (state) {
            case SIGNUP:
                return R.string.signup_button;
            case FORGOT_PASSWORD:
                return R.string.reset_button;
            default:
            case LOGIN:
                return R.string.login_button;
        }
    }

    private void setLoginState(LoginState state) {
        this.state = state;
        submitButton.setText(getSubmitButtonText(state));

        if (state == LoginState.LOGIN) {
            forgotPasswordText.setVisibility(View.VISIBLE);
        } else {
            forgotPasswordText.setVisibility(View.GONE);
        }

        if (state == LoginState.FORGOT_PASSWORD) {
            resetEmailField.setVisibility(View.VISIBLE);

            emailField.setVisibility(View.GONE);
            passwordField.setVisibility(View.GONE);
        } else {
            emailField.setVisibility(View.VISIBLE);
            passwordField.setVisibility(View.VISIBLE);

            resetEmailField.setVisibility(View.GONE);
        }
    }

    private void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
