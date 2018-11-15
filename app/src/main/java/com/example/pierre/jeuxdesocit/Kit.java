package com.example.pierre.jeuxdesocit;

import java.util.ArrayList;
import java.util.List;

public class Kit {
    public List<Item> items;
    public String nom;

    public Kit(String nom) {
        this.nom = nom;
        items = new ArrayList<Item>();
    }

    public String getNom() {
        return nom;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
    @Override
    public String toString(){
        return getNom();
    }
}
