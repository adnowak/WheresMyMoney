package com.example.wheresmymoney;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.security.KeyStore;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
public class  StartActivity extends AppCompatActivity
{
    @Override
    public void onBackPressed()
    {
        finishAffinity();
        System.exit(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTheme(Global.getInstance().getRecentTheme());
        setContentView(R.layout.activity_start);
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        if(Global.getInstance().isThemeChanged())
        {
            Global.getInstance().setThemeChanged(false);
            recreate();
        }

        Global.getInstance().authenticated = false;
        Global.getInstance().databaseHelper.readActions();
        TextView balanceTextView = (TextView) findViewById(R.id.textView2);

        String balanceText="";

        balanceText = Global.getInstance().getMainCurrency().toNaturalLanguage(Global.getBalance());

        balanceTextView.setText(balanceText);
    }

    public void viewAccounts(View view)
    {
        /*
        if(Global.authActivity!=null)
        {
            ImageView fingerprintImage = Global.authActivity.findViewById(R.id.imageView);
            //img.setImageResource(R.mipmap.fingerprint_default);
        }
        final Intent intent = new Intent(this, AuthenticationActivity.class);
        */
        final Intent intent = new Intent(this, AccountsActivity.class);
        startActivity(intent);
    }

    public void viewCurrencies(View view)
    {
        final Intent intent = new Intent(this, CurrenciesActivity.class);
        startActivity(intent);
    }

    public void viewSettings(View view)
    {
        final Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}