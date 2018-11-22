package com.example.pierre.jeuxdesocit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

public class ModifierKitActivity extends AppCompatActivity {
    public TextView titre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_kit);

        titre = findViewById(R.id.titreKit);
        String nomKit = getIntent().getStringExtra("nomKit");
        titre.setText(nomKit);
    }
}
