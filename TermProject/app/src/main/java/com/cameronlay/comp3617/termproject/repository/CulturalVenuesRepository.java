package com.cameronlay.comp3617.termproject.repository;

import android.content.Context;

import java.util.List;

import com.cameronlay.comp3617.termproject.db.CulturalVenuesDAO;
import com.cameronlay.comp3617.termproject.db.ProjectDatabase;
import com.cameronlay.comp3617.termproject.model.CulturalVenues;

public class CulturalVenuesRepository {
    private ProjectDatabase db;
    private CulturalVenuesDAO culturalVenuesDAO;

    public CulturalVenuesRepository(Context context) {
        db = ProjectDatabase.Companion.getInstance(context);
        culturalVenuesDAO = db.culturalVenturesDAO();
    }

    public Long addCulturalVenues(CulturalVenues culturalVenues) {
        final Long newId;

        newId = culturalVenuesDAO.insertCulturalVenue(culturalVenues);
        culturalVenues.setId(newId);

        return newId;
    }

    public void clearData() {
        culturalVenuesDAO.nukeTable();
    }

    public CulturalVenues createCulturalVenues() {
        return new CulturalVenues();
    }

    public List<CulturalVenues> getAllCulturalVenues() {
        return culturalVenuesDAO.getCulturalVenues();
    }
}

