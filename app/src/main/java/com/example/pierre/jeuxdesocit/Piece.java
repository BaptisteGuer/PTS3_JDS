package com.example.pierre.jeuxdesocit;

public class Piece implements Item{

    private String nom;
    private String valeur;
    private int image;

    public Piece() {
        nom = "Piece";
        valeur = "Pile";
        image = R.drawable.piece;
    }

    public Piece(String nom, String valeur) {
        this.nom = nom;
        this.valeur = valeur;
        this.image = R.drawable.piece;
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
        double val = Math.random();
        if(val<0.5){
            valeur = "Pile";
        } else {
            valeur = "Face";
        }
        return valeur;
    }
}