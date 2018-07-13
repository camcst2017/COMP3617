package com.cameronlay.comp3617.termproject.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.util.Log;

import com.cameronlay.comp3617.termproject.Templates.*;
import com.cameronlay.comp3617.termproject.model.*;
import com.cameronlay.comp3617.termproject.repository.*;

public class MapsViewModel extends AndroidViewModel {
    private static final String TAG = MapsViewModel.class.getName();
    private ArtworkRepository artworkRepository;
    private CoffeeShopRepository coffeeShopRepository;
    private CreativeIndustriesRepository creativeIndustriesRepository;
    private CulturalVenuesRepository culturalVenuesRepository;
    private HeritageInterestsRepository heritageInterestsRepository;
    private LanguageLiteracyRepository languageLiteracyRepository;
    private PlaygroundRepository playgroundRepository;
    private RecreationRepository recreationRepository;
    private ShoppingRepository shoppingRepository;
    private SportsFieldsRepository sportsFieldsRepository;

    public MapsViewModel(Application application) {
        super(application);

        artworkRepository = new ArtworkRepository(application);
        coffeeShopRepository = new CoffeeShopRepository(application);
        creativeIndustriesRepository = new CreativeIndustriesRepository(application);
        culturalVenuesRepository = new CulturalVenuesRepository(application);
        heritageInterestsRepository = new HeritageInterestsRepository(application);
        languageLiteracyRepository = new LanguageLiteracyRepository(application);
        playgroundRepository = new PlaygroundRepository(application);
        recreationRepository = new RecreationRepository(application);
        shoppingRepository = new ShoppingRepository(application);
        sportsFieldsRepository = new SportsFieldsRepository(application);
    }

    public Long addArtwork(final ArtPublic.Feature feature) {
        final Artwork artwork;
        final Long newId;

        artwork = artworkRepository.createArtwork();
        artwork.setName(feature.getProperties().getName());
        artwork.setLatitude(feature.getGeometry().getCoordinates()[1]);
        artwork.setLongitude(feature.getGeometry().getCoordinates()[0]);
        artwork.setDescription(feature.getProperties().getDescriptn());
        newId = artworkRepository.addArtwork(artwork);

        Log.i(TAG, "New bookmark " + newId + " added to the database.");

        return newId;
    }

    public Long addCoffee(final Coffee.Feature feature) {
        final CoffeeShop coffeeShop;
        final Long newId;

        coffeeShop = coffeeShopRepository.createCoffeeShop();
        coffeeShop.setName(feature.getProperties().getName());
        coffeeShop.setLatitude(feature.getGeometry().getCoordinates()[1]);
        coffeeShop.setLongitude(feature.getGeometry().getCoordinates()[0]);
        coffeeShop.setDescription(feature.getProperties().getAddress());
        newId = coffeeShopRepository.addCoffeeShop(coffeeShop);

        Log.i(TAG, "New bookmark " + newId + " added to the database.");

        return newId;
    }

    public Long addCreativeIndustry(final CreativeIndustry.Feature feature) {
        final CreativeIndustries creativeIndustry;
        final Long newId;

        creativeIndustry = creativeIndustriesRepository.createCreativeIndustries();
        creativeIndustry.setName(feature.getProperties().getName());
        creativeIndustry.setLatitude(feature.getGeometry().getCoordinates()[1]);
        creativeIndustry.setLongitude(feature.getGeometry().getCoordinates()[0]);
        creativeIndustry.setDescription(feature.getProperties().getDescriptn());
        newId = creativeIndustriesRepository.addCreativeIndustries(creativeIndustry);

        Log.i(TAG, "New bookmark " + newId + " added to the database.");

        return newId;
    }

    public Long addCulturalVenue(final CulturalVenue.Feature feature) {
        final CulturalVenues culturalVenues;
        final Long newId;

        culturalVenues = culturalVenuesRepository.createCulturalVenues();
        culturalVenues.setName(feature.getProperties().getName());
        culturalVenues.setLatitude(feature.getGeometry().getCoordinates()[1]);
        culturalVenues.setLongitude(feature.getGeometry().getCoordinates()[0]);
        culturalVenues.setDescription(feature.getProperties().getDescriptn());
        newId = culturalVenuesRepository.addCulturalVenues(culturalVenues);

        Log.i(TAG, "New bookmark " + newId + " added to the database.");

        return newId;
    }

    public Long addHeritageInterest(final HeritageInterest.Feature feature) {
        final HeritageInterests heritageInterests;
        final Long newId;

        heritageInterests = heritageInterestsRepository.createHeritageInterests();
        heritageInterests.setName(feature.getProperties().getName());
        heritageInterests.setLatitude(feature.getGeometry().getCoordinates()[1]);
        heritageInterests.setLongitude(feature.getGeometry().getCoordinates()[0]);
        heritageInterests.setDescription(feature.getProperties().getAddress());
        newId = heritageInterestsRepository.addHeritageInterests(heritageInterests);

        Log.i(TAG, "New bookmark " + newId + " added to the database.");

        return newId;
    }

    public Long addLiteracy(final Literacy.Feature feature) {
        final LanguageLiteracy languageLiteracy;
        final Long newId;

        languageLiteracy = languageLiteracyRepository.createLanguageLiteracy();
        languageLiteracy.setName(feature.getProperties().getName());
        languageLiteracy.setLatitude(feature.getGeometry().getCoordinates()[1]);
        languageLiteracy.setLongitude(feature.getGeometry().getCoordinates()[0]);
        languageLiteracy.setDescription(feature.getProperties().getLocation());
        newId = languageLiteracyRepository.addLanguageLiteracy(languageLiteracy);

        Log.i(TAG, "New bookmark " + newId + " added to the database.");

        return newId;
    }

    public Long addPlaygroundData(final PlaygroundData.Feature feature) {
        final Playground playground;
        final Long newId;

        playground = playgroundRepository.createPlayground();
        playground.setName(feature.getProperties().getPARK());
        playground.setLatitude(feature.getGeometry().getCoordinates()[1]);
        playground.setLongitude(feature.getGeometry().getCoordinates()[0]);
        playground.setDescription(feature.getProperties().getTYPE());
        newId = playgroundRepository.addPlayground(playground);

        Log.i(TAG, "New bookmark " + newId + " added to the database.");

        return newId;
    }

    public Long addRecProgram(final RecProgram.Feature feature) {
        final Recreation recreation;
        final Long newId;

        recreation = recreationRepository.createRecreation();
        recreation.setName(feature.getProperties().getName());
        recreation.setLatitude(feature.getGeometry().getCoordinates()[1]);
        recreation.setLongitude(feature.getGeometry().getCoordinates()[0]);
        recreation.setDescription(feature.getProperties().getLocation());
        newId = recreationRepository.addRecreation(recreation);

        Log.i(TAG, "New bookmark " + newId + " added to the database.");

        return newId;
    }

    public Long addSportsfield(final Sportsfield.Feature feature) {
        final SportsFields sportsFields;
        final Long newId;

        sportsFields = sportsFieldsRepository.createSportsFields();
        sportsFields.setName(feature.getProperties().getPARK());
        sportsFields.setLatitude(feature.getGeometry().getCoordinates()[1]);
        sportsFields.setLongitude(feature.getGeometry().getCoordinates()[0]);
        sportsFields.setDescription(feature.getProperties().getTYPE());
        newId = sportsFieldsRepository.addSportsFields(sportsFields);

        Log.i(TAG, "New bookmark " + newId + " added to the database.");

        return newId;
    }
}
