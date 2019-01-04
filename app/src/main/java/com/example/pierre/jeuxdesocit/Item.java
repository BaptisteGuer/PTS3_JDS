package com.example.pierre.jeuxdesocit;


public class Item {

    private String nom;
    private String valeur;


    public Item(String nom, String valeur){
        this.nom = nom;
        this.valeur = valeur;
    }

    public String getNom() {
        return nom;
    }

    public String getValeur() {
        return valeur;
    }

    public String faireAction() {
        return "je suis un item";
    }
}
