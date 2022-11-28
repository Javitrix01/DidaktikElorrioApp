package com.elorrieta.didaktikelorrioapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class IntroduccionParada2 extends AppCompatActivity {
    Button btn_play,btn_stop;
    SoundPool sp;
    int audio_de_repro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduccion_parada2);
        btn_play=(Button) findViewById(R.id.btn_play);
        sp=new SoundPool(1, AudioManager.STREAM_MUSIC,1);
        audio_de_repro=sp.load(this,R.layout.activity_introduccion_parada2,1);

        btn_stop=(Button) findViewById(R.id.btn_stop);

    }
    public void AudioSoundPool(View view){
        sp.play(audio_de_repro,1,1,1,0,0);
    }
    public void AudioMediaPlayer(View view){
        MediaPlayer mp=MediaPlayer.create(this,R.drawable.grabacion71);
        mp.start();
    }
}