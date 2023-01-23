package com.elorrieta.didaktikelorrioapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.media.MediaPlayer;
import com.elorrieta.didaktikelorrioapp.Class.Sound;

public class Puzzle_Poner_Nombre_Cruces extends AppCompatActivity {
    TextView nonbre1,nonbre2,nonbre3,nonbre4,nonbre5,nonbre6,nonbre7,nonbre8,flatante;
    Button btn_comprobar;
    Sound sonido;
    MediaPlayer vectorAcierto=new MediaPlayer();
    MediaPlayer vectorError=new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle_poner_nombre_cruces);


        flatante = (TextView) findViewById(R.id.et_Iguriako_Gurutzea);

        //Variables inicializadas
        nonbre1 = (TextView) findViewById(R.id.et_Kurutzebarri);
        nonbre2 = (TextView) findViewById(R.id.et_Ganondo);
        nonbre3 = (TextView) findViewById(R.id.et_SanJuan);
        nonbre4 = (TextView) findViewById(R.id.et_kurutziaga);
        nonbre5 = (TextView) findViewById(R.id.et_Kurutzondo);
        nonbre6 = (TextView) findViewById(R.id.et_SantaElena);
        nonbre7 = (TextView) findViewById(R.id.et_Txanberi);
        btn_comprobar = (Button) findViewById(R.id.btnJarraituPuzCrucesNombre);
        vectorAcierto = MediaPlayer.create(this, R.raw.sonidoacierto);
        vectorError = MediaPlayer.create(this, R.raw.sonidoerror);
        //Boton esperando
        btn_comprobar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Variables cargadas con los string recoguidos de sus respectivas cajas de texto
                @SuppressLint("ResourceType") String text1=nonbre1.getText().toString().trim();
                @SuppressLint("ResourceType") String text2=nonbre2.getText().toString().trim();
                @SuppressLint("ResourceType") String text3=nonbre3.getText().toString().trim();
                @SuppressLint("ResourceType") String text4=nonbre4.getText().toString().trim();
                @SuppressLint("ResourceType") String text5=nonbre5.getText().toString().trim();
                @SuppressLint("ResourceType") String text6=nonbre6.getText().toString().trim();
                @SuppressLint("ResourceType") String text7=nonbre7.getText().toString().trim();
                //if para enviar las variables con los strings a la funcion y determinar si es correcto
                if(comprobar(text1,text2,text3,text4,text5,text6,text7)){
                    if(vectorError.isPlaying()){
                        vectorError.pause();
                    }else{
                        vectorError.start();
                        if(Sound.sonidoError!=null) {
                            if (Sound.sonidoError.isPlaying()) {
                                sonido.switchMusic(Sound.gameMusic, Sound.sonidoError);
                            }
                        }
                    }
                    flatante.setText("Errorea");

                }else{
                    if(vectorAcierto.isPlaying()){
                        vectorAcierto.pause();
                    }else {
                        vectorAcierto.start();
                        if(Sound.sonidoAcierto!=null) {
                            if (Sound.sonidoAcierto.isPlaying()) {
                                sonido.switchMusic(Sound.gameMusic, Sound.sonidoAcierto);
                            }
                        }
                    }
                    flatante.setText("Iguriako");
                }

            }
        });
    }
    public boolean comprobar(String nombre1,String nombre2,String nombre3,String nombre4,String nombre5,String nombre6,String nombre7){
        Boolean compr=true;

        //Comprobaciones de los strings
        if(nombre1.equals("KB")){
            if(nombre2.equals("GD")){
                if(nombre3.equals("SJ")){
                    if(nombre4.equals("KT")){
                        if(nombre5.equals("KO")){
                            if(nombre6.equals("SE")){
                                if(nombre7.equals("TB")){
                                    compr=false;
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