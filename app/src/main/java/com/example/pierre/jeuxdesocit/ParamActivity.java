package com.example.pierre.jeuxdesocit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class ParamActivity extends AppCompatActivity {
    public TextView nom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config);
        nom = findViewById(R.id.textView);
        nom.setText(getIntent().getStringExtra("name"));

    }
}
