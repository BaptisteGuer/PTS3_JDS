package com.example.pierre.jeuxdesocit;

public class De extends Item{

    private String valeur;

    public De() {
        super("Dé", "6");
    }

    public String faireAction() {
        int val = (int)(Math.random() * ((6 - 1) + 1)) + 1;
        valeur = Integer.toString(val);
        return valeur;
    }
}
