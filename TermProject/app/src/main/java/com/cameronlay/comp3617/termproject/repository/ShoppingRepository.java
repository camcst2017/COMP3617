package com.cameronlay.comp3617.termproject.repository;

import android.content.Context;

import java.util.List;

import com.cameronlay.comp3617.termproject.db.ShoppingDAO;
import com.cameronlay.comp3617.termproject.db.ProjectDatabase;
import com.cameronlay.comp3617.termproject.model.Shopping;

public class ShoppingRepository {
    private ProjectDatabase db;
    private ShoppingDAO shoppingDAO;

    public ShoppingRepository(Context context) {
        db = ProjectDatabase.Companion.getInstance(context);
        shoppingDAO = db.shoppingDAO();
    }

    public Long addShopping(Shopping shopping) {
        final Long newId;

        newId = shoppingDAO.insertShopping(shopping);
        shopping.setId(newId);

        return newId;
    }

    public Shopping createShopping() {
        return new Shopping();
    }

    public List<Shopping> getAllShopping() {
        return shoppingDAO.getShopping();
    }
}

