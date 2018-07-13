package com.cameronlay.comp3617.termproject.repository;

import android.content.Context;

import java.util.List;

import com.cameronlay.comp3617.termproject.db.SportsFieldsDAO;
import com.cameronlay.comp3617.termproject.db.ProjectDatabase;
import com.cameronlay.comp3617.termproject.model.SportsFields;

public class SportsFieldsRepository {
    private ProjectDatabase db;
    private SportsFieldsDAO sportsFieldsDAO;

    public SportsFieldsRepository(Context context) {
        db = ProjectDatabase.Companion.getInstance(context);
        sportsFieldsDAO = db.sportsFieldsDAO();
    }

    public Long addSportsFields(SportsFields sportsFields) {
        final Long newId;

        newId = sportsFieldsDAO.insertSportsFields(sportsFields);
        sportsFields.setId(newId);

        return newId;
    }

    public void clearData() {
        sportsFieldsDAO.nukeTable();
    }

    public SportsFields createSportsFields() {
        return new SportsFields();
    }

    public List<SportsFields> getAllSportsFields() {
        return sportsFieldsDAO.getSportsFields();
    }
}

