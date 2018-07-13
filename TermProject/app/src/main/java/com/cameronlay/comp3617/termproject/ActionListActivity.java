package com.cameronlay.comp3617.termproject;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class ActionListActivity extends AppCompatActivity {

    public static String ACTIVITYNAME = "activityName";
    private ArrayList<String> activityList;
    private ArrayList<String> activityDescriptionList;
    private ArrayList<Drawable> activityImageList;
    String activityName;

    TextView activityOneTitle;
    TextView activityTwoTitle;
    TextView activityThreeTitle;

    TextView activityOneDescription;
    TextView activityTwoDescription;
    TextView activityThreeDescription;

    ImageView activityOneImg;
    ImageView activityTwoImg;
    ImageView activityThreeImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_list);

        activityOneTitle = findViewById(R.id.activityOneTitle);
        activityTwoTitle = findViewById(R.id.activityTwoTitle);
        activityThreeTitle = findViewById(R.id.activityThreeTitle);

        activityOneDescription = findViewById(R.id.activityOneDescription);
        activityTwoDescription = findViewById(R.id.activityTwoDescription);
        activityThreeDescription = findViewById(R.id.activityThreeDescription);

        activityOneImg = findViewById(R.id.activityOneImage);
        activityTwoImg = findViewById(R.id.activityTwoImage);
        activityThreeImg = findViewById(R.id.activityThreeImage);

        activityList = new ArrayList<>();
        activityDescriptionList = new ArrayList<>();
        activityImageList = new ArrayList<>();

        Intent intent = getIntent();
        int weatherCode = intent.getIntExtra(MainActivity.WEATHERCODE, 9999);

        if (weatherCode >= 0 && weatherCode <= 300) {
            //lightning
            clearArrays();
            activityList.addAll(Arrays.asList(getResources().getStringArray(R.array.raining)));
            activityDescriptionList.addAll(Arrays.asList(getResources().getStringArray(R.array.rainingDescription)));
            addToImgArray("rain");
        } else if (weatherCode >= 301 && weatherCode <= 500) {
            //light rain
            clearArrays();
            activityList.addAll(Arrays.asList(getResources().getStringArray(R.array.raining)));
            activityDescriptionList.addAll(Arrays.asList(getResources().getStringArray(R.array.rainingDescription)));
            addToImgArray("rain");
        } else if (weatherCode >= 501 && weatherCode <= 600) {
            //heavy rain
            clearArrays();
            activityList.addAll(Arrays.asList(getResources().getStringArray(R.array.raining)));
            activityDescriptionList.addAll(Arrays.asList(getResources().getStringArray(R.array.rainingDescription)));
            addToImgArray("rain");
        } else if (weatherCode >= 601 && weatherCode <= 700) {
            //snow
            clearArrays();
            activityList.addAll(Arrays.asList(getResources().getStringArray(R.array.raining)));
            activityDescriptionList.addAll(Arrays.asList(getResources().getStringArray(R.array.rainingDescription)));
            addToImgArray("rain");
        } else if (weatherCode >= 701 && weatherCode <= 799) {
            //mist
            clearArrays();
            activityList.addAll(Arrays.asList(getResources().getStringArray(R.array.cloudy)));
            activityDescriptionList.addAll(Arrays.asList(getResources().getStringArray(R.array.cloudyDescription)));
            addToImgArray("cloud");
        } else if (weatherCode == 800) {
            //sunny
            clearArrays();
            activityList.addAll(Arrays.asList(getResources().getStringArray(R.array.sunny)));
            activityDescriptionList.addAll(Arrays.asList(getResources().getStringArray(R.array.sunnyDescription)));
            addToImgArray("sun");
        } else if (weatherCode >= 801 && weatherCode <= 802) {
            //cloudy
            clearArrays();
            activityList.addAll(Arrays.asList(getResources().getStringArray(R.array.cloudy)));
            activityDescriptionList.addAll(Arrays.asList(getResources().getStringArray(R.array.cloudyDescription)));
            addToImgArray("cloud");
        } else if (weatherCode >= 803 && weatherCode <= 804) {
            //overcast
            clearArrays();
            activityList.addAll(Arrays.asList(getResources().getStringArray(R.array.cloudy)));
            activityDescriptionList.addAll(Arrays.asList(getResources().getStringArray(R.array.cloudyDescription)));
            addToImgArray("cloud");
        } else {
            //error
            clearArrays();
            activityOneTitle.setText(getString(R.string.weatherError));
            activityTwoTitle.setText(getString(R.string.weatherError));
            activityThreeTitle.setText(getString(R.string.weatherError));
            activityOneDescription.setText(getString(R.string.weatherError));
            activityTwoDescription.setText(getString(R.string.weatherError));
            activityThreeDescription.setText(getString(R.string.weatherError));
        }

        //Toast.makeText(this, activityImageList.size(), Toast.LENGTH_LONG).show();
        if (activityList.size() > 3) {
            int firstItemIndex = (int) (Math.random() * (activityList.size()));
            String firstItem = activityList.get(firstItemIndex);
            activityOneTitle.setText(firstItem);
            activityList.remove(firstItemIndex);

            String firstItemDescription = activityDescriptionList.get(firstItemIndex);
            activityOneDescription.setText(firstItemDescription);
            activityDescriptionList.remove(firstItemIndex);

            Drawable firstItemImg = activityImageList.get(firstItemIndex);
            activityOneImg.setImageDrawable(firstItemImg);
            activityImageList.remove(firstItemIndex);

            int secondItemIndex = (int) (Math.random() * (activityList.size()));
            String secondItem = activityList.get(secondItemIndex);
            activityTwoTitle.setText(secondItem);
            activityList.remove(secondItemIndex);

            String secondItemDescription = activityDescriptionList.get(secondItemIndex);
            activityTwoDescription.setText(secondItemDescription);
            activityDescriptionList.remove(secondItemIndex);

            Drawable secondItemImg = activityImageList.get(secondItemIndex);
            activityTwoImg.setImageDrawable(secondItemImg);
            activityImageList.remove(secondItemIndex);

            int thirdItemIndex = (int) (Math.random() * (activityList.size()));
            String thirdItem = activityList.get(thirdItemIndex);
            activityThreeTitle.setText(thirdItem);
            activityList.remove(thirdItemIndex);

            String thirdItemDescription = activityDescriptionList.get(thirdItemIndex);
            activityThreeDescription.setText(thirdItemDescription);
            activityDescriptionList.remove(thirdItemIndex);

            Drawable thirdItemImg = activityImageList.get(thirdItemIndex);
            activityThreeImg.setImageDrawable(thirdItemImg);
            activityImageList.remove(thirdItemIndex);
        }
    }

    public void goToMap (View view) {

        Intent intent = new Intent(this, ActivitiesMapActivity.class);

        switch (view.getTag().toString()) {
            case "rowOne":
                activityName = activityOneTitle.getText().toString();
                break;
            case "rowTwo":
                activityName = activityTwoTitle.getText().toString();
                break;
            case "rowThree":
                activityName = activityThreeTitle.getText().toString();
                break;
            default:
                Toast.makeText(getApplicationContext(), "Error getting data", Toast.LENGTH_SHORT).show();
                break;
        }

        intent.putExtra(ACTIVITYNAME, activityName);
        startActivity(intent);
    }

    public void clearArrays() {
        activityList.clear();
        activityDescriptionList.clear();
        activityImageList.clear();
    }

    public void addToImgArray (String weatherCondition) {

        switch (weatherCondition) {
            case "rain":
                activityImageList.add(getResources().getDrawable(R.drawable.popcorn));
                activityImageList.add(getResources().getDrawable(R.drawable.videogames));
                activityImageList.add(getResources().getDrawable(R.drawable.literacy));
                break;
            case "sun":
                activityImageList.add(getResources().getDrawable(R.drawable.artwork));
                activityImageList.add(getResources().getDrawable(R.drawable.culturalvenue));
                activityImageList.add(getResources().getDrawable(R.drawable.playground));
                activityImageList.add(getResources().getDrawable(R.drawable.sports));
                activityImageList.add(getResources().getDrawable(R.drawable.coffee));
                break;
            case "cloud":
                activityImageList.add(getResources().getDrawable(R.drawable.creativeindustry));
                activityImageList.add(getResources().getDrawable(R.drawable.heritage));
                activityImageList.add(getResources().getDrawable(R.drawable.sports));
                activityImageList.add(getResources().getDrawable(R.drawable.recreation));
                break;
            default:
                Toast.makeText(this, getString(R.string.weatherError), Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
