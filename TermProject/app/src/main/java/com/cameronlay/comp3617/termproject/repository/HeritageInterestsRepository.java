package com.cameronlay.comp3617.termproject.repository;

import android.content.Context;

import java.util.List;

import com.cameronlay.comp3617.termproject.db.HeritageInterestsDAO;
import com.cameronlay.comp3617.termproject.db.ProjectDatabase;
import com.cameronlay.comp3617.termproject.model.HeritageInterests;

public class HeritageInterestsRepository {
    private ProjectDatabase db;
    private HeritageInterestsDAO heritageInterestsDAO;

    public HeritageInterestsRepository (Context context) {
        db = ProjectDatabase.Companion.getInstance(context);
        heritageInterestsDAO = db.heritageInterestsDAO();
    }

    public Long addHeritageInterests(HeritageInterests heritageInterests) {
        final Long newId;

        newId = heritageInterestsDAO.insertHeritageInterest(heritageInterests);
        heritageInterests.setId(newId);

        return newId;
    }

    public void clearData() {
        heritageInterestsDAO.nukeTable();
    }

    public HeritageInterests createHeritageInterests() {
        return new HeritageInterests();
    }

    public List<HeritageInterests> getAllHeritageInterests() {
        return heritageInterestsDAO.getHeritageInterests();
    }
}

