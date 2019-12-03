package com.example.wheresmymoney;

import android.app.Activity;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;

public class Action extends Activity
{
    private int IdAc;
    private int type;
    private BigInteger amount;
    private String date;
    private int IdA;

    public Action()
    {

    }

    public Action(BigInteger amount, int type)
    {
        this.amount = amount;
        this.date = (new Date()).toString();
        this.type = type;
    }

    public Action(BigInteger amount, int type, String date)
    {
        this.amount = amount;
        this.date = date;
        this.type = type;
    }

    public String toString()
    {
        String typeText="";

        switch(type)
        {
            case 0:
                typeText = "Income :";
                break;
            case 1:
                typeText = "Expense :-";
                break;
            default:
                typeText = "Set to :";
                break;
        }

        int index = 0;

        for(Account account : Global.getInstance().getAccountsList())
        {
            if(account.getIdA()==this.getIdA())
            {
                break;
            }
            index++;
        }

        return typeText+Global.getInstance().getAccountsList().get(index).getCurrency().toNaturalLanguage(amount);
    }

    public String toSavingString()
    {
        return type+"_"+amount+"_"+date;
    }


    public BigInteger getAmount()
    {
        return amount;
    }

    public void setAmount(BigInteger newAmount)
    {
        this.amount = newAmount;
    }

    public String getDateString()
    {
        String[] splitData = date.split(" ");

        String date;
        String day;

        switch(splitData[0])
        {
            case "Mon":
                day = "Monday";
                break;
            case "Tue":
                day = "Tuesday";
                break;
            case "Wed":
                day = "Wednesday";
                break;
            case "Thu":
                day = "Thursday";
                break;
            case "Fri":
                day = "Friday";
                break;
            case "Sat":
                day = "Saturday";
                break;
            case "Sun":
                day = "Sunday";
                break;
            default:
                day = splitData[0];
                break;
        }

        date = day+", "+splitData[1]+" "+splitData[2]+" "+splitData[5]+" "+splitData[3];

        return date;
    }

    public void setAccount(Account newAccount)
    {
        this.IdA = newAccount.getIdA();
    }

    public int getIdA()
    {
        return this.IdA;
    }

    public int getType()
    {
        return type;
    }

    public void setType(int newType)
    {
        this.type = newType;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getDate()
    {
        return date;
    }

    public int getIdAc()
    {
        return IdAc;
    }

    public void setIdAc(int idAc)
    {
        IdAc = idAc;
    }
}
