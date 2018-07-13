package com.cameronlay.comp3617.termproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.cameronlay.comp3617.termproject.Templates.ArtPublic;
import com.cameronlay.comp3617.termproject.Templates.Coffee;
import com.cameronlay.comp3617.termproject.Templates.CreativeIndustry;
import com.cameronlay.comp3617.termproject.Templates.CulturalVenue;
import com.cameronlay.comp3617.termproject.Templates.HeritageInterest;
import com.cameronlay.comp3617.termproject.Templates.Literacy;
import com.cameronlay.comp3617.termproject.Templates.PlaygroundData;
import com.cameronlay.comp3617.termproject.Templates.RecProgram;
import com.cameronlay.comp3617.termproject.Templates.Sportsfield;
import com.cameronlay.comp3617.termproject.model.Artwork;
import com.cameronlay.comp3617.termproject.model.CoffeeShop;
import com.cameronlay.comp3617.termproject.model.CreativeIndustries;
import com.cameronlay.comp3617.termproject.model.CulturalVenues;
import com.cameronlay.comp3617.termproject.model.HeritageInterests;
import com.cameronlay.comp3617.termproject.model.LanguageLiteracy;
import com.cameronlay.comp3617.termproject.model.Playground;
import com.cameronlay.comp3617.termproject.model.Recreation;
import com.cameronlay.comp3617.termproject.model.SportsFields;
import com.cameronlay.comp3617.termproject.repository.ArtworkRepository;
import com.cameronlay.comp3617.termproject.repository.CoffeeShopRepository;
import com.cameronlay.comp3617.termproject.repository.CreativeIndustriesRepository;
import com.cameronlay.comp3617.termproject.repository.CulturalVenuesRepository;
import com.cameronlay.comp3617.termproject.repository.HeritageInterestsRepository;
import com.cameronlay.comp3617.termproject.repository.LanguageLiteracyRepository;
import com.cameronlay.comp3617.termproject.repository.PlaygroundRepository;
import com.cameronlay.comp3617.termproject.repository.RecreationRepository;
import com.cameronlay.comp3617.termproject.repository.SportsFieldsRepository;
import com.cameronlay.comp3617.termproject.viewmodel.MapsViewModel;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import android.app.Application;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.List;


public class ActivitiesMapActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final String TAG = MainActivity.class.getName();
    private GoogleMap mMap;
    private ArtworkRepository artworkRepository;
    private CoffeeShopRepository coffeeShopRepository;
    private CreativeIndustriesRepository creativeIndustriesRepository;
    private CulturalVenuesRepository culturalVenuesRepository;
    private HeritageInterestsRepository heritageInterestsRepository;
    private LanguageLiteracyRepository languageLiteracyRepository;
    private PlaygroundRepository playgroundRepository;
    private RecreationRepository recreationRepository;
    private SportsFieldsRepository sportsFieldsRepository;
    private MapsViewModel model;
    private String activityName;
    private List<LatLng> latLngs;
    private List<String> titles;
    private List<String> descriptions;
    private MapDataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities_map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Application application = getApplication();

        artworkRepository = new ArtworkRepository(application);
        coffeeShopRepository = new CoffeeShopRepository(application);
        creativeIndustriesRepository = new CreativeIndustriesRepository(application);
        culturalVenuesRepository = new CulturalVenuesRepository(application);
        heritageInterestsRepository = new HeritageInterestsRepository(application);
        languageLiteracyRepository = new LanguageLiteracyRepository(application);
        playgroundRepository = new PlaygroundRepository(application);
        recreationRepository = new RecreationRepository(application);
        sportsFieldsRepository = new SportsFieldsRepository(application);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        adapter = new MapDataAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        model = new MapsViewModel(application);
        latLngs = new ArrayList<>();
        titles = new ArrayList<>();
        descriptions = new ArrayList<>();
        descriptions.clear();
        latLngs.clear();
        titles.clear();

        Intent intent = getIntent();
        activityName = intent.getStringExtra(ActionListActivity.ACTIVITYNAME);

        DownloadTask task = new DownloadTask();
        if (activityName.equals(getResources().getStringArray(R.array.sunny)[0])) {
            task.execute("publicart.json");
        } else if (activityName.equals(getResources().getStringArray(R.array.sunny)[4])) {
            task.execute("coffee.json");
        } else if (activityName.equals(getResources().getStringArray(R.array.cloudy)[0])) {
            task.execute("creativeindustry.json");
        } else if (activityName.equals(getResources().getStringArray(R.array.sunny)[1])) {
            task.execute("culturalvenue.json");
        } else if (activityName.equals(getResources().getStringArray(R.array.cloudy)[1])) {
            task.execute("heritageinterests.json");
        } else if (activityName.equals(getResources().getStringArray(R.array.raining)[2])) {
            task.execute("literacy.json");
        } else if (activityName.equals(getResources().getStringArray(R.array.sunny)[2])) {
            task.execute("playgrounds.json");
        } else if (activityName.equals(getResources().getStringArray(R.array.cloudy)[3])) {
            task.execute("recreation.json");
        } else if (activityName.equals(getResources().getStringArray(R.array.sunny)[3])) {
            task.execute("sportsfields.json");
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng newWest = new LatLng(49.2057, -122.9110);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(newWest, 12.0f));
    }

    @SuppressLint("StaticFieldLeak")
    private class DownloadTask extends AsyncTask<String, Integer, Void> {
        @Override
        protected Void doInBackground(final String... strings) {
            try {
                artworkRepository.clearData();
                coffeeShopRepository.clearData();
                creativeIndustriesRepository.clearData();
                culturalVenuesRepository.clearData();
                heritageInterestsRepository.clearData();
                languageLiteracyRepository.clearData();
                playgroundRepository.clearData();
                recreationRepository.clearData();
                sportsFieldsRepository.clearData();

                AssetManager assetManager = getApplicationContext().getAssets();
                InputStream is;
                String json;
                String path = strings[0];

                is = assetManager.open(path);

                BufferedReader in = new BufferedReader(new InputStreamReader(is));
                String inputLine;
                final StringBuilder builder;

                builder = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    builder.append(inputLine);
                }

                in.close();
                is.close();

                json = builder.toString();

                if (activityName.equals(getResources().getStringArray(R.array.sunny)[0])) {
                    createArtwork(json);
                    List<Artwork> works = artworkRepository.getAllArtwork();
                    for (final Artwork work : works) {
                        System.out.println(work.getName());
                        latLngs.add(new LatLng(work.getLatitude(), work.getLongitude()));
                        titles.add(work.getName());
                        descriptions.add(work.getDescription());
                    }
                } else if (activityName.equals(getResources().getStringArray(R.array.sunny)[4])) {
                    createCoffee(json);
                    List<CoffeeShop> coffeeShops = coffeeShopRepository.getAllCoffeeShop();
                    for (final CoffeeShop coffee : coffeeShops) {
                        System.out.println(coffee.getName());
                        latLngs.add(new LatLng(coffee.getLatitude(), coffee.getLongitude()));
                        titles.add(coffee.getName());
                        descriptions.add(coffee.getDescription());
                    }
                } else if (activityName.equals(getResources().getStringArray(R.array.cloudy)[0])) {
                    createCreativeIndustry(json);
                    List<CreativeIndustries> creativeIndustries = creativeIndustriesRepository.getAllCreativeIndustries();
                    for (final CreativeIndustries creative : creativeIndustries) {
                        System.out.println(creative.getName());
                        latLngs.add(new LatLng(creative.getLatitude(), creative.getLongitude()));
                        titles.add(creative.getName());
                        descriptions.add(creative.getDescription());
                    }
                } else if (activityName.equals(getResources().getStringArray(R.array.sunny)[1])) {
                    createCulturalVenue(json);
                    List<CulturalVenues> culturalVenues = culturalVenuesRepository.getAllCulturalVenues();
                    for (final CulturalVenues venues : culturalVenues) {
                        System.out.println(venues.getName());
                        latLngs.add(new LatLng(venues.getLatitude(), venues.getLongitude()));
                        titles.add(venues.getName());
                        descriptions.add(venues.getDescription());
                    }
                } else if (activityName.equals(getResources().getStringArray(R.array.cloudy)[1])) {
                    createHeritageInterest(json);
                    List<HeritageInterests> heritageInterests = heritageInterestsRepository.getAllHeritageInterests();
                    for (final HeritageInterests interests : heritageInterests) {
                        System.out.println(interests.getName());
                        latLngs.add(new LatLng(interests.getLatitude(), interests.getLongitude()));
                        titles.add(interests.getName());
                        descriptions.add(interests.getDescription());
                    }
                } else if (activityName.equals(getResources().getStringArray(R.array.raining)[2])) {
                    createLiteracy(json);
                    List<LanguageLiteracy> languageLiteracies = languageLiteracyRepository.getAllLanguageLiteracy();
                    for (final LanguageLiteracy literacy : languageLiteracies) {
                        System.out.println(literacy.getName());
                        latLngs.add(new LatLng(literacy.getLatitude(), literacy.getLongitude()));
                        titles.add(literacy.getName());
                        descriptions.add(literacy.getDescription());
                    }
                } else if (activityName.equals(getResources().getStringArray(R.array.sunny)[2])) {
                    createPlayground(json);
                    List<Playground> playgrounds = playgroundRepository.getAllPlayground();
                    for (final Playground play : playgrounds) {
                        System.out.println(play.getName());
                        latLngs.add(new LatLng(play.getLatitude(), play.getLongitude()));
                        titles.add(play.getName());
                        descriptions.add(play.getDescription());
                    }
                } else if (activityName.equals(getResources().getStringArray(R.array.cloudy)[3])) {
                    createRecProgram(json);
                    List<Recreation> recreations = recreationRepository.getAllRecreation();
                    Log.wtf("THIS IS IMPORTANT", Integer.toString(recreations.size()));
                    for (final Recreation rec : recreations) {
                        System.out.println(rec.getName());
                        latLngs.add(new LatLng(rec.getLatitude(), rec.getLongitude()));
                        titles.add(rec.getName());
                        descriptions.add(rec.getDescription());
                    }
                    Log.wtf("THIS IS ALSO IMPORTANT", Integer.toString(titles.size()));
                } else if (activityName.equals(getResources().getStringArray(R.array.sunny)[3])) {
                    createSportsfield(json);
                    List<SportsFields> sportsFields = sportsFieldsRepository.getAllSportsFields();
                    for (final SportsFields fields : sportsFields) {
                        System.out.println(fields.getName());
                        latLngs.add(new LatLng(fields.getLatitude(), fields.getLongitude()));
                        titles.add(fields.getName());
                        descriptions.add(fields.getDescription());
                    }
                }
            } catch (final FileNotFoundException ex) {
                Log.e(TAG, "File not found", ex);
            } catch (final IOException ex) {
                Log.e(TAG, "I/O Error", ex);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            for (int i = 0; i < latLngs.size(); i++) {
                mMap.addMarker(new MarkerOptions().position(latLngs.get(i)).title(titles.get(i)));
            }
            adapter.setMapData(titles, descriptions);
//            Toast.makeText(getApplicationContext(), Integer.toString(titles.size()), Toast.LENGTH_LONG).show();
        }

        private void createArtwork(final String json) {
            final Gson gson;
            final ArtPublic artPublic;
            final ArtPublic.Feature[] features;

            gson = new Gson();
            artPublic = gson.fromJson(json, ArtPublic.class);
            features = artPublic.getFeatures();

            for (final ArtPublic.Feature feature : features) {
                model.addArtwork(feature);
            }
        }

        private void createCoffee(final String json) {
            final Gson gson;
            final Coffee coffee;
            final Coffee.Feature[] features;

            gson = new Gson();
            coffee = gson.fromJson(json, Coffee.class);
            features = coffee.getFeatures();

            for (final Coffee.Feature feature : features) {
                model.addCoffee(feature);
            }
        }

        private void createCreativeIndustry(final String json) {
            final Gson gson;
            final CreativeIndustry creativeIndustry;
            final CreativeIndustry.Feature[] features;

            gson = new Gson();
            creativeIndustry = gson.fromJson(json, CreativeIndustry.class);
            features = creativeIndustry.getFeatures();

            for (final CreativeIndustry.Feature feature : features) {
                model.addCreativeIndustry(feature);
            }
        }

        private void createCulturalVenue(final String json) {
            final Gson gson;
            final CulturalVenue culturalVenue;
            final CulturalVenue.Feature[] features;

            gson = new Gson();
            culturalVenue = gson.fromJson(json, CulturalVenue.class);
            features = culturalVenue.getFeatures();

            for (final CulturalVenue.Feature feature : features) {
                model.addCulturalVenue(feature);
            }
        }

        private void createHeritageInterest(final String json) {
            final Gson gson;
            final HeritageInterest heritageInterest;
            final HeritageInterest.Feature[] features;

            gson = new Gson();
            heritageInterest = gson.fromJson(json, HeritageInterest.class);
            features = heritageInterest.getFeatures();

            for (final HeritageInterest.Feature feature : features) {
                model.addHeritageInterest(feature);
            }
        }

        private void createLiteracy(final String json) {
            final Gson gson;
            final Literacy literacy;
            final Literacy.Feature[] features;

            gson = new Gson();
            literacy = gson.fromJson(json, Literacy.class);
            features = literacy.getFeatures();

            for (final Literacy.Feature feature : features) {
                model.addLiteracy(feature);
            }
        }

        private void createPlayground(final String json) {
            final Gson gson;
            final PlaygroundData playgroundData;
            final PlaygroundData.Feature[] features;

            gson = new Gson();
            playgroundData = gson.fromJson(json, PlaygroundData.class);
            features = playgroundData.getFeatures();

            for (final PlaygroundData.Feature feature : features) {
                model.addPlaygroundData(feature);
            }
        }

        private void createRecProgram(final String json) {
            final Gson gson;
            final RecProgram recProgram;
            final RecProgram.Feature[] features;

            gson = new Gson();
            recProgram = gson.fromJson(json, RecProgram.class);
            features = recProgram.getFeatures();

            for (final RecProgram.Feature feature : features) {
                model.addRecProgram(feature);
            }
        }

        private void createSportsfield(final String json) {
            final Gson gson;
            final Sportsfield sportsfield;
            final Sportsfield.Feature[] features;

            gson = new Gson();
            sportsfield = gson.fromJson(json, Sportsfield.class);
            features = sportsfield.getFeatures();

            for (final Sportsfield.Feature feature : features) {
                model.addSportsfield(feature);
            }
        }
    }
}
