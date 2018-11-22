package com.example.pierre.jeuxdesocit;

public class De extends Item{

    private int valeur;

    public De() {
        super("Dé");
        valeur = 6;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public int getValeur() {
        return valeur;
    }
}
