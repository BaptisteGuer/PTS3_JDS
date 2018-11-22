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

    public String getNom() {
        return this.nom;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void addItem(Item item){
        this.items.add(item);
    }
}
