package com.example.pierre.jeuxdesocit;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class AccesLocal {

    private String nomBase = "bdPTS3.sqlite";
    private Integer versionBase = 1;
    private MySQLiteOpenHelper accesBD;
    private SQLiteDatabase bd;

    public AccesLocal(Context context) {
        accesBD = new MySQLiteOpenHelper(context, nomBase, null, versionBase);
    }

    public List<String> getListKits() {
        bd = accesBD.getReadableDatabase();
        List<String> stringList = new ArrayList<>();
        String req = "SELECT DISTINCT nomKit FROM Item WHERE nomKit <> ''";
        Cursor cursor = bd.rawQuery(req, null);

        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            String s = cursor.getString(0);
            stringList.add(s);
        }
        cursor.close();
        return stringList;
    }

    public List<String> getListItems() {
        bd = accesBD.getReadableDatabase();
        List<String> stringList = new ArrayList<>();
        String req = "SELECT DISTINCT nom FROM Item";
        Cursor cursor = bd.rawQuery(req, null);

        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            String s = cursor.getString(0);
            stringList.add(s);
        }

        cursor.close();
        return stringList;
    }

    public List<String> getListItems(String nomKit) {
        bd = accesBD.getReadableDatabase();
        List<String> stringList = new ArrayList<>();
        String req = "SELECT nom FROM Item WHERE nomKit='" + nomKit + "'";
        Cursor cursor = bd.rawQuery(req, null);

        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            String s = cursor.getString(0);
            stringList.add(s);
        }

        cursor.close();
        return stringList;
    }

    public Item getItem(String nomItem, String nomKit) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        bd = accesBD.getReadableDatabase();
        String req = "SELECT DISTINCT nom, valeur FROM Item WHERE nom='" + nomItem + "' AND nomKit='" + nomKit + "'";
        Cursor cursor = bd.rawQuery(req, null);
        cursor.moveToFirst();
        String TypeItem = cursor.getString(0);
        Class<?> clazz = Class.forName("com.example.pierre.jeuxdesocit." + TypeItem);
        Constructor<?> constructor = clazz.getConstructor(String.class, String.class);
        Item item = (Item) constructor.newInstance(cursor.getString(0), cursor.getString(1));
        return item;
    }

    public boolean getKit(String nomKit) {
        bd = accesBD.getReadableDatabase();
        List<String> stringList = new ArrayList<>();
        String req = "SELECT nomKit FROM Item WHERE nomKit=\"" + nomKit + "\"";
        Cursor cursor = bd.rawQuery(req, null);
        cursor.moveToFirst();
        if (cursor.isLast()) return true;
        return false;
    }

    public void ajoutItem(Item item, String nomKit) {
        Log.e("yuiop", item.getClass().getName());
        bd = accesBD.getWritableDatabase();
        String req = "INSERT INTO Item (nom, valeur, nomKit) VALUES ";
        item.getClass().getName();

        req += "(\"" + item.getNom() + "\",\"" + item.getValeur() + "\",\"" + nomKit + "\")";
        bd.execSQL(req);
    }

    public void ajoutListItem(List<Item> lesItems, String nomKit) {
        bd = accesBD.getWritableDatabase();
        String req = "INSERT INTO Item (nom, valeur, nomKit) VALUES ";
        for (Item item : lesItems) {
            req += "(\"" + item.getNom() + "\",\"" + item.getValeur() + "\",\"" + nomKit + "\")";
            if (!lesItems.get(lesItems.size() - 1).equals(item)) {
                req += ",";
            }
        }
        req += ";";
        bd.execSQL(req);
        bd.close();
    }

    public void supprimerItem(String nomItem, String nomKit) {
        bd = accesBD.getWritableDatabase();
        String req = "DELETE FROM Item WHERE nom=\"" + nomItem + "\" AND nomKit=\"" + nomKit + "\";";
        bd.execSQL(req);
        bd.close();
    }

    public void supprimerKit(String nomKit) {
        bd = accesBD.getWritableDatabase();
        String req = "DELETE FROM Item WHERE nomKit=\"" + nomKit + "\";";
        bd.execSQL(req);
        bd.close();
    }

    //save un joueur dans la bdd
    public void ajoutJoueur(Joueur joueur, String nomKit) {

        bd = accesBD.getWritableDatabase();
        String req = "INSERT INTO Joueur (nom, point, couleur, nomKit) VALUES ";

        req += "(\"" + joueur.getNom() + "\"," + joueur.getScore() + ",\"" + joueur.getCouleur() + "\", \"" + nomKit + "\")";
        bd.execSQL(req);
        bd.close();
    }

    //get joueur bdd
    public List<Joueur> getJoueurs(String nomKit) {
        bd = accesBD.getReadableDatabase();

        List<Joueur> joueurList = new ArrayList<>();
        String req = "SELECT nom, point, couleur FROM Joueur WHERE nomKit='" + nomKit + "'";
        Cursor cursor = bd.rawQuery(req, null);

        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            String s = cursor.getString(0);
            int p = cursor.getInt(1);
            String c = cursor.getString(2);
            Joueur j = new Joueur(s, p, c);
            joueurList.add(j);
        }

        cursor.close();
        return joueurList;
    }

    public void supprimerJoueur(String nomKit) {
        bd = accesBD.getWritableDatabase();
        String req = "DELETE FROM Joueur WHERE nomKit=\"" + nomKit + "\";";
        bd.execSQL(req);
        bd.close();
    }

    public void ajoutListJoueur(List<Joueur> joueurs, String nomKit) {
        bd = accesBD.getWritableDatabase();
        String req = "INSERT INTO Joueur (nom, point, couleur, nomkit) VALUES ";
        for (Joueur joueur : joueurs) {
            req += "(\"" + joueur.getNom() + "\"," + joueur.getScore() + ",\"" + joueur.getCouleur() + "\", \"" + nomKit + "\")";
            if (!joueurs.get(joueurs.size() - 1).equals(joueur)) {
                req += ",";
            }
        }
        req += ";";
        bd.execSQL(req);
        bd.close();
    }

}