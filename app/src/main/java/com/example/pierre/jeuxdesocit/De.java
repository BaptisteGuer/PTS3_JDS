package com.example.pierre.jeuxdesocit;


public class De implements Item{

    private String valeur;

    public De(String valeur) {
        this.valeur = valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    @Override
    public String getNom() {
        return "De";
    }

    public String getValeur() {
        return valeur;
    }

    public String faireAction() {
        int val = (int)(Math.random() * ((6 - 1) + 1)) + 1;
        valeur = Integer.toString(val);
        return valeur;
    }
}
