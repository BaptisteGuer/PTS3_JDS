package com.example.pierre.jeuxdesocit;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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

    public void ajout(Item item, String nomKit) {
        bd = accesBD.getWritableDatabase();
        String req = "INSERT INTO Item (nom, valeur, nomKit) VALUES ";
        req += "(\"" + item.getNom() + "\",\"" + item.getValeur() + "\",\"" + nomKit + "\")";
        bd.execSQL(req);
    }


    public Item recupDernier(String nomKit) {
        bd = accesBD.getReadableDatabase();
        Item item = null;
        String req = "SELECT * FROM Item WHERE \"" + nomKit + "\"= nomKit";
        Cursor cursor = bd.rawQuery(req, null);
        cursor.moveToLast();
        if (!cursor.isAfterLast()) {
            String nom = cursor.getString(1);
            String valeur = cursor.getString(2);
            item = new Item(nom, valeur);
        }
        cursor.close();
        return item;
    }


    public List<String> getListKit() {
        bd = accesBD.getReadableDatabase();
        List<String> stringList = new ArrayList<>();
        String req = "SELECT DISTINCT nomKit FROM Item";
        Cursor cursor = bd.rawQuery(req, null);

        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            String s = cursor.getString(0);
            stringList.add(s);
        }
        
        cursor.close();
        return stringList;
    }


}
