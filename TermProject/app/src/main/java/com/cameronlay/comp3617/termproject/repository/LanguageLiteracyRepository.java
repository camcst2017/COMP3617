package com.cameronlay.comp3617.termproject.repository;

import android.content.Context;

import java.util.List;

import com.cameronlay.comp3617.termproject.db.LanguageLiteracyDAO;
import com.cameronlay.comp3617.termproject.db.ProjectDatabase;
import com.cameronlay.comp3617.termproject.model.LanguageLiteracy;

public class LanguageLiteracyRepository {
    private ProjectDatabase db;
    private LanguageLiteracyDAO languageLiteracyDAO;

    public LanguageLiteracyRepository(Context context) {
        db = ProjectDatabase.Companion.getInstance(context);
        languageLiteracyDAO = db.languageLiteracyDAO();
    }

    public Long addLanguageLiteracy(LanguageLiteracy languageLiteracy) {
        final Long newId;

        newId = languageLiteracyDAO.insertLanguageLiteracy(languageLiteracy);
        languageLiteracy.setId(newId);

        return newId;
    }

    public void clearData() {
        languageLiteracyDAO.nukeTable();
    }

    public LanguageLiteracy createLanguageLiteracy() {
        return new LanguageLiteracy();
    }

    public List<LanguageLiteracy> getAllLanguageLiteracy() {
        return languageLiteracyDAO.getLanguageLiteracy();
    }
}

