package com.example.wheresmymoney;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ExpenseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(Global.getInstance().getRecentTheme());
        setContentView(R.layout.activity_expense);
    }

    public void submitNewExpense(View view)
    {
        EditText textBox = (EditText) findViewById(R.id.newExpenseText);

        try
        {
            BigInteger result = Global.getInstance().getRecentAccount().getBalance().subtract(new BigDecimal(textBox.getText().toString()).multiply(new BigDecimal(Global.getInstance().getRecentAccount().getCurrency().getDivision())).toBigInteger());

            if(result.signum()<=0 || new BigDecimal(textBox.getText().toString()).signum()<=0)
            {
                throw new Exception();
            }
            Global.getInstance().getRecentAccount().setBalance(result);
            BigInteger amount = new BigDecimal(textBox.getText().toString()).multiply(new BigDecimal(Global.getInstance().getRecentAccount().getCurrency().getDivision())).toBigInteger();
            Global.getInstance().getRecentAccount().addTransaction(new Action(amount,1));
            Global.getInstance().databaseHelper.updateState();
            Global.getInstance().displayMessage("Expense added!");
            onBackPressed();
        }
        catch (Exception e)
        {
            Global.getInstance().displayMessage("Please enter a valid amount");
        }

    }
}
