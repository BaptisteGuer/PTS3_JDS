package com.example.pierre.jeuxdesocit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

public class ModifierKitActivity extends AppCompatActivity {
    public TextView titre;
    public static AccesLocal accesLocal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_kit);

        titre = findViewById(R.id.titreKit);
        String nomKit = getIntent().getStringExtra("nomKit");
        titre.setText(nomKit);


        accesLocal = new AccesLocal(this);


        Item itemTest = new Item("De", "6");

        accesLocal.ajout(itemTest, "kit1");
        accesLocal.ajout(itemTest, "kit2");
        accesLocal.ajout(itemTest, "kit3");
        accesLocal.ajout(itemTest, "kit4");
        accesLocal.ajout(itemTest, "kit5");
        accesLocal.ajout(itemTest, "kit6");
        accesLocal.ajout(itemTest, "kit1");
        accesLocal.ajout(itemTest, "kit2");

        List<String> list = accesLocal.getListKit();
        for (String unString:list) {
            Log.i("LLLLLLLLL", unString);
        }



        Item item = accesLocal.recupDernier("kit1");

        titre.setText(item.getNom());


    }
}
