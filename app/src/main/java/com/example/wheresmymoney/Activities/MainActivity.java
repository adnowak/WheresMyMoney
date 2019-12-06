package com.example.wheresmymoney.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.wheresmymoney.R;
import com.example.wheresmymoney.Singleton.Global;

public class  MainActivity extends AppCompatActivity
{
    boolean initiated = false;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        if(!initiated)
        {
            Global.initiateGlobal(this);
            initiated = true;
        }

        super.onCreate(savedInstanceState);
        setTheme(Global.getInstance().getRecentTheme());
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        final Intent intent = new Intent(this, StartActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }
}