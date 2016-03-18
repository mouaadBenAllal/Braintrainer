package com.example.administrator.braintrainer;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.Random;

public class TimerActivity extends AppCompatActivity {
    private TextView timerTextview;
    private TextView scoreTextview;
    private Button[] buttons;
    private int goedeAntwoord;
    private int aantalGoed;
    private int aantalBeurten;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        timerTextview = (TextView) findViewById(R.id.timerTextview);
        scoreTextview = (TextView) findViewById(R.id.scoreTextview);
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        buttons = new Button[4];
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = (Button) gridLayout.getChildAt(i);
        }
        startTimer();
        maakSom();
    }

    private void startTimer() {
        new CountDownTimer(30000 + 100, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                int seconden = (int) millisUntilFinished / 1000;
                timerTextview.setText(String.valueOf(seconden));
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("score",aantalGoed);
                startActivity(intent);
            }


        }.start();
    }

    private void maakSom () {
        Random randomizer = new Random();
        int getal1 = randomizer.nextInt(50) + 1;
        int getal2 = randomizer.nextInt(50) + 1;
        TextView somTextView = (TextView) findViewById(R.id.somTextView);
        somTextView.setText(String.valueOf(getal1) + "+" + getal2);
        goedeAntwoord = getal1 + getal2;

        int goedeAntwoordKnop = randomizer.nextInt(4);
        buttons[goedeAntwoordKnop].setText(String.valueOf(goedeAntwoord));
        for (int i = 0; i < buttons.length; i++) {
            if (i != goedeAntwoordKnop) {
                int foutAntwoord = randomizer.nextInt(50) + 1;
                while (foutAntwoord == goedeAntwoord) {
                    foutAntwoord = randomizer.nextInt(50) + 1;
                }
                buttons [i].setText(String.valueOf(foutAntwoord));
            }
        }
    }
        public void checkUitkomst (View view) {
            aantalBeurten++;
            Button button = (Button) view;
            int antwoord = Integer.parseInt(button.getText().toString());
            if (antwoord == goedeAntwoord) {
                aantalGoed++;
            }
            scoreTextview.setText(String.valueOf(aantalGoed) + "/" + aantalBeurten);
            maakSom();
    }
    }



