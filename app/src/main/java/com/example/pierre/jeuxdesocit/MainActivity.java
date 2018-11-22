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

    private ArrayList<Kit> kits;
    private ListView kitsView;
    private MyCustomAdapter adapter;
    private Button create_kit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        kitsView = findViewById(R.id.kitsList);
        create_kit = findViewById(R.id.create_new_kit);
        kits = new ArrayList<>();
        adapter = new MyCustomAdapter(kits, this);


        kitsView.setAdapter(adapter);
        create_kit.setText("Créer Kit");
        create_kit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Kit.class);
                startActivity(intent);
            }
        });


        Kit kit1 = new Kit("Kit1");
        Kit kit2 = new Kit("Kit2");
        Kit kit3 = new Kit("Kit3");
        Kit kit4 = new Kit("Kit4");
        Kit kit5 = new Kit("Kit5");
        Kit kit6 = new Kit("Kit6");
        Kit kit7 = new Kit("Kit7");
        Kit kit8 = new Kit("Kit8");
        Kit kit9 = new Kit("Kit9");
        Kit kit10 = new Kit("Kit10");
        Kit kit11= new Kit("Kit11");
        Kit kit12 = new Kit("Kit12");
        kits.add(kit1);
        kits.add(kit2);
        kits.add(kit3);
        kits.add(kit4);
        kits.add(kit5);
        kits.add(kit6);
        kits.add(kit7);
        kits.add(kit8);
        kits.add(kit9);
        kits.add(kit10);
        kits.add(kit11);
        kits.add(kit12);

        kit1.addItem(new De());

    }




}

//    getWindowManager().getDefaultDisplay().getMetrics(metrics);
//    private DisplayMetrics metrics = new DisplayMetrics();
