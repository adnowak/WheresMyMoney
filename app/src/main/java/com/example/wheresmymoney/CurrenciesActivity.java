package com.example.wheresmymoney;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.math.BigDecimal;
import java.util.ArrayList;

public class CurrenciesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(Global.getInstance().getRecentTheme());
        setContentView(R.layout.activity_currencies);
    }

    protected void onResume()
    {
        super.onResume();
        Currency.sortCurrencies(Global.getInstance().getCurrenciesList());

        final ArrayList<String> currenciesListData = new ArrayList<String>();

        for(Currency currency : Global.getInstance().getCurrenciesList())
        {
            currenciesListData.add(currency.toString());
        }

        final ListView list = findViewById(R.id.listViewCurrencies);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, currenciesListData);
        list.setAdapter(adapter2);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                final Intent intent = new Intent(getApplicationContext(), CurrencyActivity.class);
                for(Currency currency : Global.getInstance().getCurrenciesList())
                {
                    if(currency.toString().equals(currenciesListData.get(position)))
                    {
                        Global.getInstance().setRecentCurrency(currency);
                        break;

                    }
                }
                startActivity(intent);
            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id)
            {
                list.setOnItemClickListener(null);
                AlertDialog.Builder builder = new AlertDialog.Builder(CurrenciesActivity.this, R.style.AlertDialog);

                builder.setTitle("Delete "+Global.getInstance().getCurrenciesList().get(position).getName());
                builder.setMessage("Are you sure?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                        boolean deleted = Global.getInstance().databaseHelper.deleteSingleCurrencyIfPossible(Global.getInstance().getCurrenciesList().get(position));
                        if(deleted)
                        {
                            Global.getInstance().displayMessage("Deleting");
                        }
                        else
                        {
                            Global.getInstance().displayMessage("Can't delete "+Global.getInstance().getCurrenciesList().get(position).getName());
                        }
                        dialog.dismiss();
                        list.setOnItemClickListener(new AdapterView.OnItemClickListener()
                        {
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                            {
                                //Global.recentAccount = Global.accountsList.get(position);
                                //startActivity(intent);
                            }
                        });
                        onResume();
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Do nothing
                        Global.getInstance().displayMessage("Deleting cancelled");
                        dialog.dismiss();
                        list.setOnItemClickListener(new AdapterView.OnItemClickListener()
                        {
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                            {
                                //Global.recentAccount = Global.accountsList.get(position);
                                //startActivity(intent);
                            }
                        });
                        onResume();
                    }
                });

                builder.setOnDismissListener(new DialogInterface.OnDismissListener() {

                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        //If the error flag was set to true then show the dialog again
                        list.setOnItemClickListener(new AdapterView.OnItemClickListener()
                        {
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                            {
                                final Intent intent = new Intent(getApplicationContext(), CurrencyActivity.class);
                                for(Currency currency : Global.getInstance().getCurrenciesList())
                                {
                                    if(currency.toString().equals(currenciesListData.get(position)))
                                    {
                                        Global.getInstance().setRecentCurrency(currency);
                                        break;

                                    }
                                }
                                startActivity(intent);
                            }
                        });
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
                return false;
            }
        });
    }

    public void addCurrency(View view)
    {
        final Intent intent = new Intent(this, AddCurrencyActivity.class);
        startActivity(intent);
    }

    public void updateCurrencies(View view)
    {
        Global.getInstance().displayMessage("Updating currencies");
        try
        {
            Global.getInstance().setRecentAPI(Global.getInstance().getApisList().get(0));
            Global.getInstance().getRecentAPI().makeRequest("Rates", this);
        }
        catch (Exception e)
        {
            Global.getInstance().displayMessage("This functionality isn't yet available");
        }
    }
}
