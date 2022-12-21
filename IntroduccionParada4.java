package com.elorrieta.didaktikelorrioapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.elorrieta.didaktikelorrioapp.Class.Sound;

public class IntroduccionParada4 extends AppCompatActivity {
    Button btn_iniio;
    MediaPlayer vector=new MediaPlayer();
    Sound sonido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduccion_parada4);

        btn_iniio=(Button) findViewById(R.id.btn_inicio4);
        vector=MediaPlayer.create(this,R.raw.grabacionparada4);
        btn_iniio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(vector.isPlaying()){
                    vector.pause();
                    Toast.makeText(IntroduccionParada4.this,"Pausa",Toast.LENGTH_SHORT).show();
                }else{
                    vector.start();
                    if (Sound.grabacionparada4 != null) {
                        if (!Sound.grabacionparada4.isPlaying())
                            sonido.switchMusic(Sound.gameMusic, Sound.grabacionparada4);
                    }
                    Toast.makeText(IntroduccionParada4.this,"Inicio",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}