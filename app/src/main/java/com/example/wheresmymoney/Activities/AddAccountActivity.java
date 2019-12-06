package com.example.wheresmymoney.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.wheresmymoney.Model.Account;
import com.example.wheresmymoney.Model.Currency;
import com.example.wheresmymoney.R;
import com.example.wheresmymoney.Singleton.Global;

import java.util.ArrayList;

public class AddAccountActivity extends AppCompatActivity {
    Currency newAccountsCurrency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(Global.getInstance().getRecentTheme());
        setContentView(R.layout.activity_add_account);
    }

    protected void onResume()
    {
        super.onResume();

        ListView list;
        ArrayList<String> currenciesListData = new ArrayList<>();
        for (Currency currency : Global.getInstance().getCurrenciesList()) {
            currenciesListData.add(currency.getTag());
        }
        list = findViewById(R.id.currencies);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, currenciesListData);
        list.setAdapter(adapter3);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                newAccountsCurrency = Global.getInstance().getCurrenciesList().get(position);
                Global.getInstance().displayMessage(newAccountsCurrency.getTag());
            }
        });

        final EditText editText = findViewById(R.id.editText);
        Button button = findViewById(R.id.button5);
        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                if(newAccountsCurrency!=null)
                {
                    if (editText.getText() != null && !editText.getText().toString().equals("") && editText.getText().toString().length() > 0)
                    {
                        boolean nameExists = false;
                        for(Account account : Global.getInstance().getAccountsList())
                        {
                            if(account.getName().equals(editText.getText().toString()))
                            {
                                nameExists=true;
                            }
                        }

                        if(!nameExists)
                        {
                            Global.getInstance().displayMessage("Account added:\n"+editText.getText().toString()+"\n"+newAccountsCurrency.getTag());
                            Global.getInstance().databaseHelper.addAccount(new Account(editText.getText().toString(), newAccountsCurrency));
                            Global.getInstance().databaseHelper.readAccounts();
                        }
                        else
                        {
                            Global.getInstance().displayMessage("Name is already taken");
                        }
                    }
                    else
                    {
                        Global.getInstance().displayMessage("Please select a name for the account");
                    }
                }
                else
                {
                    Global.getInstance().displayMessage("Please select a currency for the account");
                }
                onBackPressed();
            }
        });
    }
}
