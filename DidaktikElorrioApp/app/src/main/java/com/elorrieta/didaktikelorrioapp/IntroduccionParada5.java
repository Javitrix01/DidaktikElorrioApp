package com.elorrieta.didaktikelorrioapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;

public class IntroduccionParada5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduccion_parada5);

        Button botonPasar = findViewById(R.id.btnJarraituP5);
        botonPasar.setOnClickListener(view -> startActivity(new Intent(IntroduccionParada5.this, Puzzle_Mapa_Elorrio_Con_Nombres.class)));

        /*Animacion del personaje*/
        ImageView myImageView = (ImageView) findViewById(R.id.imageView16);
        AnimationDrawable myAnimation = (AnimationDrawable) getResources().getDrawable(R.drawable.animationreina);
        myImageView.setBackgroundDrawable(myAnimation);


        getSupportActionBar().hide();


         /* Audio y movimiento del seekbar al pulsar el boton:
        Al clickar el boton inicia el audio y empieza a moverse el seekbar acorde al audio,
        si vuelves a clickar el boton se detiene el audio y el seekbar*/

        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.grabacionparada5);
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar4);
        seekBar.setMax(mediaPlayer.getDuration());
        final Handler handler = new Handler();
        Button button = findViewById(R.id.btnSonidoP1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    button.setAlpha(1f);
                    mediaPlayer.pause();
                    myAnimation.stop();
                    handler.removeCallbacksAndMessages(null);
                } else {
                    mediaPlayer.start();
                    myAnimation.start();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            button.setAlpha(0.5f);
                            seekBar.setProgress(mediaPlayer.getCurrentPosition());
                            handler.postDelayed(this, 1000);
                        }
                    });
                }
            }
        });

        handler.post(new Runnable() {
            @Override
            public void run() {
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
                handler.postDelayed(this, 1000);
            }
        });
    }
}