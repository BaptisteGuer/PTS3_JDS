package com.example.pierre.jeuxdesocit;


public class De extends Item{

    private String valeur;

    public De() {
        super("Dé", "6");
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
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
