package com.example.pierre.jeuxdesocit;

import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;

import java.util.Locale;
import java.util.TimerTask;

public class Timer implements Item {

    private static final long START_TIME_IN_MILLIS = 600000;

    private String nom;
    private String valeur;
    private int image;

    private CountDownTimer mCountDownTimer;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    private boolean mTimerRunning = false;
    private String timeactualiser = null;


    public Timer() {
        nom = "Timer";
        valeur = "60";
        image = R.drawable.de;
    }

    public Timer(String nom, String valeur) {
        this.nom = nom;
        this.valeur = valeur;
        this.image = R.drawable.de;
    }

    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public String getValeur() {
        return valeur;
    }

    @Override
    public int getImage() {
        return image;
    }

    public boolean ismTimerRunning() {
        return this.mTimerRunning;
    }

    @Override
    public String faireAction() {

/*
            if (mTimerRunning == false){
                Log.e("PPPPPPP", ""+mTimerRunning);
                mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        mTimeLeftInMillis = millisUntilFinished;
                        updateCountDownText();
                    }

                    @Override
                    public void onFinish() {
                        mTimerRunning = false;
                    }
                }.start();
                mTimerRunning = true;
            }*/


/*
        if (mTimerRunning == false) {
            java.util.Timer timer = new java.util.Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    faireAction();

                    Log.e("PPPP",""+mTimeLeftInMillis );
                }
            }, 0, 1000);
            mTimerRunning = true;
        }*/

        mTimeLeftInMillis -= 1000;
        return updateCountDownText();
    }

    private String updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        return timeLeftFormatted;

    }
}
