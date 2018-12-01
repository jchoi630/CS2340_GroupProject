package com.gaggle.givr;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Spinner;

import java.util.Map;

/**
 * what happens when you login!
 */
public class LoginActivity extends AppCompatActivity {
    TinyDB tinydb;
    User admin;
    LoginState state;
    NotificationManager notifManager;

    // Element References
    EditText emailField;
    EditText passwordField;
    EditText resetEmailField;
    Button submitButton;
    TextView forgotPasswordText;
    Spinner userTypeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tinydb = new TinyDB(this);
        notifManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        // Store elements as variables
        emailField = (EditText) findViewById(R.id.emailField);
        passwordField = (EditText) findViewById(R.id.passwordField);
        resetEmailField = (EditText) findViewById(R.id.resetEmailField);
        submitButton = (Button) findViewById(R.id.submitButton);
        forgotPasswordText = (TextView) findViewById(R.id.forgotPasswordText);
        userTypeSpinner = (Spinner) findViewById(R.id.userTypeSpinner);

        // Retrieve and set state
        state = (LoginState) getIntent().getSerializableExtra("LoginState");
        setLoginState(state);

        // Pull saved Users
        Map<String, User> storedMap = tinydb.getHashMapUser("users");
        if (storedMap == null) {
            // Store default user
            admin = new User("admin", "admin", "User");
            User.userMap.put("admin", admin);
        } else {
            User.userMap = storedMap;
        }

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

    /**
     * What will happen if you forget your password
     * @param v the page to view
     */
    public void forgotPassword(View v) {
        setLoginState(LoginState.FORGOT_PASSWORD);
    }

    /**
     * What happens when you click submit!
     * @param v the next thing to go to
     */
    public void submit(View v) {
        User tempUser;
        if (state == LoginState.SIGNUP) {
            tempUser = new User(emailField.getText().toString(),
                    passwordField.getText().toString(),
                    userTypeSpinner.getSelectedItem().toString());

            User.userMap.put(emailField.getText().toString(), tempUser);
            tinydb.putHashMapUser("users", User.userMap);
            navigateToLocationListPage();
            notifySignUp();
        }
        if (state == LoginState.LOGIN) {
            tempUser = User.userMap.get(emailField.getText().toString());
            if (tempUser == null) {
                System.out.println("User does not exist");
                return;
            }
            if (tempUser.password.equals(passwordField.getText().toString())) {
                navigateToLocationListPage();
            }
        }
    }

    private void notifySignUp() {
        String CHANNEL_ID = "my_channel_01";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            CharSequence name = "my_channel";
            String Description = "This is my channel";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            mChannel.setDescription(Description);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.GREEN);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            notifManager.createNotificationChannel(mChannel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Givr")
                .setContentText("Congratulations! You successfully signed up!");

        notifManager.notify(0, builder.build());
    }

    private void navigateToLocationListPage() {
        Intent locationListPageIntent = new Intent(LoginActivity.this, LocationListPage.class);
        LoginActivity.this.startActivity(locationListPageIntent);
    }

    /**
     * The enum for login state
     */
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
            case LOGIN:
                return R.string.login_button;
            default:
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
        if (state != LoginState.SIGNUP) {
            userTypeSpinner.setVisibility(View.GONE);
        }
    }

    private void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
