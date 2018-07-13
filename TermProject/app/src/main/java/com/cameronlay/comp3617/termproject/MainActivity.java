package com.cameronlay.comp3617.termproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


public class MainActivity extends AppCompatActivity {

    private TextView temperatureText;
    private ImageView weatherIcon;
    public static String WEATHERCODE = "WEATHERCODE";
    private int currentWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ScrollView scrollView = findViewById(R.id.landingScrollView);
        temperatureText = findViewById(R.id.temperatureText);
        ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout);
        weatherIcon = findViewById(R.id.weatherIcon);

        // white background notification bar
        whiteNotificationBar(scrollView);

        temperatureText.setText("");
        String cityName = "New Westminster";

        try {
            String encodedCityName = URLEncoder.encode(cityName, "UTF-8");
            String siteName = "http://api.openweathermap.org/data/2.5/weather?q=";
            String apiKey = "&appid=83d9af9ccc63b0027916ec61464e46c1";
            WeatherDownloader task = new WeatherDownloader();

            task.execute(siteName + encodedCityName + apiKey);
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(constraintLayout.getWindowToken(), 0);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void recommendActivity(View view) {

        Intent intent = new Intent(this, ActionListActivity.class);
        intent.putExtra(WEATHERCODE, currentWeather);
        startActivity(intent);
    }

    public void weatherForecast(View view) {

        Intent intent = new Intent(this, ForecastActivity.class);
        startActivity(intent);
    }

    public void changeCity(View view) {

        Intent intent = new Intent(this, ChangeCityActivity.class);
        intent.putExtra("temp", temperatureText.getText());
        intent.putExtra("city", "New Westminster");
        startActivity(intent);
    }

    public void groupChat(View view) {

        Intent intent = new Intent(this, GroupChatActivity.class);
        startActivity(intent);
    }

    // Used to create a white notification bar for the app
    private void whiteNotificationBar(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = view.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
            getWindow().setStatusBarColor(Color.WHITE);
        }
    }

    public class WeatherDownloader extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {

            StringBuilder result = new StringBuilder();
            URL url;
            HttpURLConnection urlConnection;

            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();

                while (data != -1) {
                    char current = (char) data;
                    result.append(current);
                    data = reader.read();
                }

                return result.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Could not find weather", Toast.LENGTH_LONG).show();
            }
            return null;
        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            try {
                JSONObject jsonObject = new JSONObject(result);
                String weatherInfo = jsonObject.getString("weather");
                JSONObject tempInfo = jsonObject.getJSONObject("main");

                JSONArray array = new JSONArray(weatherInfo);

                int temp = -1000;

                for (int i = 0; i < array.length(); i++) {
                    JSONObject jsonPart = array.getJSONObject(i);

                    int weatherCode = jsonPart.getInt("id");
                    double tempDbl = tempInfo.getDouble("temp") - 273.15;

                    temp = (int) Math.round(tempDbl);

                    if (weatherCode >= 0 && weatherCode <= 300) {
                        weatherIcon.setImageResource(R.drawable.thunderstorm);
                    } else if (weatherCode >= 301 && weatherCode <= 500) {
                        weatherIcon.setImageResource(R.drawable.lightrain);
                    } else if (weatherCode >= 501 && weatherCode <= 600) {
                        weatherIcon.setImageResource(R.drawable.heavyrain);
                    } else if (weatherCode >= 601 && weatherCode <= 700) {
                        weatherIcon.setImageResource(R.drawable.snow);
                    } else if (weatherCode >= 701 && weatherCode <= 799) {
                        weatherIcon.setImageResource(R.drawable.mist);
                    } else if (weatherCode == 800) {
                        weatherIcon.setImageResource(R.drawable.sunny);
                    } else if (weatherCode >= 801 && weatherCode <= 802) {
                        weatherIcon.setImageResource(R.drawable.cloudy);
                    } else if (weatherCode >= 803 && weatherCode <= 804) {
                        weatherIcon.setImageResource(R.drawable.overcast);
                    } else {
                        weatherIcon.setImageResource(R.drawable.error);
                    }

                    currentWeather = weatherCode;
                }

                temperatureText.setText(getString(R.string.degreeCelsius, temp));

            } catch (JSONException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                Toast.makeText(getApplicationContext(), "Could not find city", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Exception caught in onPostExecute", Toast.LENGTH_LONG).show();
            }
        }
    }
}
