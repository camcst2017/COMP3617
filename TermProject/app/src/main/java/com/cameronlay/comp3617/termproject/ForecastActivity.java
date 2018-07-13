package com.cameronlay.comp3617.termproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.kwabenaberko.openweathermaplib.Units;
import com.kwabenaberko.openweathermaplib.implementation.OpenWeatherMapHelper;
import com.kwabenaberko.openweathermaplib.models.threehourforecast.ThreeHourForecast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ForecastActivity extends AppCompatActivity {

    public static final String TAG = "WEATHER DATA";

    public ImageView day1Img;
    public ImageView day2Img;
    public ImageView day3Img;
    public ImageView day4Img;
    public ImageView day5Img;

    public TextView day1Temp;
    public TextView day2Temp;
    public TextView day3Temp;
    public TextView day4Temp;
    public TextView day5Temp;

    public TextView day1Date;
    public TextView day2Date;
    public TextView day3Date;
    public TextView day4Date;
    public TextView day5Date;

    public TextView day1DescriptionDate;
    public TextView day2DescriptionDate;
    public TextView day3DescriptionDate;
    public TextView day4DescriptionDate;
    public TextView day5DescriptionDate;

    public TextView day1Description;
    public TextView day2Description;
    public TextView day3Description;
    public TextView day4Description;
    public TextView day5Description;

    Date date1;
    Date date2;
    Date date3;
    Date date4;
    Date date5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        day1Img = findViewById(R.id.day1Img);
        day2Img = findViewById(R.id.day2Img);
        day3Img = findViewById(R.id.day3Img);
        day4Img = findViewById(R.id.day4Img);
        day5Img = findViewById(R.id.day5Img);

        day1Date = findViewById(R.id.day1Date);
        day2Date = findViewById(R.id.day2Date);
        day3Date = findViewById(R.id.day3Date);
        day4Date = findViewById(R.id.day4Date);
        day5Date = findViewById(R.id.day5Date);

        day1Temp = findViewById(R.id.day1Temp);
        day2Temp = findViewById(R.id.day2Temp);
        day3Temp = findViewById(R.id.day3Temp);
        day4Temp = findViewById(R.id.day4Temp);
        day5Temp = findViewById(R.id.day5Temp);

        day1DescriptionDate = findViewById(R.id.day1DescriptionDate);
        day2DescriptionDate = findViewById(R.id.day2DescriptionDate);
        day3DescriptionDate = findViewById(R.id.day3DescriptionDate);
        day4DescriptionDate = findViewById(R.id.day4DescriptionDate);
        day5DescriptionDate = findViewById(R.id.day5DescriptionDate);

        day1Description = findViewById(R.id.day1Description);
        day2Description = findViewById(R.id.day2Description);
        day3Description = findViewById(R.id.day3Description);
        day4Description = findViewById(R.id.day4Description);
        day5Description = findViewById(R.id.day5Description);

        final SimpleDateFormat formatShort = new SimpleDateFormat("EEE", Locale.ENGLISH);
        final SimpleDateFormat formatLong = new SimpleDateFormat("EEEE", Locale.ENGLISH);

        OpenWeatherMapHelper helper = new OpenWeatherMapHelper();
        helper.setApiKey(getString(R.string.OPEN_WEATHER_MAP_API_KEY));
        helper.setUnits(Units.METRIC);

        helper.getThreeHourForecastByCityName("New Westminster", new OpenWeatherMapHelper.ThreeHourForecastCallback() {
            @Override
            public void onSuccess(ThreeHourForecast threeHourForecast) {

                day1Img.setImageResource(setWeather(threeHourForecast.getThreeHourWeatherArray().get(3).getWeatherArray().get(0).getId()));
                day2Img.setImageResource(setWeather(threeHourForecast.getThreeHourWeatherArray().get(11).getWeatherArray().get(0).getId()));
                day3Img.setImageResource(setWeather(threeHourForecast.getThreeHourWeatherArray().get(19).getWeatherArray().get(0).getId()));
                day4Img.setImageResource(setWeather(threeHourForecast.getThreeHourWeatherArray().get(27).getWeatherArray().get(0).getId()));
                day5Img.setImageResource(setWeather(threeHourForecast.getThreeHourWeatherArray().get(35).getWeatherArray().get(0).getId()));

                day1Temp.setText((getString(R.string.degreeCelsius, (int) Math.round(threeHourForecast.getThreeHourWeatherArray().get(3).getMain().getTemp()))));
                day2Temp.setText((getString(R.string.degreeCelsius, (int) Math.round(threeHourForecast.getThreeHourWeatherArray().get(11).getMain().getTemp()))));
                day3Temp.setText((getString(R.string.degreeCelsius, (int) Math.round(threeHourForecast.getThreeHourWeatherArray().get(19).getMain().getTemp()))));
                day4Temp.setText((getString(R.string.degreeCelsius, (int) Math.round(threeHourForecast.getThreeHourWeatherArray().get(27).getMain().getTemp()))));
                day5Temp.setText((getString(R.string.degreeCelsius, (int) Math.round(threeHourForecast.getThreeHourWeatherArray().get(35).getMain().getTemp()))));

                date1 = new Date(threeHourForecast.getThreeHourWeatherArray().get(3).getDt() * 1000);
                date2 = new Date(threeHourForecast.getThreeHourWeatherArray().get(11).getDt() * 1000);
                date3 = new Date(threeHourForecast.getThreeHourWeatherArray().get(19).getDt() * 1000);
                date4 = new Date(threeHourForecast.getThreeHourWeatherArray().get(27).getDt() * 1000);
                date5 = new Date(threeHourForecast.getThreeHourWeatherArray().get(35).getDt() * 1000);

                day1Date.setText(formatShort.format(date1));
                day2Date.setText(formatShort.format(date2));
                day3Date.setText(formatShort.format(date3));
                day4Date.setText(formatShort.format(date4));
                day5Date.setText(formatShort.format(date5));

                day1DescriptionDate.setText(formatLong.format(date1));
                day2DescriptionDate.setText(formatLong.format(date2));
                day3DescriptionDate.setText(formatLong.format(date3));
                day4DescriptionDate.setText(formatLong.format(date4));
                day5DescriptionDate.setText(formatLong.format(date5));

                day1Description.setText(threeHourForecast.getThreeHourWeatherArray().get(3).getWeatherArray().get(0).getDescription());
                day2Description.setText(threeHourForecast.getThreeHourWeatherArray().get(11).getWeatherArray().get(0).getDescription());
                day3Description.setText(threeHourForecast.getThreeHourWeatherArray().get(19).getWeatherArray().get(0).getDescription());
                day4Description.setText(threeHourForecast.getThreeHourWeatherArray().get(27).getWeatherArray().get(0).getDescription());
                day5Description.setText(threeHourForecast.getThreeHourWeatherArray().get(35).getWeatherArray().get(0).getDescription());
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
