package com.cameronlay.comp3617.termproject;

import android.content.Context;
import android.os.AsyncTask;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kwabenaberko.openweathermaplib.Units;
import com.kwabenaberko.openweathermaplib.implementation.OpenWeatherMapHelper;
import com.kwabenaberko.openweathermaplib.models.currentweather.CurrentWeather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class ChangeCityActivity extends AppCompatActivity {

    TextView tempText;
    EditText cityName;
    TextView searchedCity;
    ConstraintLayout constraintLayout;
    ImageView weatherImage;
    Button button;
    public static final String TAG = "WEATHER DATA";
    OpenWeatherMapHelper helper;

    public void getWeather(View view) {

        String enteredCity = "";
        if (!(cityName.getText().toString().equals(""))) {
            enteredCity = cityName.getText().toString();
        }

        if(!(enteredCity.equals(""))) {

            helper.getCurrentWeatherByCityName(enteredCity, new OpenWeatherMapHelper.CurrentWeatherCallback() {

                @Override
                public void onSuccess(CurrentWeather currentWeather) {
                    weatherImage.setImageResource(setWeather(currentWeather.getWeatherArray().get(0).getId()));
                    searchedCity.setText(currentWeather.getName() + ", " + currentWeather.getSys().getCountry());
                    tempText.setText((getString(R.string.degreeCelsius, (int) Math.round(currentWeather.getMain().getTemp()))));

                }

                @Override
                public void onFailure(Throwable throwable) {
                    Toast.makeText(getApplicationContext(), "Could not find City", Toast.LENGTH_LONG).show();
                    Log.wtf(TAG, throwable.getMessage());
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "Please enter a city", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_city);

        tempText = findViewById(R.id.cityTemp);
        cityName = findViewById(R.id.cityName);
        searchedCity = findViewById(R.id.searchedCity);
        constraintLayout = findViewById(R.id.otherCityConstraintLayout);
        weatherImage = findViewById(R.id.weatherImage);
        button = findViewById(R.id.searchButton);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        constraintLayout.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                }
                return true;
            }
        });

        helper = new OpenWeatherMapHelper();
        helper.setApiKey(getString(R.string.OPEN_WEATHER_MAP_API_KEY));
        helper.setUnits(Units.METRIC);

        helper.getCurrentWeatherByCityName("New Westminster", new OpenWeatherMapHelper.CurrentWeatherCallback() {

            @Override
            public void onSuccess(CurrentWeather currentWeather) {
                weatherImage.setImageResource(setWeather(currentWeather.getWeatherArray().get(0).getId()));
                searchedCity.setText(currentWeather.getName() + ", " + currentWeather.getSys().getCountry());
                tempText.setText((getString(R.string.degreeCelsius, (int) Math.round(currentWeather.getMain().getTemp()))));

            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.v(TAG, throwable.getMessage());
            }
        });

    }

    public int setWeather(long weatherCode) {
        if (weatherCode >= 0 && weatherCode <= 300) {
            return R.drawable.thunderstorm;
        } else if (weatherCode >= 301 && weatherCode <= 500) {
            return R.drawable.lightrain;
        } else if (weatherCode >= 501 && weatherCode <= 600) {
            return R.drawable.heavyrain;
        } else if (weatherCode >= 601 && weatherCode <= 700) {
            return R.drawable.snow;
        } else if (weatherCode >= 701 && weatherCode <= 799) {
            return R.drawable.mist;
        } else if (weatherCode == 800) {
            return R.drawable.sunny;
        } else if (weatherCode >= 801 && weatherCode <= 802) {
            return R.drawable.cloudy;
        } else if (weatherCode >= 803 && weatherCode <= 804) {
            return R.drawable.overcast;
        } else {
            return R.drawable.error;
        }
    }
}

