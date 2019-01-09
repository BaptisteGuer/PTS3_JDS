package com.example.pierre.jeuxdesocit;

public class Carte implements Item {

    private String nom;
    private String valeur;
    private int image;

    public Carte() {
        nom = "Carte";
        valeur = "";
        image = R.drawable.carte;
    }

    public Carte(String nom, String valeur) {
        this.nom = nom;
        this.valeur = valeur;
        this.image = R.drawable.carte;
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
        valeur = "";
        int val = (int) (Math.random() * ((13 - 1) + 1)) + 1;
        valeur += String.valueOf(val);
        val = (int) (Math.random() * ((4 - 1) + 1)) + 1;
        switch (val) {
            case 1:
                valeur += " de Pique";
                break;
            case 2:
                valeur += " de Trefle";
                break;
            case 3:
                valeur += " de Carreau";
                break;
            case 4:
                valeur += " de Coeur";
        }

        return valeur;
    }
}
