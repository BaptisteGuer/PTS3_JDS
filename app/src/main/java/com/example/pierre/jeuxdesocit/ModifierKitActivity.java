package com.example.pierre.jeuxdesocit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ModifierKitActivity extends AppCompatActivity {
    public TextView nom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_kit);
        nom = findViewById(R.id.textView);
        nom.setText(getIntent().getStringExtra("name"));


    }
}
