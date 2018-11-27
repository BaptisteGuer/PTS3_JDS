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

    public void ajoutItem(Item item, String nomKit) {
        bd = accesBD.getWritableDatabase();
        String req = "INSERT INTO Item (nom, valeur, nomKit) VALUES ";
        req += "(\"" + item.getNom() + "\",\"" + item.getValeur() + "\",\"" + nomKit + "\")";
        bd.execSQL(req);
    }

    public void ajoutListItem(List<Item> lesItems, String nomKit) {
        bd = accesBD.getWritableDatabase();
        String req = "INSERT INTO Item (nom, valeur, nomKit) VALUES ";
        for (Item item : lesItems) {
            req += "(\"" + item.getNom() + "\",\"" + item.getValeur() + "\",\"" + nomKit + "\")";
            if(!lesItems.get(lesItems.size() - 1).equals(item)){
                req += ",";
            }
        }
        req += ";";
        bd.execSQL(req);
    }

    public void supprimerItem(Item item, String nomKit) {
        bd = accesBD.getWritableDatabase();
        String req = "DELETE FROM Item WHERE nom=\"" + item + "\" AND nomKit=\"" + nomKit + "\";";
        bd.execSQL(req);
    }

    public void supprimerListItem(List<Item> lesItems, String nomKit) {
        bd = accesBD.getWritableDatabase();
        for (Item item : lesItems) {
            String req = "DELETE FROM Item WHERE nom=\"" + item + "\" AND nomKit=\"" + nomKit + "\";";
            bd.execSQL(req);
        }
    }

    public void supprimerKit(String nomKit) {
        bd = accesBD.getWritableDatabase();
        String req = "DELETE FROM Item WHERE nomKit=\"" + nomKit + "\";";
        bd.execSQL(req);
    }

}
