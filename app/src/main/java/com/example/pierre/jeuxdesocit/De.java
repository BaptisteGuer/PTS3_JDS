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
        double res;
        res =  Math.floor(Math.random() * (Integer.parseInt(this.valeur) - 1 + 1)) + 1;
        return ""+res;
    }
}
