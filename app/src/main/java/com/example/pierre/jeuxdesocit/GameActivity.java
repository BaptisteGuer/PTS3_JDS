package com.example.pierre.jeuxdesocit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private String nomKit;
    private Button augmenter;
    private Button baisser;
    private ImageButton itemDroite;
    private ImageButton itemGauche;
    private Button lancer;
    private EditText entrerScore;
    private TextView nomJoueurTv;
    private TextView nomItemTv;
    private TextView score;
    private TextView resTv;
    private Joueur joueurSelectionne;
    private int positionItemSelectionne;
    private int nbItems;
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
        itemDroite = findViewById(R.id.itemDroite);
        itemGauche = findViewById(R.id.itemGauche);
        lancer = findViewById(R.id.lancer);
        nomJoueurTv = findViewById(R.id.JoueurChoisi);
        nomJoueurTv.setText(joueurSelectionne.getNom());
        resTv = findViewById(R.id.resTv);

        augmenter.setOnClickListener(this);
        baisser.setOnClickListener(this);
        itemDroite.setOnClickListener(this);
        itemGauche.setOnClickListener(this);
        lancer.setOnClickListener(this);
        
        lesItems = new ArrayList<>();
//        for (String nomItem : MainActivity.accesLocal.getListItems(nomKit)) {
//            lesItems.add(MainActivity.accesLocal.getItem(nomItem, nomKit));
//        }
        lesItems.add(new De());
        lesItems.add(new Piece());
        lesItems.add(new Item("ITEM TEST 2", ""));
        positionItemSelectionne = 0;
//        nbItems = lesItems.size();
        nbItems = 3;
        nomItemTv = findViewById(R.id.itemChoisi);
        nomItemTv.setText(lesItems.get(positionItemSelectionne).getNom());
    }


    @Override
    public void onClick(View v) {
        {
            if(v.getId()==R.id.augmenter){
                try {
                    joueurSelectionne.setScore(joueurSelectionne.getScore() + Integer.parseInt(entrerScore.getText().toString()));
                    score.setText(Integer.toString(joueurSelectionne.getScore()));
                }catch (NumberFormatException e){
                    entrerScore.setError("Veuillez entrer un nombre");
                }
            }
            if(v.getId()==R.id.baisser){
                    try {
                        joueurSelectionne.setScore(joueurSelectionne.getScore() - Integer.parseInt(entrerScore.getText().toString()));
                        score.setText(Integer.toString(joueurSelectionne.getScore()));
                    }catch (NumberFormatException e){
                        entrerScore.setError("Veuillez entrer un nombre");
                    }
            }
            if (v.getId()==R.id.lancer){
                resTv.setText(lesItems.get(positionItemSelectionne).faireAction());
            }
            if (v.getId()==R.id.joueurDroite){
                //On passe au prochain joueur droite
            }
            if(v.getId()==R.id.joueurGauche){
                //On passe au prochain joueur gauche
            }
            if(v.getId()==R.id.itemDroite){
                if(positionItemSelectionne == nbItems-1){
                    positionItemSelectionne = 0;
                } else {
                    positionItemSelectionne++;
                }
                nomItemTv.setText(lesItems.get(positionItemSelectionne).getNom());
            }
            if(v.getId()==R.id.itemGauche){
                if(positionItemSelectionne == 0){
                    positionItemSelectionne = nbItems-1;
                } else {
                    positionItemSelectionne--;
                }
                nomItemTv.setText(lesItems.get(positionItemSelectionne).getNom());
            }
        }
    }
}

