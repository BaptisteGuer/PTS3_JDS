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
        Class<?> clazz = Class.forName("com.example.pierre.jeuxdesocit."+TypeItem);
        Constructor<?> constructor = clazz.getConstructor(String.class, String.class);
        Item item = (Item) constructor.newInstance(cursor.getString(0),cursor.getString(1));
        return item;
    }

    public boolean getKit(String nomKit){
        bd = accesBD.getReadableDatabase();
        List<String> stringList = new ArrayList<>();
        String req = "SELECT nomKit FROM Item WHERE nomKit=\"" + nomKit + "\"";
        Cursor cursor = bd.rawQuery(req, null);
        cursor.moveToFirst();
        if(cursor.isLast()) return true;
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
            if(!lesItems.get(lesItems.size() - 1).equals(item)){
                req += ",";
            }
        }
        req += ";";
        bd.execSQL(req);
    }

    public void supprimerItem(Item item, String nomKit) {
        bd = accesBD.getWritableDatabase();
        String req = "DELETE FROM Item WHERE nom=\"" + item.getNom() + "\" AND nomKit=\"" + nomKit + "\";";
        bd.execSQL(req);
    }

//    public void supprimerListItem(List<Item> lesItems, String nomKit) {
//        bd = accesBD.getWritableDatabase();
//        for (Item item : lesItems) {
//            String req = "DELETE FROM Item WHERE nom=\"" + item + "\" AND nomKit=\"" + nomKit + "\";";
//            bd.execSQL(req);
//        }
//    }

    public void supprimerKit(String nomKit) {
        bd = accesBD.getWritableDatabase();
        String req = "DELETE FROM Item WHERE nomKit=\"" + nomKit + "\";";
        bd.execSQL(req);
    }

}