package com.cameronlay.comp3617.termproject.repository;

import android.content.Context;

import java.util.List;

import com.cameronlay.comp3617.termproject.db.CoffeeShopDAO;
import com.cameronlay.comp3617.termproject.db.ProjectDatabase;
import com.cameronlay.comp3617.termproject.model.CoffeeShop;

public class CoffeeShopRepository {

    private ProjectDatabase db;
    private CoffeeShopDAO coffeeShopDAO;

    public CoffeeShopRepository(Context context) {
        db = ProjectDatabase.Companion.getInstance(context);
        coffeeShopDAO = db.coffeeShopDAO();
    }

    public Long addCoffeeShop(CoffeeShop coffeeShop) {
        final Long newId;

        newId = coffeeShopDAO.insertCoffeeShop(coffeeShop);
        coffeeShop.setId(newId);

        return newId;
    }

    public void clearData() {
        coffeeShopDAO.nukeTable();
    }

    public CoffeeShop createCoffeeShop() {
        return new CoffeeShop();
    }

    public List<CoffeeShop> getAllCoffeeShop() {
        return coffeeShopDAO.getCoffeeShops();
    }
}

