package com.example.pierre.jeuxdesocit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static AccesLocal accesLocal;
    public static List<String> listNomsKits;
    public List<String> templist;
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
        accesLocal = new AccesLocal(this);

        listNomsKits.addAll(accesLocal.getListKits());
        adapter = new MyCustomAdapter(listNomsKits, this);
        kitsView.setAdapter(adapter);

        List<Item> lesItems = new ArrayList<>();
        lesItems.add(new Item("ITEM TEST 1", ""));
        lesItems.add(new Item("ITEM TEST 2", ""));
        lesItems.add(new Item("ITEM TEST 3", ""));
        lesItems.add(new Item("ITEM TEST 4", ""));
        lesItems.add(new Item("ITEM TEST 5", ""));
        accesLocal.ajoutListItem(lesItems, "");

        create_kit.setText("Créer Kit");
        create_kit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreerKitActivity.class);
                startActivity(intent);
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search,menu);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.menuSearch));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
               templist = new ArrayList<>();
                for (String temp : listNomsKits){
                    if (temp.toLowerCase().startsWith(newText.toLowerCase())){
                        templist.add(temp);
                    }
                }
                adapter = new MyCustomAdapter(templist, MainActivity.this);
                kitsView.setAdapter(adapter);
                return true;
            }
        });
    return super.onCreateOptionsMenu(menu);
    }

}
