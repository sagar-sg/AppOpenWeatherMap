package com.example.weatherapp.view;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;

import com.example.weatherapp.R;
import com.example.weatherapp.helper.ApiCallBack;
import com.example.weatherapp.model.Result;
import com.example.weatherapp.rest.Api;
import com.example.weatherapp.rest.Constant;
import com.example.weatherapp.viewmodel.WeatherViewModel;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.squareup.picasso.Picasso;

import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    SearchView searchView;
    ImageView ivWeather;
    TextView tvTemp, tvFeel, tvHumidity, tvLon, tvLat, tvCountry, tvSunrise, tvSunset, tvDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchView = findViewById(R.id.search_view);
        ivWeather = findViewById(R.id.iv_weather);
        tvTemp = findViewById(R.id.tv_temp);
        tvFeel = findViewById(R.id.tv_feels);
        tvLon = findViewById(R.id.tv_lon);
        tvLat = findViewById(R.id.tv_lat);
        tvHumidity = findViewById(R.id.tv_humidity);
        tvCountry = findViewById(R.id.tv_country);
        tvSunrise = findViewById(R.id.tv_sunrise);
        tvSunset = findViewById(R.id.tv_sunset);

        Dexter.withContext(this)
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {/* ... */}

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();

        setupSearchBar();

    }

    private void setupSearchBar() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query != null && query.length() > 1) {
                    fetchData(query);
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(searchView.getWindowToken(), 0);
                    searchView.clearFocus();
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void fetchData(String query) {
        WeatherViewModel weatherViewModel = new ViewModelProvider(this).get(WeatherViewModel.class);
        weatherViewModel.getWeatherData(query, apiCallBack);
    }

    ApiCallBack apiCallBack = new ApiCallBack() {
        @Override
        public void onSuccess(Response<Result> response) {
            Picasso.get().load(Constant.IMAGE_BASE_URL.concat(response.body().getWeather().get(0).getIcon() + ".png"))
                    .into(ivWeather);
            tvTemp.setText(String.format("Temperature : %s ℃", response.body().getMain().getTemp()));
            tvFeel.setText(String.format("Feels Like : %s ℃", response.body().getMain().getFeelsLike()));
            tvLon.setText(String.format("Longitude : %s °E", response.body().getCoord().getLon()));
            tvLat.setText(String.format("Latitude : %s °N", response.body().getCoord().getLat()));
            tvHumidity.setText("Humidity :" + " " + response.body().getMain().getHumidity() + " %");
            tvCountry.setText(String.format("country : %s", response.body().getSys().getCountry()));
            tvSunrise.setText("sunrise :" + " " + response.body().getSys().getSunrise() + " AM");
            tvSunset.setText("sunset :" + " " + response.body().getSys().getSunset() + " PM");
//                descText.setText("descText :"+" "+response.body().getWeather().g);
        }

        @Override
        public void onFailure(String errorMsg) {
            Toast.makeText(MainActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
        }
    };

}