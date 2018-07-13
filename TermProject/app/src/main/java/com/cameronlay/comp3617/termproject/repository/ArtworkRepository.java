package com.cameronlay.comp3617.termproject.repository;

import android.content.Context;

import java.util.List;

import com.cameronlay.comp3617.termproject.db.ArtworkDAO;
import com.cameronlay.comp3617.termproject.db.ProjectDatabase;
import com.cameronlay.comp3617.termproject.model.Artwork;

public class ArtworkRepository {
    private ProjectDatabase db;
    private ArtworkDAO artworkDAO;

    public ArtworkRepository(Context context) {
        db = ProjectDatabase.Companion.getInstance(context);
        artworkDAO = db.artworkDAO();
    }

    public Long addArtwork(Artwork artwork) {
        final Long newId;

        newId = artworkDAO.insertArtwork(artwork);
        artwork.setId(newId);

        return newId;
    }

    public void clearData() {
        artworkDAO.nukeTable();
    }

    public Artwork createArtwork() {
        return new Artwork();
    }

    public List<Artwork> getAllArtwork() {
        return artworkDAO.getArtwork();
    }
}
