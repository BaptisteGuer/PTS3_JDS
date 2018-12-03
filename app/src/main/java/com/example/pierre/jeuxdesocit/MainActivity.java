package com.example.pierre.jeuxdesocit;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static AccesLocal accesLocal;
    private List<String> listNomsKits;
    private ListView kitsView;
    private MyCustomAdapter adapter;
    private Button create_kit;
    private MenuItem item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        kitsView = findViewById(R.id.kitsList);
        create_kit = findViewById(R.id.create_new_kit);
        item = findViewById(R.id.menuSearch);
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
        accesLocal.ajoutListItem(lesItems, "Les items");

        create_kit.setText("Créer Kit");
        create_kit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                List<Item> lesItems2 = new ArrayList<>();
                lesItems2.add(new Item("ITEM TEST 4", ""));
                accesLocal.ajoutListItem(lesItems2, "LoupGarou");
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_search,menu);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.menuSearch));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                    adapter.getFilter().filter(newText);

                return true;
            }
        });
    return super.onCreateOptionsMenu(menu);
    }

}

//    getWindowManager().getDefaultDisplay().getMetrics(metrics);
//    private DisplayMetrics metrics = new DisplayMetrics();
