package com.example.wheresmymoney;

import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.math.BigDecimal;

public interface APIRequest
{
    int getID();

    void setID(int newID);

    String getName();

    String getRequestURL();

    void setRequestURL(String newRequestURL);

    void makeRequest(API requestAPI);
}
