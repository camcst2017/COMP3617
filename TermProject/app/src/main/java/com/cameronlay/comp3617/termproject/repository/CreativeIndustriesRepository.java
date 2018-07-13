package com.cameronlay.comp3617.termproject.repository;

import android.content.Context;

import java.util.List;

import com.cameronlay.comp3617.termproject.db.CreativeIndustriesDAO;
import com.cameronlay.comp3617.termproject.db.ProjectDatabase;
import com.cameronlay.comp3617.termproject.model.CreativeIndustries;

public class CreativeIndustriesRepository {
    private ProjectDatabase db;
    private CreativeIndustriesDAO creativeIndustriesDAO;

    public CreativeIndustriesRepository(Context context) {
        db = ProjectDatabase.Companion.getInstance(context);
        creativeIndustriesDAO = db.creativeIndustriesDAO();
    }

    public Long addCreativeIndustries(CreativeIndustries creativeIndustries) {
        final Long newId;

        newId = creativeIndustriesDAO.insertCreativeIndustries(creativeIndustries);
        creativeIndustries.setId(newId);

        return newId;
    }

    public void clearData() {
        creativeIndustriesDAO.nukeTable();
    }

    public CreativeIndustries createCreativeIndustries() {
        return new CreativeIndustries();
    }

    public List<CreativeIndustries> getAllCreativeIndustries() {
        return creativeIndustriesDAO.getCreativeIndustries();
    }
}

