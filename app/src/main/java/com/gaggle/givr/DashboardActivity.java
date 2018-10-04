package com.gaggle.givr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class DashboardActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }

    public void navigateBackToLandingPage(View v) {
        //action you want, to start new activity, params are the things you go from (this page to next page)
        Intent backToLandingPage = new Intent(DashboardActivity.this, LandingPage.class);
        DashboardActivity.this.startActivity(backToLandingPage);
    }
}
