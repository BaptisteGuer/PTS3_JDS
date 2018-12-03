package com.example.pierre.jeuxdesocit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ModifierKitActivity extends AppCompatActivity {

    private TextView titre;
    private String nomKit;
    private List<String> lesItems;
    private ListView itemsView;
    private MyCustomAdapterItems adapter;
    private Button validerKit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_kit);

        titre = findViewById(R.id.nomKit);
        nomKit = getIntent().getStringExtra("nomKit");
        titre.setText(nomKit);
        itemsView = findViewById(R.id.listItems);
        validerKit = findViewById(R.id.validerKit);
        lesItems = MainActivity.accesLocal.getListItems();
        adapter = new MyCustomAdapterItems(lesItems, nomKit, this);
        itemsView.setAdapter(adapter);

        validerKit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                // afficher les items du kit validé
                List<String> test = MainActivity.accesLocal.getListItems(nomKit);
                for (String item:test) {
                    Log.e("azerty","" + item + " dans " + nomKit);
                }

                Intent intent = new Intent(ModifierKitActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
