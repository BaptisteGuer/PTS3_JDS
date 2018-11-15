package com.example.pierre.jeuxdesocit;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    public static int itemSelec;
    public static ArrayList<Kit> kits;
    ListView kitsView;
    DisplayMetrics metrics = new DisplayMetrics();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        kitsView = (ListView) findViewById(R.id.kitsList);
        kits = new ArrayList<Kit>();
        Kit kit1 = new Kit("Kit1");
        Kit kit2 = new Kit("Kit2");
        Kit kit3 = new Kit("Kit3");
        kits.add(kit1);
        kits.add(kit2);
        kits.add(kit3);
        ArrayAdapter<Kit> adapter = new ArrayAdapter<Kit>(this, android.R.layout.simple_list_item_1, kits);
        kitsView.setAdapter(adapter);
        kitsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                itemSelec = position;
                ChangePage(position);

            }
        });
        Log.e("largeur", kitsView.getMeasuredWidth() + "");


    }


    public String getItemSelec() {
        return String.valueOf(kits.get(itemSelec));
    }


    public void ChangePage(int position) {
        Intent intent = new Intent(getBaseContext(), ParamActivity.class);
        intent.putExtra("name", getItemSelec());
        startActivity(intent);
        }


    }


