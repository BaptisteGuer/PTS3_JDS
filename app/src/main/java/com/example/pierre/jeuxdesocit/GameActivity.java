package com.example.pierre.jeuxdesocit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private String nomKit;
    private Button augmenter;
    private Button baisser;
    private EditText entrerScore;
    private TextView nomJoueurTv;
    private TextView score;
    private Joueur joueurSelectionne;
    private Item itemSelectionne;
    private List<Item> lesItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        joueurSelectionne = new Joueur("Joueur1(test)"); //Joueur de test en attente de la liste des joueurs
        nomKit = getIntent().getStringExtra("nomKit");
        score = findViewById(R.id.score);
        score.setText(Integer.toString(joueurSelectionne.getScore()));
        augmenter = findViewById(R.id.augmenter);
        entrerScore = findViewById(R.id.entrerScore);
        baisser = findViewById(R.id.baisser);
        nomJoueurTv = findViewById(R.id.JoueurChoisi);
        nomJoueurTv.setText(joueurSelectionne.getNom());

        lesItems = new ArrayList<>();
        for (String nomItem : MainActivity.accesLocal.getListItems(nomKit)) {
            lesItems.add(MainActivity.accesLocal.getItem(nomItem, nomKit));
        }

        augmenter.setOnClickListener(this);
        baisser.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        {
            if(v.getId()==R.id.augmenter){
                joueurSelectionne.setScore(joueurSelectionne.getScore()+Integer.parseInt(entrerScore.getText().toString()));
                score.setText(Integer.toString(joueurSelectionne.getScore()));
            }
            if(v.getId()==R.id.baisser){
                joueurSelectionne.setScore(joueurSelectionne.getScore()-Integer.parseInt(entrerScore.getText().toString()));
                score.setText(Integer.toString(joueurSelectionne.getScore()));
            }
            if (v.getId()==R.id.lancer){
               //Quand on appuie sur le gros boutton pour lancer l'événement de l'item selectionné
            }
            if (v.getId()==R.id.joueurDroite){
                //On passe au prochain joueur droite
            }
            if(v.getId()==R.id.joueurGauche){
                //On passe au prochain joueur gauche
            }
            if(v.getId()==R.id.itemDroite){
                //Prochain item à droite

            }
            if(v.getId()==R.id.itemGauche){
                //Prochain item à gauche
            }
        }
    }
}

