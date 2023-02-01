package com.elorrieta.didaktikelorrioapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.elorrieta.didaktikelorrioapp.Class.Sound;


public class HomeActivity extends AppCompatActivity {

    /**
     * Pantalla que muestra la imagen del puzzle completada para que sea mas sencillo hacerlo despues.
     */

    Sound sound=new Sound();
    boolean finish = false;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        sound.release();
        finish =true;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(!Sound.activitySwitchFlag)
            if(!finish)
                Sound.backgroundMusic.pause();
        Sound.activitySwitchFlag = false;
        finish=false;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //oculta la barra de arriba
        if (getSupportActionBar() != null)
        getSupportActionBar().hide();

        Button startButton = findViewById(R.id.start_button);



        final TextView puzzelText = findViewById(R.id.PuzzelIt);
        startButton.setOnClickListener(onClickListener);




        YoYo.with(Techniques.Shake)
                .duration(3000)
                .repeat(-1);

        new Handler().postDelayed(() -> {
            puzzelText.setVisibility(View.VISIBLE);
            YoYo.with(Techniques.SlideInDown)
                    .duration(4000)
                    .interpolate(new AccelerateDecelerateInterpolator())
                    .interpolate(new BounceInterpolator())
                    .repeat(0);
            YoYo.with(Techniques.SlideInRight)
                    .duration(1000)
                    .repeat(0);
            YoYo.with(Techniques.RotateIn)
                    .duration(1000)
                    .repeat(0);
            YoYo.with(Techniques.SlideInRight)
                    .duration(2000)
                    .repeat(0);
            YoYo.with(Techniques.RotateIn)
                    .duration(2000)
                    .repeat(0);
            YoYo.with(Techniques.SlideInRight)
                    .duration(3000)
                    .repeat(0);
            YoYo.with(Techniques.RotateIn)
                    .duration(3000)
                    .repeat(0);

        },1000);
    }

    View.OnClickListener onClickListener = v -> {
        if (v.getId() == R.id.start_button) {
            Sound.menuClickSound.start();
            Intent chooseDifficultIntent = new Intent(HomeActivity.this, ChooseDifficultActivity.class);
            startActivity(chooseDifficultIntent);
            Sound.activitySwitchFlag = true;
            overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
        }
    };

}