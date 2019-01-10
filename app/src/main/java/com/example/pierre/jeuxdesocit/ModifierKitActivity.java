package com.example.pierre.jeuxdesocit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class ModifierKitActivity extends AppCompatActivity {

    private TextView titre;
    private String nomKit;
    private List<String> lesItems;
    private ListView itemsView;
    private ListModifierKit adapter;
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
        adapter = new ListModifierKit(lesItems, nomKit, this);
        itemsView.setAdapter(adapter);

        validerKit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(ModifierKitActivity.this, MainActivity.class);
                startActivity(intent);
                ModifierKitActivity.this.finish();
            }
        });
    }
}