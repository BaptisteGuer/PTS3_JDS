package com.example.pierre.jeuxdesocit;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class ParametrageActivity extends AppCompatActivity {

    private String nomKit;
    private Button jouerBtn;
    private TextView nomKitTv;
    private List<Joueur> lesJoueurs;
    private ListView joueursView;
    private ListJoueurs adapter;
    private Button ajouterJoueur;
    private Dialog popup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametrage);
        nomKit = getIntent().getStringExtra("nomKit");
        ajouterJoueur = findViewById(R.id.addPlayer);
        jouerBtn = findViewById(R.id.btnJouer);
        nomKitTv = findViewById(R.id.nomKitTv);
        joueursView = findViewById(R.id.joueursList);
        nomKitTv.setText(nomKit);
        lesJoueurs = MainActivity.accesLocal.getJoueurs(nomKit);
        adapter = new ListJoueurs(lesJoueurs, nomKit, this);
        joueursView.setAdapter(adapter);

        jouerBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ParametrageActivity.this, GameActivity.class);
                intent.putExtra("nomKit", nomKit);
                startActivity(intent);
            }
        });

        ajouterJoueur.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                popup = new Dialog(ParametrageActivity.this);
                ShowPopup(v);
            }
        });

    }

    public void ShowPopup (View v){
        TextView txtclose;
        Button btnValider;
        final EditText texte;
        popup.setContentView(R.layout.custom_popup_joueur);
        texte = (EditText) popup.findViewById(R.id.text);
        txtclose = (TextView) popup.findViewById(R.id.croix);
        btnValider = (Button) popup.findViewById(R.id.valider);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.dismiss();
            }
        });
        btnValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom = texte.getText().toString();
                if (nom.isEmpty()){
                    texte.setError("Veuillez entrer un nom correct");
                }else{
                for(int i=0; i<lesJoueurs.size(); i++){
                    if(lesJoueurs.get(i).getNom() == nom){
                        texte.setError("Nom déjà utilisé !");
                    }
                    else{
                        Joueur joueur = new Joueur(nom);
                        MainActivity.accesLocal.ajoutJoueur(joueur,nomKit);
                        lesJoueurs.add(joueur);
                        popup.dismiss();
                    }
                }}

            }
        });
        popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popup.show();
    }
}
