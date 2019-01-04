package com.example.pierre.jeuxdesocit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ParametrageActivity extends AppCompatActivity {

    private String nomKit;
    private Button jouerBtn;
    private TextView nomKitTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametrage);

        nomKit = getIntent().getStringExtra("nomKit");
        jouerBtn = findViewById(R.id.btnJouer);
        nomKitTv = findViewById(R.id.nomKitTv);
        nomKitTv.setText(nomKit);

        jouerBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ParametrageActivity.this, GameActivity.class);
                intent.putExtra("nomKit", nomKit);
                startActivity(intent);
            }
        });

    }
}
