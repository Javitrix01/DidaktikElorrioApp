package com.elorrieta.didaktikelorrioapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.elorrieta.didaktikelorrioapp.Class.Sound;

public class IntroduccionParada1 extends AppCompatActivity {
    Button btn_iniciar;
    MediaPlayer vector=new MediaPlayer();
    Sound sonido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduccion_parada1);
        btn_iniciar=(Button) findViewById(R.id.btn_empezar);
        vector=MediaPlayer.create(this,R.raw.grabacionparada1);
        btn_iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(vector.isPlaying()){
                    vector.pause();
                    Toast.makeText(IntroduccionParada1.this,"Pausa",Toast.LENGTH_SHORT).show();
                }else{
                    vector.start();
                    if(Sound.grabacionparada1!=null){
                        if(!Sound.grabacionparada1.isPlaying()){
                            sonido.switchMusic(Sound.gameMusic,Sound.grabacionparada1);
                        }
                        Toast.makeText(IntroduccionParada1.this,"Inicio",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}