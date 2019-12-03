package com.example.wheresmymoney;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.wheresmymoney.Currency;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;

public class Account
{
    private int IdA;
    private String name;
    private BigInteger balance;
    private ArrayList<Action> actionHistory;
    private com.example.wheresmymoney.Currency currency;

    public Account()
    {
        this.actionHistory = new ArrayList<Action>();
    }

    public Account(String name, Currency currency)
    {
        this.name = name;
        this.balance = new BigInteger("0");
        this.actionHistory = new ArrayList<Action>();
        this.currency = currency;
    }

    public void addTransaction(Action action)
    {
        action.setAccount(this);
        Global.getInstance().databaseHelper.addAction(action);
    }

    public void removeTransaction(Action action)
    {
        Global.getInstance().databaseHelper.deleteSingleAction(action);
    }

    public String getName()
    {
        return this.name;
    }

    public BigInteger getBalance()
    {
        return this.balance;
    }

    public ArrayList<Action> getActionHistory()
    {
        return this.actionHistory;
    }

    public Currency getCurrency()
    {
        return currency;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setBalance(BigInteger balance)
    {
        this.balance = balance;
    }

    public void refreshAccount()
    {

    }

    public void setCurrency(Currency currency)
    {
        this.currency = currency;
    }

    public int getIdA() {
        return IdA;
    }

    public void setIdA(int idA) {
        IdA = idA;
    }
}
