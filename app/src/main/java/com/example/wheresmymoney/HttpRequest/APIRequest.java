package com.example.wheresmymoney.HttpRequest;

import com.example.wheresmymoney.Model.API;

public interface APIRequest
{
    int getID();

    void setID(int newID);

    String getName();

    String getRequestURL();

    void setRequestURL(String newRequestURL);

    void makeRequest(API requestAPI);
}
