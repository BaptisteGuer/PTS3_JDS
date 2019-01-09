package com.example.pierre.jeuxdesocit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;


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
    private ImageView imageItem;
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
        imageItem = findViewById(R.id.item);

        augmenter.setOnClickListener(this);
        baisser.setOnClickListener(this);
        itemDroite.setOnClickListener(this);
        itemGauche.setOnClickListener(this);
        lancer.setOnClickListener(this);
        joueurDroite.setOnClickListener(this);
        joueurGauche.setOnClickListener(this);

        lesJoueurs = new ArrayList<>();
        for (Joueur j : MainActivity.accesLocal.getJoueurs(nomKit)) {
            lesJoueurs.add(j);
        }

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
        nomItemTv.setText(lesItems.get(positionItemSelectionne).getNom());
        imageItem.setImageResource(lesItems.get(positionItemSelectionne).getImage());
    }


    private void save(){
        MainActivity.accesLocal.supprimerJoueur(nomKit);
        MainActivity.accesLocal.ajoutListJoueur(lesJoueurs,nomKit);
        Intent intent = new Intent(GameActivity.this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }

    private void delete(){
        MainActivity.accesLocal.supprimerJoueur(nomKit);
        Intent intent = new Intent(GameActivity.this, MainActivity.class);
        startActivity(intent);
        this.finish();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //ajoute les entrées de menu_test à l'ActionBar
        getMenuInflater().inflate(R.menu.menu_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_save:
                save();
                return true;
            case R.id.action_leave:
                delete();
                return true;
        }

        return super.onOptionsItemSelected(item);
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

                if (lesItems.get(positionItemSelectionne) instanceof Timer){

                    java.util.Timer timerV2 = new java.util.Timer();
                    timerV2.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            resTv.setText(lesItems.get(positionItemSelectionne).faireAction());
                        }
                    }, 0, 1000);
                }else {
                    resTv.setText(lesItems.get(positionItemSelectionne).faireAction());
                }


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
                imageItem.setImageResource(lesItems.get(positionItemSelectionne).getImage());
            }
            if(v.getId()==R.id.itemGauche){
                if(positionItemSelectionne == 0){
                    positionItemSelectionne = nbItems-1;
                } else {
                    positionItemSelectionne--;
                }
                nomItemTv.setText(lesItems.get(positionItemSelectionne).getNom());
                imageItem.setImageResource(lesItems.get(positionItemSelectionne).getImage());
            }
        }
    }
}

