package com.example.pierre.jeuxdesocit;

public class Joueur {

    private String nom;
    private int score;
    private String couleur;

    public Joueur(String nom){
        this.nom=nom;
        this.score = 0;
        this.couleur=null;
    }

    public int getScore() {
        return score;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setCouleur(String couleur) { //Pour équipes
        this.couleur = couleur;
    }
}
