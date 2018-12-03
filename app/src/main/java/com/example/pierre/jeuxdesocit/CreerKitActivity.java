package com.example.pierre.jeuxdesocit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CreerKitActivity extends AppCompatActivity {

    private TextView titre;
    private String nomKit;
    private List<String> lesItems;
    private ListView itemsView;
    private MyCustomAdapterItems2 adapter;
    private Button validerKit;
    public static List<String> listItemsAAjouter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creer_kit);

        titre = findViewById(R.id.nomKit);
        itemsView = findViewById(R.id.listItems);
        validerKit = findViewById(R.id.validerKit);

        listItemsAAjouter = new ArrayList<>();
        lesItems = MainActivity.accesLocal.getListItems();
        adapter = new MyCustomAdapterItems2(lesItems, this);
        itemsView.setAdapter(adapter);

        validerKit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                nomKit = titre.getText().toString();

                if(MainActivity.accesLocal.kitExiste(nomKit)){
                    // dire que ce nom est déja prit
                }

                for (String unItem : listItemsAAjouter) {
                    MainActivity.accesLocal.ajoutItem(new Item(unItem,""), nomKit);
                }

                List<String> test = MainActivity.accesLocal.getListItems(nomKit);
                for (String item : test) {
                    Log.e("azerty","" + item + " dans " + nomKit);
                }
                Intent intent = new Intent(CreerKitActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
