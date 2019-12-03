package com.example.wheresmymoney;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;

public class EditBalanceActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTheme(Global.getInstance().getRecentTheme());
        setContentView(R.layout.activity_edit_balance);

        TextView textView = findViewById(R.id.textView13);

        textView.setText(Global.getInstance().getRecentAccount().getCurrency().toNaturalLanguage(Global.getInstance().getRecentAccount().getBalance()));
    }

    public void submitNewBalance(View view)
    {
        EditText textBox = (EditText) findViewById(R.id.editBalance);

        try
        {
            BigDecimal result = new BigDecimal(textBox.getText().toString()).multiply(new BigDecimal(Global.getInstance().getRecentAccount().getCurrency().getDivision()));

            if(result.signum()<0)
            {
                throw new Exception();
            }
            Global.getInstance().getRecentAccount().setBalance(result.toBigInteger());
            Global.getInstance().getRecentAccount().addTransaction(new Action(result.toBigInteger(),2));
            Global.getInstance().databaseHelper.updateState();
            Global.getInstance().displayMessage("Balance edited!");
            onBackPressed();
        }
        catch (Exception e)
        {
            Global.getInstance().displayMessage("Please enter a valid amount");
        }

    }
}
