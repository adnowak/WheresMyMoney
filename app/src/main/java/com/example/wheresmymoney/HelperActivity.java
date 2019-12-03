package com.example.wheresmymoney;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class HelperActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(Global.getInstance().getRecentTheme());
        setContentView(R.layout.activity_helper);

        onBackPressed();
    }
}
