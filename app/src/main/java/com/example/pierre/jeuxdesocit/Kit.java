package com.example.pierre.jeuxdesocit;

import java.util.ArrayList;
import java.util.List;

public class Kit {

    public List<Item> items;
    public String nom;

    public Kit(String nom) {
        this.nom = nom;
        items = new ArrayList<>();
    }

}
