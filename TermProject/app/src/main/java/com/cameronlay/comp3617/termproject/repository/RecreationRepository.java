package com.cameronlay.comp3617.termproject.repository;

import android.content.Context;

import java.util.List;

import com.cameronlay.comp3617.termproject.db.RecreationDAO;
import com.cameronlay.comp3617.termproject.db.ProjectDatabase;
import com.cameronlay.comp3617.termproject.model.Recreation;

public class RecreationRepository {
    private ProjectDatabase db;
    private RecreationDAO recreationDAO;

    public RecreationRepository(Context context) {
        db = ProjectDatabase.Companion.getInstance(context);
        recreationDAO = db.recreationDAO();
    }

    public Long addRecreation(Recreation recreation) {
        final Long newId;

        newId = recreationDAO.insertRecreation(recreation);
        recreation.setId(newId);

        return newId;
    }

    public void clearData() {
        recreationDAO.nukeTable();
    }

    public Recreation createRecreation() {
        return new Recreation();
    }

    public List<Recreation> getAllRecreation() {
        return recreationDAO.getRecreation();
    }
}

