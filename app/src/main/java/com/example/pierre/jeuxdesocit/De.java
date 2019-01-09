package com.example.pierre.jeuxdesocit;

import android.graphics.drawable.Drawable;

public class De implements Item{

    private String nom;
    private String valeur;
    private int image;

    public De() {
        nom = "De";
        valeur = "6";
        image = R.drawable.de;
    }

    public De(String nom, String valeur) {
        this.nom = nom;
        this.valeur = valeur;
        this.image = R.drawable.de;
    }

    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public String getValeur() {
        return valeur;
    }

    @Override
    public int getImage() {
        return image;
    }

    @Override
    public String faireAction() {
        int val = (int)(Math.random() * ((6 - 1) + 1)) + 1;
        valeur = Integer.toString(val);
        return valeur;
    }
}
