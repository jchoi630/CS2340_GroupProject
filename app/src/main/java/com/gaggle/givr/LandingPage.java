package com.gaggle.givr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * the original landing page for the app
 */
public class LandingPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
    }

    /**
     * how to signup
     * @param v the page to view
     */
    public void signUp(View v) {
        navigateToLoginActivity(LoginActivity.LoginState.SIGNUP);
    }

    /**
     * the login page
     * @param v the page to view
     */
    public void logIn(View v) {
        navigateToLoginActivity(LoginActivity.LoginState.LOGIN);
    }

    private void navigateToLoginActivity(LoginActivity.LoginState state) {
        Intent loginActivityIntent = new Intent(LandingPage.this, LoginActivity.class);
        loginActivityIntent.putExtra("LoginState", state);
        LandingPage.this.startActivity(loginActivityIntent);
        overridePendingTransition(R.transition.fade_out, R.transition.fade_in);
    }
}
