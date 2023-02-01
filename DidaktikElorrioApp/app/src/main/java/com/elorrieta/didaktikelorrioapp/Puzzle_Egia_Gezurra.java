package com.elorrieta.didaktikelorrioapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.elorrieta.didaktikelorrioapp.appdatabase.AppDatabase;

public class Puzzle_Egia_Gezurra extends AppCompatActivity {

    /**
     * Pantalla en la que tiene que marcar si los enunciados
     * que aparecen son verdad o mentira. Tienen un boton para
     * comprobar las respuestas y una vez las tengan bien pasan
     * a la siguiente actividad.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle_egia_gezurra);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        //crea una instancia de la base de datos
        AppDatabase db = AppDatabase.getDatabase(getApplicationContext());

        //Definicion de variables
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Button baieztatu = findViewById(R.id.BaieztatuEgiaGezurra);
        CheckBox P1E = findViewById(R.id.P1E);
        CheckBox P1G = findViewById(R.id.P1G);

        CheckBox P2E = findViewById(R.id.P2E);
        CheckBox P2G = findViewById(R.id.P2G);

        CheckBox P3E = findViewById(R.id.P3E);
        CheckBox P3G = findViewById(R.id.P3G);

        CheckBox P4E = findViewById(R.id.P4E);
        CheckBox P4G = findViewById(R.id.P4G);

        CheckBox P5E = findViewById(R.id.P5E);
        CheckBox P5G = findViewById(R.id.P5G);

        CheckBox P6E = findViewById(R.id.P6E);
        CheckBox P6G = findViewById(R.id.P6G);

        CheckBox P7E = findViewById(R.id.P7E);
        CheckBox P7G = findViewById(R.id.P7G);

        CheckBox P8E = findViewById(R.id.P8E);
        CheckBox P8G = findViewById(R.id.P8G);


        MediaPlayer victory = MediaPlayer.create(this, R.raw.victory);
        MediaPlayer error = MediaPlayer.create(this, R.raw.error);

/*------------------Pregunta 1------------------------*/
        P1E.setOnClickListener(view -> P1G.setChecked(!P1E.isChecked()));
        P1G.setOnClickListener(view -> P1E.setChecked(!P1G.isChecked()));
/*------------------- Pregunta 2 ------------------*/
        P2E.setOnClickListener(view -> P2G.setChecked(!P2E.isChecked()));
        P2G.setOnClickListener(view -> P2E.setChecked(!P2G.isChecked()));
/*----------------Baieztatu--------------------*/
        P3E.setOnClickListener(view -> P3G.setChecked(!P3E.isChecked()));
        P3G.setOnClickListener(view -> P3E.setChecked(!P3G.isChecked()));
/*-------------------Pregunta 4 -------------------*/
        P4E.setOnClickListener(view -> P4G.setChecked(!P4E.isChecked()));
        P4G.setOnClickListener(view -> P4E.setChecked(!P4G.isChecked()));
/*-------------------Pregunta 5 -------------------*/
        P5E.setOnClickListener(view -> P5G.setChecked(!P5E.isChecked()));
        P5G.setOnClickListener(view -> P5E.setChecked(!P5G.isChecked()));
/*-------------------Pregunta 6 -------------------*/
        P6E.setOnClickListener(view -> P6G.setChecked(!P6E.isChecked()));
        P6G.setOnClickListener(view -> P6E.setChecked(!P6G.isChecked()));
/*-------------------Pregunta 7 -------------------*/
        P7E.setOnClickListener(view -> P7G.setChecked(!P7E.isChecked()));
        P7G.setOnClickListener(view -> P7E.setChecked(!P7G.isChecked()));
/*-------------------Pregunta 8 -------------------*/
        P8E.setOnClickListener(view -> P8G.setChecked(!P8E.isChecked()));
        P8G.setOnClickListener(view -> P8E.setChecked(!P8G.isChecked()));
/*----------------Baieztatu--------------------*/
        baieztatu.setOnClickListener(view -> {
            if(P1G.isChecked()&&P2E.isChecked()&&P3E.isChecked()&&P4G.isChecked()&&P5E.isChecked()&&P6G.isChecked()&&P7G.isChecked()&&P8E.isChecked()){
                db.activityDao().updateActivityVisitedById(7, true);
                victory.start();
                Toast.makeText(this, "Oso ondo", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Puzzle_Egia_Gezurra.this, MenuPrincipalMapa.class));
            }else{
                Toast.makeText(this, "Txarto", Toast.LENGTH_SHORT).show();
                error.start();
            }
        });
    }

    }
