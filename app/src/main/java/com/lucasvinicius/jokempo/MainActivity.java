package com.lucasvinicius.jokempo;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView imageViewPlayer1;
    private ImageView imageViewPlayer2;

    private ImageButton imageButtonStone;
    private ImageButton imageButtonPaper;
    private ImageButton imageButtonScissors;

    private TextView textViewMessage;
    private TextView textViewScoreboard1;
    private TextView textViewScoreboard2;

    private Animation appear;
    private Animation disappear;

    private int movePlayer1 = 0;
    private int movePlayer2 = 0;
    private int scoreboard1 = 0;
    private int scoreboard2 = 0;

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageViewPlayer1 = findViewById(R.id.imageViewPlayer1);
        imageViewPlayer2 = findViewById(R.id.imageViewPlayer2);

        imageButtonStone = findViewById(R.id.imageButtonStone);
        imageButtonPaper = findViewById(R.id.imageButtonPaper);
        imageButtonScissors = findViewById(R.id.imageButtonScissors);

        textViewMessage = findViewById(R.id.textViewMessage);
        textViewScoreboard1 = findViewById(R.id.textViewScoreboard1);
        textViewScoreboard2 = findViewById(R.id.textViewScoreboard2);

        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.som1);

        appear = new AlphaAnimation(0,1);
        appear.setDuration(100);
        disappear = new AlphaAnimation(1,0);
        disappear.setDuration(1500);

        animationToDisappear(disappear);
        animationToAppear(appear);
    }
    public void clickedOnTheButton(View view) {
        playSound();
        imageViewPlayer1.setScaleX(-1f);
        switch (view.getId()) {
            case (R.id.imageButtonStone):
                imageViewPlayer1.setImageResource(R.drawable.stone);
                movePlayer1 = 1;
                break;
            case (R.id.imageButtonPaper):
                imageViewPlayer1.setImageResource(R.drawable.paper);
                movePlayer1 = 2;
                break;
            case (R.id.imageButtonScissors):
                imageViewPlayer1.setImageResource(R.drawable.scissors);
                movePlayer1 = 3;
                break;
        }
        imageViewPlayer2.setImageResource(R.drawable.interrogation);
        imageViewPlayer2.startAnimation(disappear);
    }

    public void enemyMove() {
        Random random = new Random();
        int option = random.nextInt(3);
        switch (option) {
            case (0):
                imageViewPlayer2.setImageResource(R.drawable.stone);
                movePlayer2 = 1;
                break;
            case (1):
                imageViewPlayer2.setImageResource(R.drawable.paper);
                movePlayer2 = 2;
                break;
            case (2):
                imageViewPlayer2.setImageResource(R.drawable.scissors);
                movePlayer2 = 3;
                break;
        }
    }

    public void checkMove() {
        if (movePlayer1 == 1 && movePlayer2 == 2) {
            textViewMessage.setText("You Lost!");
            scoreboard2++;
            textViewScoreboard2.setText("" + scoreboard2);
        } else if (movePlayer1 == 1 && movePlayer2 == 3) {
            textViewMessage.setText("VICTORY!");
            scoreboard1++;
            textViewScoreboard1.setText("" + scoreboard1);
        } else if (movePlayer1 == 2 && movePlayer2 == 1) {
            textViewMessage.setText("VICTORY!");
            scoreboard1++;
            textViewScoreboard1.setText("" + scoreboard1);
        } else if (movePlayer1 == 2 && movePlayer2 == 3) {
            textViewMessage.setText("You Lost!");
            scoreboard2++;
            textViewScoreboard2.setText("" + scoreboard2);
        } else if (movePlayer1 == 3 && movePlayer2 == 1) {
            textViewMessage.setText("You Lost!");
            scoreboard2++;
            textViewScoreboard2.setText("" + scoreboard2);
        } else if (movePlayer1 == 3 && movePlayer2 == 2) {
            textViewMessage.setText("VICTORY!");
            scoreboard1++;
            textViewScoreboard1.setText("" + scoreboard1);
        } else {
            textViewMessage.setText("Match Drawn!");
        }
    }

    public void playSound() {
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
    }

    public void animationToAppear(Animation appear) {
        appear.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                enemyMove();
                imageViewPlayer2.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imageViewPlayer2.setVisibility(View.VISIBLE);
                checkMove();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void animationToDisappear(Animation disappear) {
        disappear.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                imageViewPlayer2.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imageViewPlayer2.setVisibility(View.INVISIBLE);
                imageViewPlayer2.startAnimation(appear);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}