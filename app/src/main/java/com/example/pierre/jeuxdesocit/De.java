package com.example.pierre.jeuxdesocit;

public class De implements Item{

    private String nom;
    private String valeur;

    public De() {
        nom = "De";
        valeur = "6";
    }

    public De(String nom, String valeur) {
        this.nom = nom;
        this.valeur = valeur;
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
    public String faireAction() {
        int val = (int)(Math.random() * ((6 - 1) + 1)) + 1;
        valeur = Integer.toString(val);
        return valeur;
    }
}
