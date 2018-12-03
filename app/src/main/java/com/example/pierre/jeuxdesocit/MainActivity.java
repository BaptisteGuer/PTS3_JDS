package com.example.pierre.jeuxdesocit;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
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

        create_kit.setText("Créer Kit");
        create_kit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                List<Item> lesItems = new ArrayList<>();
                Item item1 = new Item("ITEM TEST 1 KIT 1", "6");
                Item item2 = new Item("ITEM TEST 2 KIT 1", "0");
                lesItems.add(item1);
                lesItems.add(item2);

                List<Item> lesItems2 = new ArrayList<>();
                Item item3 = new Item("ITEM TEST 3 KIT 2", "1.5");
                Item item4 = new Item("ITEM TEST 4 KIT 2", "AZER");
                lesItems2.add(item3);
                lesItems2.add(item4);

                accesLocal.ajoutListItem(lesItems, "Munchkin");
                accesLocal.ajoutListItem(lesItems2, "Un Kit Nul");
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
                adapter.notifyDataSetChanged();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                    adapter.getFilter().filter(newText);
                    adapter.notifyDataSetChanged();
                return true;
            }
        }
        );
        searchView.setOnSearchClickListener(new SearchView.OnClickListener(){
            @Override
            public void onClick(View v) {
                searchView.clearFocus();
            }
        });
    return super.onCreateOptionsMenu(menu);
    }

}

//    getWindowManager().getDefaultDisplay().getMetrics(metrics);
//    private DisplayMetrics metrics = new DisplayMetrics();
