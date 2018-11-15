package com.example.pierre.jeuxdesocit;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Iterator;


public class MainActivity extends AppCompatActivity {

    ListView kitsView;
   public static int itemSelec;
    public static ArrayList<Kit> kits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        kitsView = (ListView) findViewById(R.id.kitsList);
        kits = new ArrayList<Kit>();
        Kit kit1 = new Kit("Kit1");
        Kit kit2= new Kit("Kit2");
        Kit kit3= new Kit("Kit3");
        kits.add(kit1);
        kits.add(kit2);
        kits.add(kit3);
        ArrayAdapter<Kit> adapter = new ArrayAdapter<Kit>(this,android.R.layout.simple_list_item_1, kits);
        kitsView.setAdapter(adapter);
        kitsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                itemSelec = 0;
                ChangePage(position);
                itemSelec = position;
            }
        });
    }

    public void ChangePage(int position){
        Intent intent = null ;
        switch (position) {
            case 0:
                intent = new Intent(getBaseContext(), ParamActivity.class);
                break;
            case 1:
                 intent = new Intent(getBaseContext(), ParamActivity.class);
                break;
            case 2:
                intent = new Intent(getBaseContext(), ParamActivity.class);
                break;
        }
        if(intent != null) {
            startActivity(intent);
        }

    }

    public static String getItemSelec(){
        return kits.get(itemSelec).getNom();
    }



}
