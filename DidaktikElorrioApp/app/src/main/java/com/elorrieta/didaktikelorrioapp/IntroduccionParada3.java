package com.elorrieta.didaktikelorrioapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AppOpsManager;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.elorrieta.didaktikelorrioapp.appdatabase.AppDatabase;

public class IntroduccionParada3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduccion_parada3);

        //crea una instancia de la base de datos
        AppDatabase db = AppDatabase.getDatabase(getApplicationContext());

        Button botonPasar = findViewById(R.id.btnJarraituP3);
        botonPasar.setOnClickListener(view -> {  db.activityDao().updateActivityVisitedById(3, true); startActivity(new Intent(IntroduccionParada3.this, parada4_presentacion.class)); });

        /*Animacion del personaje*/
        ImageView myImageView = (ImageView) findViewById(R.id.imageView22);
        AnimationDrawable myAnimation = (AnimationDrawable) getResources().getDrawable(R.drawable.animationalcalde);
        myImageView.setBackgroundDrawable(myAnimation);

        getSupportActionBar().hide();

         /* Audio y movimiento del seekbar al pulsar el boton:
        Al clickar el boton inicia el audio y empieza a moverse el seekbar acorde al audio,
        si vuelves a clickar el boton se detiene el audio y el seekbar*/

        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.grabacionparada3);
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
                    myAnimation.start();
                    mediaPlayer.start();
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