package com.example.pierre.jeuxdesocit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Kit> kits;
    private ListView kitsView;
    private MyCustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        kitsView = findViewById(R.id.kitsList);
        kits = new ArrayList<>();
        adapter = new MyCustomAdapter(kits, this);

        kitsView.setAdapter(adapter);

        Kit kit1 = new Kit("Kit1");
        Kit kit2 = new Kit("Kit2");
        Kit kit3 = new Kit("Kit3");
        kits.add(kit1);
        kits.add(kit2);
        kits.add(kit3);
    }

}

//    getWindowManager().getDefaultDisplay().getMetrics(metrics);
//    private DisplayMetrics metrics = new DisplayMetrics();
