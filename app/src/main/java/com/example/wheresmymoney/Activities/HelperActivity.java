package com.example.wheresmymoney.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.wheresmymoney.R;
import com.example.wheresmymoney.Singleton.Global;

public class HelperActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(Global.getInstance().getRecentTheme());
        setContentView(R.layout.activity_helper);

        onBackPressed();
    }
}
