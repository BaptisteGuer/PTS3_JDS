package com.example.pierre.jeuxdesocit;


import android.graphics.drawable.Drawable;

public interface Item {

    String nom = "";
    String valeur = "";
    int image = 0;

    String getNom() ;

    String getValeur();

    int getImage();

    String faireAction() ;
}
