package com.elorrieta.didaktikelorrioapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.elorrieta.didaktikelorrioapp.Class.Sound;


public class ChooseDifficultActivity extends AppCompatActivity {

    /**
     * Pantalla en la que aparece las reglas para el puzzle y pasa a la siguiente actividad.
     */

    private ImageButton soundBtn;
    Sound sound = new Sound();
    ImageButton[] zooImages;
    int[] imagesDraw = {R.drawable.lion, R.drawable.dolphin, R.drawable.bird, R.drawable.elephant, R.drawable.dog, R.drawable.horse};
    Drawable imageChoose;

    @Override
    protected void onStart() {
        super.onStart();
        if (Sound.backgroundMusic != null) {
            if (!Sound.backFromGame) {
                if (!Sound.backgroundMusic.isPlaying())
                    Sound.backgroundMusic.start();
            }
            Sound.backFromGame = false;


        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (!Sound.activitySwitchFlag)
            Sound.backgroundMusic.pause();
        Sound.activitySwitchFlag = false;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_difficult);

        //oculta la barra de arriba
        if (getSupportActionBar() != null)
        getSupportActionBar().hide();

        Button game9Btn = findViewById(R.id.btn1);
        Button game15Btn = findViewById(R.id.btn2);
        Button game24Btn = findViewById(R.id.btn3);
        Button gameZoo = findViewById(R.id.btn4);

        AnimationDrawable animationDrawable = (AnimationDrawable) gameZoo.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(2000);
        animationDrawable.start();

        final TextView puzzelTextChoose = findViewById(R.id.Choose);
        final TextView puzzelextLevels = findViewById(R.id.Levels);


        ImageButton infoBtn = findViewById(R.id.howToPlayBtn);
        infoBtn.setVisibility(View.INVISIBLE);
        infoBtn.setClickable(false);
        ImageButton backBtn = findViewById(R.id.levelBackMenu);
        soundBtn = findViewById(R.id.bSoundOffOnChoose);

        ImageView card9 = findViewById(R.id.card908);
        ImageView card15 = findViewById(R.id.card1515);
        //card24 = findViewById(R.id.card2424);
        card9.setVisibility(View.INVISIBLE);
        card15.setVisibility(View.INVISIBLE);
        //card24.setVisibility(View.INVISIBLE);



        game9Btn.setOnClickListener(onClickListener);
        game15Btn.setOnClickListener(onClickListener);
        game24Btn.setOnClickListener(onClickListener);
        gameZoo.setOnClickListener(onClickListener);

        soundBtn.setOnClickListener(onClickListener);
        backBtn.setOnClickListener(onClickListener);

        if (Sound.check)
            soundBtn.setImageResource(R.drawable.soundon);
        else
            soundBtn.setImageResource(R.drawable.soundoff);


        new Handler().postDelayed(() -> {
            puzzelTextChoose.setVisibility(View.VISIBLE);
            YoYo.with(Techniques.SlideInRight)
                    .duration(2000)
                    .repeat(0);
            puzzelextLevels.setVisibility(View.VISIBLE);
            YoYo.with(Techniques.SlideInLeft)
                    .duration(2000)
                    .repeat(0);
            YoYo.with(Techniques.FadeIn)
                    .duration(3000)
                    .repeat(0);
            YoYo.with(Techniques.Bounce)
                    .duration(3000)
                    .repeat(-1);



        }, 1000);
        play24();
    }

    View.OnClickListener onClickListener = v -> {
        switch (v.getId()) {
            case R.id.btn1:
                //Sound.menuClickSound.start();
                Sound.activitySwitchFlag = true;
                //sound.switchMusic(Sound.gameMusic, Sound.backgroundMusic);
                newGame(3);
                break;
            case R.id.btn2:
                Sound.menuClickSound.start();
                //sound.switchMusic(Sound.gameMusic, Sound.backgroundMusic);
                newGame(4);
                break;
            case R.id.btn3:
                Sound.menuClickSound.start();
                //sound.switchMusic(Sound.gameMusic, Sound.backgroundMusic);
                newGame(5);
                break;
            case R.id.btn4:
                Sound.menuClickSound.start();
                openZooDialog();
                break;
            case R.id.levelBackMenu:
                Sound.menuClickSound.start();
                back();
                break;
            case R.id.bSoundOffOnChoose:
                soundOffOn();
                Sound.menuClickSound.start();
                break;
        }
    };


    private void newGame(int level) {
        Intent gameChooserIntent = new Intent();
        if (level == 3) {
            gameChooserIntent = new Intent(ChooseDifficultActivity.this, GameActivity9.class);
            gameChooserIntent.putExtra("whatToShow", "Cards");
        }
        Sound.activitySwitchFlag = true;
        Sound.backFromGame = true;
        startActivity(gameChooserIntent);
        overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
    }

    public void back() {

        finish();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void openZooDialog() {
        imageChoose = getResources().getDrawable(imagesDraw[1]);
        zooImages = new ImageButton[6];

        for (int i = 0; i < 6; i++) {
            zooImages[i].setOnClickListener(zooClickListener);
            BitmapDrawable drawable = (BitmapDrawable) zooImages[i].getDrawable();
            Bitmap bitmap = drawable.getBitmap();
            Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, 135, 135, true);
            zooImages[i].setImageBitmap(scaledBitmap);
        }

    }

    View.OnClickListener zooClickListener = new View.OnClickListener() {
        @SuppressLint("UseCompatLoadingForDrawables")
        @Override
        public void onClick(View v) {
            int i = 0;
            while (zooImages[i].getId() != v.getId()) {
                i++;
            }
            imageChoose = getResources().getDrawable(imagesDraw[i]);
        }
    };

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    public void soundOffOn() {
        if (Sound.check) {
            Sound.check = false;
            soundBtn.setImageResource(R.drawable.soundoff);
        } else {
            Sound.check = true;
            soundBtn.setImageResource(R.drawable.soundon);
        }
        //sound.setSounds();
        //sound.setMusic();
    }


    private  void play24()
    {
        new Handler().postDelayed(() -> YoYo.with(Techniques.RotateIn)
                .duration(20000)
                .repeat(10),100);
    }
}
