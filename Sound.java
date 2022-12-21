package com.elorrieta.didaktikelorrioapp.Class;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;

import com.elorrieta.didaktikelorrioapp.R;


public class Sound{
    public static  boolean activitySwitchFlag = false;
    public static  boolean backFromGame = false;
    public static MediaPlayer menuClickSound, buttonGameSound,winningSound,backgroundMusic,gameMusic,testuGurutza,grabacionparada1,grabacionparada2
            ,grabacionparada3,grabacionparada4,grabacionparada5,grabacionparada6,grabacionparada7;
    public static boolean check = true;

    public Sound(){
    }
    public void createSound(Context mContext){
        menuClickSound = MediaPlayer.create(mContext, R.raw.buttonpressed);
        buttonGameSound = MediaPlayer.create(mContext, R.raw.cardpress);
        winningSound =MediaPlayer.create(mContext,R.raw.victory);
        backgroundMusic = MediaPlayer.create(mContext,R.raw.backgroundsound);
        gameMusic = MediaPlayer.create(mContext,R.raw.gamesound);
        testuGurutza = MediaPlayer.create(mContext,R.raw.gurutza);
        grabacionparada1=MediaPlayer.create(mContext,R.raw.grabacionparada1);
        grabacionparada2=MediaPlayer.create(mContext,R.raw.grabacionparada2);
        grabacionparada3=MediaPlayer.create(mContext,R.raw.grabacionparada3);
        grabacionparada4=MediaPlayer.create(mContext,R.raw.grabacionparada4);
        grabacionparada5=MediaPlayer.create(mContext,R.raw.grabacionparada5);
        grabacionparada6=MediaPlayer.create(mContext,R.raw.grabacionparada6);
        grabacionparada7=MediaPlayer.create(mContext,R.raw.grabacionparada7);
        gameMusic.setLooping(true);
        backgroundMusic.setLooping(true);

        setMusic();
        setSounds();
    }
    public void  setSounds(){
        if (check) {
            buttonGameSound.setVolume(0.1f, 0.1f);
            winningSound.setVolume(0.1f, 0.1f);
            menuClickSound.setVolume(0.1f, 0.1f);
        }
        else {
            buttonGameSound.setVolume(0, 0);
            winningSound.setVolume(0, 0);
            menuClickSound.setVolume(0, 0);

        }


    }

    public void setMusic(){
        if(check){
            gameMusic.setVolume(0.5f,0.5f);
            backgroundMusic.setVolume(0.05f,0.05f);
        }
        else {
            gameMusic.setVolume(0, 0);
            backgroundMusic.setVolume(0, 0);
        }
    }
    public void switchMusic(final MediaPlayer in , MediaPlayer out){
        out.pause();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                in.start();
            }
        },1000);

    }
    public void release(){
        gameMusic.release();
        backgroundMusic.release();
        menuClickSound.release();
        winningSound.release();
        buttonGameSound.release();
        testuGurutza.release();
        grabacionparada2.release();
    }


}


