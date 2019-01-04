package com.example.pierre.jeuxdesocit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;


public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private String nomKit;
    private Button augmenter;
    private Button baisser;
    private ImageButton itemDroite;
    private ImageButton itemGauche;
    private ImageButton joueurDroite;
    private ImageButton joueurGauche;
    private Button lancer;
    private EditText entrerScore;
    private TextView nomJoueurTv;
    private TextView nomItemTv;
    private TextView score;
    private TextView resTv;
    private int positionJoueurSelectionne;
    private int positionItemSelectionne;
    private int nbItems;
    private int nbJoueurs;
    private List<Item> lesItems;
    private List<Joueur> lesJoueurs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        nomKit = getIntent().getStringExtra("nomKit");
        augmenter = findViewById(R.id.augmenter);
        baisser = findViewById(R.id.baisser);
        joueurDroite = findViewById(R.id.joueurDroite);
        joueurGauche = findViewById(R.id.joueurGauche);
        nomJoueurTv = findViewById(R.id.JoueurChoisi);
        score = findViewById(R.id.score);
        entrerScore = findViewById(R.id.entrerScore);
        itemDroite = findViewById(R.id.itemDroite);
        itemGauche = findViewById(R.id.itemGauche);
        nomItemTv = findViewById(R.id.itemChoisi);
        lancer = findViewById(R.id.lancer);
        resTv = findViewById(R.id.resTv);

        augmenter.setOnClickListener(this);
        baisser.setOnClickListener(this);
        itemDroite.setOnClickListener(this);
        itemGauche.setOnClickListener(this);
        lancer.setOnClickListener(this);
        joueurDroite.setOnClickListener(this);
        joueurGauche.setOnClickListener(this);

        lesJoueurs = new ArrayList<>();
        lesJoueurs.add(new Joueur("Joueur1"));
        lesJoueurs.add(new Joueur("Joueur2"));
        lesJoueurs.add(new Joueur("Joueur3"));
        lesJoueurs.add(new Joueur("Joueur4"));
        positionJoueurSelectionne = 0;
        nbJoueurs = lesJoueurs.size();
        nomJoueurTv.setText(lesJoueurs.get(positionJoueurSelectionne).getNom());
        score.setText(Integer.toString(lesJoueurs.get(positionJoueurSelectionne).getScore()));

        lesItems = new ArrayList<>();
        for (String nomItem : MainActivity.accesLocal.getListItems(nomKit)) {
            try {
                lesItems.add(MainActivity.accesLocal.getItem(nomItem, nomKit));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
        positionItemSelectionne = 0;
        nbItems = lesItems.size();
        Log.e("azerty",""+nbItems);
        nomItemTv.setText(lesItems.get(positionItemSelectionne).getNom());
    }


    @Override
    public void onClick(View v) {
        {
            if(v.getId()==R.id.augmenter){
                try {
                    lesJoueurs.get(positionJoueurSelectionne).setScore(lesJoueurs.get(positionJoueurSelectionne).getScore() + Integer.parseInt(entrerScore.getText().toString()));
                    score.setText(Integer.toString(lesJoueurs.get(positionJoueurSelectionne).getScore()));
                }catch (NumberFormatException e){
                    entrerScore.setError("Veuillez entrer un nombre");
                }
            }
            if(v.getId()==R.id.baisser){
                    try {
                        lesJoueurs.get(positionJoueurSelectionne).setScore(lesJoueurs.get(positionJoueurSelectionne).getScore() - Integer.parseInt(entrerScore.getText().toString()));
                        score.setText(Integer.toString(lesJoueurs.get(positionJoueurSelectionne).getScore()));
                    }catch (NumberFormatException e){
                        entrerScore.setError("Veuillez entrer un nombre");
                    }
            }
            if (v.getId()==R.id.lancer){
                resTv.setText(lesItems.get(positionItemSelectionne).faireAction());
            }
            if (v.getId()==R.id.joueurDroite){
                if(positionJoueurSelectionne == nbJoueurs-1){
                    positionJoueurSelectionne = 0;
                } else {
                    positionJoueurSelectionne++;
                }
                nomJoueurTv.setText(lesJoueurs.get(positionJoueurSelectionne).getNom());
                score.setText(Integer.toString(lesJoueurs.get(positionJoueurSelectionne).getScore()));
            }
            if(v.getId()==R.id.joueurGauche){
                if(positionJoueurSelectionne == 0){
                    positionJoueurSelectionne = nbJoueurs-1;
                } else {
                    positionJoueurSelectionne--;
                }
                nomJoueurTv.setText(lesJoueurs.get(positionJoueurSelectionne).getNom());
                score.setText(Integer.toString(lesJoueurs.get(positionJoueurSelectionne).getScore()));
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

