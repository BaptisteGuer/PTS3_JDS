package com.example.pierre.jeuxdesocit;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class CreerKitActivity extends AppCompatActivity {

    private RelativeLayout layout;
    private TextView titre;
    private String nomKit;
    private List<String> lesItems;
    private ListView itemsView;
    private ListCreerKit adapter;
    private Button validerKit;
    private boolean probNomKit;
    private String messageErreur;
    public static List<String> listItemsAAjouter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creer_kit);

        layout = findViewById(R.id.activity_creer_kit);
        titre = findViewById(R.id.nomKit);
        itemsView = findViewById(R.id.listItems);
        validerKit = findViewById(R.id.validerKit);

        listItemsAAjouter = new ArrayList<>();
        lesItems = MainActivity.accesLocal.getListItems();
        adapter = new ListCreerKit(lesItems, this);
        itemsView.setAdapter(adapter);
        probNomKit = false;
        messageErreur = "Il n'y a pas d'erreur";

        itemsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                InputMethodManager imm =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });

        validerKit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                nomKit = titre.getText().toString();

                if (nomKit.isEmpty()) {
                    probNomKit = true;
                    messageErreur = "Veuillez renseigner le nom du kit";
                }

                for (String unKit : MainActivity.accesLocal.getListKits()) {
                    if (unKit.equals(nomKit)) {
                        probNomKit = true;
                        messageErreur = "Le nom du kit est déjà pris";
                    }
                }

                for (int i = 0; i < nomKit.length(); i++) {
                    if (nomKit.charAt(i) == '\'' || nomKit.charAt(i) == '"' || nomKit.charAt(i) == '\\') {
                        probNomKit = true;
                        messageErreur = "Caractère interdit : ', \", \\";
                    }
                }

                if (probNomKit) {
                    titre.setError(messageErreur);
                    probNomKit = false;
                } else {
                    for (String unItem : listItemsAAjouter) {
                              Class<?> clazz = null;
                              try {
                                  clazz = Class.forName("com.example.pierre.jeuxdesocit."+unItem);
                                  Log.e("azerty",""+clazz);
                              } catch (ClassNotFoundException e) {}
                              Constructor<?> constructor = null;
                              try {
                                        constructor = clazz.getConstructor();
                              } catch (NoSuchMethodException e) {}
                              try {
                                        MainActivity.accesLocal.ajoutItem((Item) constructor.newInstance(), nomKit);
                              } catch (IllegalAccessException e) {
                                        e.printStackTrace();
                              } catch (InstantiationException e) {
                                        e.printStackTrace();
                              } catch (InvocationTargetException e) {
                                        e.printStackTrace();
                              }
                    }
                    Intent intent = new Intent(CreerKitActivity.this, MainActivity.class);
                    startActivity(intent);
                    CreerKitActivity.this.finish();
                }
            }
        });
    }
}