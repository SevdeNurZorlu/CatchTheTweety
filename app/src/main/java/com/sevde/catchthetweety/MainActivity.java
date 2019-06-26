package com.sevde.catchthetweety;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView timetext;
    TextView scoretext;
    int score;
    ImageView imageView;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView[] imageArray;
    Handler handler ;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize
        timetext = findViewById(R.id.textView);

        scoretext = findViewById(R.id.textView2);
        imageView = findViewById(R.id.imageView);
        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);


        hideImages();
        imageArray = new ImageView[]{imageView, imageView1, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8};
        score = 0;

        new CountDownTimer(60000, 1000) {


            @Override
            public void onTick(long millisUntilFinished) {

                timetext.setText("Time:" + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {

                timetext.setText ( "Time Off" );
                handler.removeCallbacks ( runnable );
                for (ImageView image : imageArray) {
                    image.setVisibility(View.INVISIBLE);
                }

                AlertDialog.Builder alert=new AlertDialog.Builder (MainActivity.this) ;
                alert.setTitle ( "Restart?" );
                alert.setMessage ( "Are you sure to restart game?" );
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //restart

                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);

                    }
                });

                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Game Over!", Toast.LENGTH_SHORT).show();
                    }
                });


                alert.show ();
            }
        }.start();
    }

    public void Score(View view)
    //I assigned ImageView to an onClick attribute called Score
    {

        //Score will increase every time you click ImageView
        score++;
        scoretext.setText("Score:" + score);
    }

    public void hideImages() {

        handler=new Handler ();
        runnable = new Runnable() {
            @Override
            public void run() {

                //tweety was invisible
                for (ImageView image : imageArray) {
                    image.setVisibility(View.INVISIBLE);
                }
                Random random = new Random();
                int i = random.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);

                handler.postDelayed(this, 500);

            }

        };

        handler.post ( runnable );


    }

}




