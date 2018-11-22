package com.example.pierre.jeuxdesocit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static AccesLocal accesLocal;
    private List<String> listNomsKits;
    private ListView kitsView;
    private MyCustomAdapter adapter;
    private Button create_kit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        kitsView = findViewById(R.id.kitsList);
        create_kit = findViewById(R.id.create_new_kit);
        listNomsKits = new ArrayList<>();
        adapter = new MyCustomAdapter(listNomsKits, this);
        accesLocal = new AccesLocal(this);

        kitsView.setAdapter(adapter);
        create_kit.setText("Créer Kit");
        create_kit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ModifierKitActivity.class);
                startActivity(intent);
            }
        });


        accesLocal.ajout(new Item("De", "6"), "Munchkin");
        accesLocal.ajout(new Item("Piece", "0"), "Munchkin");
        accesLocal.ajout(new Item("Timer", "1.5"), "Un Kit Nul");

        listNomsKits.addAll(accesLocal.getListKit());
    }

}

//    getWindowManager().getDefaultDisplay().getMetrics(metrics);
//    private DisplayMetrics metrics = new DisplayMetrics();
