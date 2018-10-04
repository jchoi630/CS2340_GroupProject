package com.gaggle.givr;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.text.TextUtils;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    String adminUser = "admin";
    String adminPass = "admin";

    LoginState state;

    // Element References
    EditText emailField;
    EditText passwordField;
    EditText resetEmailField;
    Button submitButton;
    TextView forgotPasswordText;
    FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    TextView welcomeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FirebaseApp.initializeApp(this);

        // Store elements as variables - buttons
        emailField = (EditText) findViewById(R.id.emailField);
        passwordField = (EditText) findViewById(R.id.passwordField);
        resetEmailField = (EditText) findViewById(R.id.resetEmailField);
        submitButton = (Button) findViewById(R.id.submitButton);
        forgotPasswordText = (TextView) findViewById(R.id.forgotPasswordText);
        welcomeText = (TextView) findViewById(R.id.welcomeText);

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

        //Initializing Firebase & Email/Password Creation
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                System.out.println("auth state changed I guess");
            }
        };
        mAuth = FirebaseAuth.getInstance();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        //Check if user is signed in (non-null) and update UI accordingly
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            welcomeText.setText("Welcome, " + currentUser.getDisplayName());
        } else {
            welcomeText.setText("Please log in");
        }
    }

    /**
     * Create user account
     *
     * @param email user email
     * @param password user password
     */
    private void createAccount(String email, String password) {
        if (!validateForm()) {
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        //Sign in success, update UI with signed-in user's info
                        FirebaseUser user = mAuth.getCurrentUser();
                    } else {
                        //If sign in fails, display message to user ??
                    }
                }
            });
    }

    private boolean validateForm() {
        boolean valid = true;

//        String email = emailField.getText().toString();
//        if (TextUtils.isEmpty(email)) {
//            emailField.setError("Required.");
//            valid = false;
//        } else {
//            emailField.setError(null);
//        }
//
//        String password = passwordField.getText().toString();
//        if (TextUtils.isEmpty(password)) {
//            passwordField.setError("Required.");
//            valid = false;
//        } else {
//            passwordField.setError(null);
//        }

        return valid;
    }

    /**
     * Sign in method
     *
     * @param email user email
     * @param password user password
     */

    private void signIn(String email, String password) {
        if (!validateForm()) {
            return;
        }

        // Start sign in w email
        mAuth.signInWithEmailAndPassword(email, password)
             .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI w user's info
                        FirebaseUser user = mAuth.getCurrentUser();
                        System.out.println("USER CREATEDS!!!");
                    } else {
                        System.out.println("USER WAS NOT CREATED :(");
                    }
                }
        });
    }

    /**
     * Sign out method
     * 
     */
    public void signOut() {
        FirebaseAuth.getInstance().signOut();
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
//        if (emailField.getText().toString().equals(adminUser) && passwordField.getText().toString().equals(adminPass))
//            navigateToDashboardActivity();

        switch (this.state) {
            case SIGNUP:
                System.out.println("Singup State");
                createAccount(emailField.getText().toString(), passwordField.getText().toString());
                break;
            case FORGOT_PASSWORD:
                System.out.println("forgetti State");
                return;
            default:
            case LOGIN:
                System.out.println("signin/def State");
                signIn(emailField.getText().toString(), passwordField.getText().toString());
                break;
        }
    }

    private void navigateToDashboardActivity() {
        //action you want, to start new activity, params are the things you go from (this paeg to next page)
        Intent dashboardActivityIntent = new Intent(LoginActivity.this, DashboardActivity.class);
        LoginActivity.this.startActivity(dashboardActivityIntent);
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
