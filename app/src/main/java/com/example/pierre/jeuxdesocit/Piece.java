package com.example.pierre.jeuxdesocit;

public class Piece extends Item{

    private String valeur;

    public Piece() {
        super("Pièce", "pile");
    }

    public String faireAction() {
        double val = Math.random();
        if(val<0.5){
            valeur = "pile";
        } else {
            valeur = "face";
        }
        return valeur;
    }
}