package com.elorrieta.didaktikelorrioapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.elorrieta.didaktikelorrioapp.Class.Sound;
import com.elorrieta.didaktikelorrioapp.appdatabase.AppDatabase;

public class Puzzle_Poner_Nombre_Cruces extends AppCompatActivity {

    TextView nonbre1, nonbre2, nonbre3, nonbre4, nonbre5, nonbre6, nonbre7, nonbre8, flatante;
    Button btn_comprobar;
    Sound sonido;
    MediaPlayer vectorAcierto = new MediaPlayer();
    MediaPlayer vectorError = new MediaPlayer();
    String textofalt;

    String[] faltan = getResources().getStringArray(R.array.lugares);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle_poner_nombre_cruces);


        flatante = (TextView) findViewById(R.id.et_Iguriako_Gurutzea);


        final String[] text = new String[7];
        //Variables inicializadas
        nonbre1 = (TextView) findViewById(R.id.et_Kurutzebarri);
        nonbre2 = (TextView) findViewById(R.id.et_Ganondo);
        nonbre3 = (TextView) findViewById(R.id.et_Iguriako_Gurutzea);
        nonbre4 = (TextView) findViewById(R.id.et_SanJuan);
        nonbre5 = (TextView) findViewById(R.id.et_kurutziaga);
        nonbre6 = (TextView) findViewById(R.id.et_Kurutzondo);
        nonbre7 = (TextView) findViewById(R.id.et_SantaElena);
        nonbre8 = (TextView) findViewById(R.id.et_Txanberi);
        btn_comprobar = (Button) findViewById(R.id.btnJarraituPuzCrucesNombre);
        vectorAcierto = MediaPlayer.create(this, R.raw.victory);
        vectorError = MediaPlayer.create(this, R.raw.error);
        //Boton esperando

        //crea una instancia de la base de datos
        AppDatabase db = AppDatabase.getDatabase(getApplicationContext());
        btn_comprobar.setOnClickListener(view -> {  db.activityDao().updateActivityVisitedById(1, true); startActivity(new Intent(Puzzle_Poner_Nombre_Cruces.this, MenuPrincipalMapa.class));});

        btn_comprobar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Variables cargadas con los string recoguidos de sus respectivas cajas de texto
                @SuppressLint("ResourceType") String text1 = nonbre1.getText().toString().trim();
                @SuppressLint("ResourceType") String text2 = nonbre2.getText().toString().trim();
                @SuppressLint("ResourceType") String text3 = nonbre3.getText().toString().trim();
                @SuppressLint("ResourceType") String text4 = nonbre4.getText().toString().trim();
                @SuppressLint("ResourceType") String text5 = nonbre5.getText().toString().trim();
                @SuppressLint("ResourceType") String text6 = nonbre6.getText().toString().trim();
                @SuppressLint("ResourceType") String text7 = nonbre7.getText().toString().trim();
                @SuppressLint("ResourceType") String text8 = nonbre8.getText().toString().trim();
                text[0] = text1;
                text[1] = text2;
                text[2] = text3;
                text[3] = text4;
                text[4] = text5;
                text[5] = text6;
                text[6] = text7;
                text[7] = text8;
                //if para enviar las variables con los strings a la funcion y determinar si es correcto
                if (comprobar(text1, text2, text3, text4, text5, text6, text7, text8)) {
                    if (vectorError.isPlaying()) {
                        vectorError.pause();
                    } else {
                        vectorError.start();
                        if (Sound.error != null) {
                            if (Sound.error.isPlaying()) {
                                sonido.switchMusic(Sound.gameMusic, Sound.error);
                            }
                        }
                    }
                    //textofalt=", "+faltan[1]+", "+faltan[2]+", "+faltan[3]+", "+faltan[4]+", "+faltan[6]+", "+faltan[7]+", "+faltan[8];
                    //flatante.setText(textofalt);
                } else {
                    if (vectorAcierto.isPlaying()) {
                        vectorAcierto.pause();
                    } else {
                        vectorAcierto.start();
                        if (Sound.winningSound != null) {
                            if (Sound.winningSound.isPlaying()) {
                                sonido.switchMusic(Sound.gameMusic, Sound.winningSound);
                            }
                        }
                    }
                }

            }
        });
    }

    public boolean comprobar(String nombre1, String nombre2, String nombre3, String nombre4, String nombre5, String nombre6, String nombre7, String nombre8) {
        Boolean compr = true;

        //Comprobaciones de los strings
        if (nombre1.equals("KB")) {
            if (nombre2.equals("GD")) {
                if (nombre3.equals("IG")) {
                    if (nombre4.equals("SJ")) {
                        if (nombre5.equals("KT")) {
                            if (nombre6.equals("KO")) {
                                if (nombre7.equals("SE")) {
                                    if (nombre8.equals("TB")) {
                                        compr = false;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return compr;
    }
}
