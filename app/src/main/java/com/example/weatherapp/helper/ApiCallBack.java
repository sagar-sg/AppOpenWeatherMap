package com.example.weatherapp.helper;

import com.example.weatherapp.model.Result;

import retrofit2.Response;

public interface ApiCallBack {
    void onSuccess(Response<Result> response);
    void onFailure(String errorMsg);

}
