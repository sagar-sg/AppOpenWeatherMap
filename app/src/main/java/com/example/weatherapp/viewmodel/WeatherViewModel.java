package com.example.weatherapp.viewmodel;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.weatherapp.helper.ApiCallBack;
import com.example.weatherapp.model.Result;
import com.example.weatherapp.rest.Api;
import com.example.weatherapp.rest.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherViewModel extends ViewModel {

    public void getWeatherData(String name, ApiCallBack apiCallBack) {

        Api apiInterface = RetrofitClient.getInstance().getMyApi();

        Call<Result> call = apiInterface.getWeatherData(name, "5e0b37c914a240dd68c841311795b913", "metric");

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {

                if (response.body()!=null && response.isSuccessful() && response.body().getCod() == 200) {
                    Log.d("response", response.body().toString());
                    apiCallBack.onSuccess(response);
                } else if (response.body()==null || response.code() == 404) {
                    apiCallBack.onFailure(response.message());
                }

            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.d("failure", "error");
                apiCallBack.onFailure(t.getMessage());
            }
        });

    }
}
