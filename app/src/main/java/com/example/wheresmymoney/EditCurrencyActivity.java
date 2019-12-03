package com.example.wheresmymoney;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.ArrayList;

public class EditCurrencyActivity extends AppCompatActivity
{
    private Currency newLink;
    ArrayList<String> currenciesListData;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTheme(Global.getInstance().getRecentTheme());
        setContentView(R.layout.activity_edit_currency);
        EditText nameEdit = findViewById(R.id.editName);
        EditText tagEdit = findViewById(R.id.editTag);
        EditText ratioEdit = findViewById(R.id.editToDollarRatio);
        EditText pointEdit = findViewById(R.id.editPointPosition);
        TextView newLinkRatioTextView = findViewById(R.id.textView20);

        nameEdit.setText(Global.getInstance().getRecentCurrency().getName());
        tagEdit.setText(Global.getInstance().getRecentCurrency().getTag());
        ratioEdit.setText(Global.getInstance().getRecentCurrency().getLinkRatio().toString());
        pointEdit.setText(((Integer)Global.getInstance().getRecentCurrency().getPointPosition()).toString());
        newLinkRatioTextView.setText("Type new "+Global.getInstance().getRecentCurrency().getLink().getName().toLowerCase()+" ratio");
    }

    protected void onResume()
    {
        super.onResume();
        newLink = Global.getInstance().getRecentCurrency().getLink();
        ListView list;
        currenciesListData = new ArrayList<String>();
        for (Currency currency : Global.getInstance().getCurrenciesList()) {
            if (!currency.isLinkedTo(Global.getInstance().getRecentCurrency())) {
                currenciesListData.add(currency.getTag());
            }
        }
        list = findViewById(R.id.currenciesListView);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, currenciesListData);
        list.setAdapter(adapter3);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                for(Currency currency : Global.getInstance().getCurrenciesList())
                {
                    if(currenciesListData.get(position).equals(currency.getTag()))
                    {
                        newLink = currency;
                    }
                }
                Global.getInstance().displayMessage(newLink.getTag());
            }
        });
    }

    public void editCurrency(View view)
    {
        Global.getInstance().displayMessage("Editing a currency!");
        EditText nameEdit = findViewById(R.id.editName);
        EditText tagEdit = findViewById(R.id.editTag);
        EditText ratioEdit = findViewById(R.id.editToDollarRatio);
        EditText pointEdit = findViewById(R.id.editPointPosition);

        String name = nameEdit.getText().toString();
        String tag = tagEdit.getText().toString();
        BigDecimal linkRatio = new BigDecimal("0");
        int pointPosition = 0;
        try
        {
            linkRatio = new BigDecimal(ratioEdit.getText().toString());
        }
        catch (Exception e){}

        try
        {
            pointPosition = new Integer(pointEdit.getText().toString());
        }
        catch (Exception e){}

        if(pointPosition>=0)
        {
            if(Global.getInstance().getRecentCurrency().getTag().equals("EUR"))
            {
                Global.getInstance().databaseHelper.editCurrency(Global.getInstance().getRecentCurrency(), name, Global.getInstance().getRecentCurrency().getTag(), Global.getInstance().getRecentCurrency().getLinkRatio(), pointPosition, newLink);
                Global.getInstance().getRecentCurrency().setName(name);
                Global.getInstance().getRecentCurrency().setPointPosition(pointPosition);
            }
            else
            {
                Global.getInstance().databaseHelper.editCurrency(Global.getInstance().getRecentCurrency(), name, tag, linkRatio, pointPosition, newLink);
                Global.getInstance().getRecentCurrency().setName(name);
                Global.getInstance().getRecentCurrency().setTag(tag);
                Global.getInstance().getRecentCurrency().setLinkRatio(linkRatio);
                Global.getInstance().getRecentCurrency().setPointPosition(pointPosition);
                Global.getInstance().getRecentCurrency().setLink(newLink);
                if(Global.getInstance().getRecentCurrency().getTag().equals(Global.getInstance().getMainCurrency().getTag()))
                {
                    Global.getInstance().setMainCurrency(Global.getInstance().getRecentCurrency());
                }
            }
            Global.getInstance().databaseHelper.readCurrencies();
        }
        else
        {
            Global.getInstance().displayMessage("Please choose a number bigger or equal to 0");
        }

        onBackPressed();
    }
}
