package com.cameronlay.comp3617.termproject.repository;

import android.content.Context;

import java.util.List;

import com.cameronlay.comp3617.termproject.db.PlaygroundDAO;
import com.cameronlay.comp3617.termproject.db.ProjectDatabase;
import com.cameronlay.comp3617.termproject.model.Playground;

public class PlaygroundRepository {
    private ProjectDatabase db;
    private PlaygroundDAO playgroundDAO;

    public PlaygroundRepository(Context context) {
        db = ProjectDatabase.Companion.getInstance(context);
        playgroundDAO = db.playgroundDAO();
    }

    public Long addPlayground(Playground playground) {
        final Long newId;

        newId = playgroundDAO.insertPlayground(playground);
        playground.setId(newId);

        return newId;
    }

    public void clearData() {
        playgroundDAO.nukeTable();
    }

    public Playground createPlayground() {
        return new Playground();
    }

    public List<Playground> getAllPlayground() {
        return playgroundDAO.getPlayground();
    }
}

