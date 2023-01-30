package com.elorrieta.didaktikelorrioapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;
import android.os.Bundle;

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

        getSupportActionBar().hide();

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
/**------------------Pregunta 1------------------------*/
        P1E.setOnClickListener(view -> {
            if(P1E.isChecked()){
                P1G.setChecked(false);
            }else{
                P1G.setChecked(true);
            }
        });
        P1G.setOnClickListener(view -> {
            if(P1G.isChecked()){
                P1E.setChecked(false);
            }else{
                P1E.setChecked(true);
            }
        });
/**------------------- Pregunta 2 ------------------*/
        P2E.setOnClickListener(view -> {
            if(P2E.isChecked()){
                P2G.setChecked(false);
            }else{
                P2G.setChecked(true);
            }
        });
        P2G.setOnClickListener(view -> {
            if(P2G.isChecked()){
                P2E.setChecked(false);
            }else{
                P2E.setChecked(true);
            }
        });
/**-------------------Pregunta 3 -------------------*/

        P3E.setOnClickListener(view -> {
            if(P3E.isChecked()){
                P3G.setChecked(false);
            }else{
                P3G.setChecked(true);
            }
        });
        P3G.setOnClickListener(view -> {
            if(P3G.isChecked()){
                P3E.setChecked(false);
            }else{
                P3E.setChecked(true);
            }
        });
/**-------------------Pregunta 4 -------------------*/
        P4E.setOnClickListener(view -> {
            if(P4E.isChecked()){
                P4G.setChecked(false);
            }else{
                P4G.setChecked(true);
            }
        });
        P4G.setOnClickListener(view -> {
            if(P4G.isChecked()){
                P4E.setChecked(false);
            }else{
                P4E.setChecked(true);
            }
        });
/**-------------------Pregunta 5 -------------------*/
        P5E.setOnClickListener(view -> {
            if(P5E.isChecked()){
                P5G.setChecked(false);
            }else{
                P5G.setChecked(true);
            }
        });
        P5G.setOnClickListener(view -> {
            if(P5G.isChecked()){
                P5E.setChecked(false);
            }else{
                P5E.setChecked(true);
            }
        });
/**-------------------Pregunta 6 -------------------*/
        P6E.setOnClickListener(view -> {
            if(P6E.isChecked()){
                P6G.setChecked(false);
            }else{
                P6G.setChecked(true);
            }
        });
        P6G.setOnClickListener(view -> {
            if(P6G.isChecked()){
                P6E.setChecked(false);
            }else{
                P6E.setChecked(true);
            }
        });
/**-------------------Pregunta 7 -------------------*/
        P7E.setOnClickListener(view -> {
            if(P7E.isChecked()){
                P7G.setChecked(false);
            }else{
                P7G.setChecked(true);
            }
        });
        P7G.setOnClickListener(view -> {
            if(P7G.isChecked()){
                P7E.setChecked(false);
            }else{
                P7E.setChecked(true);
            }
        });
/**-------------------Pregunta 8 -------------------*/
        P8E.setOnClickListener(view -> {
            if(P8E.isChecked()){
                P8G.setChecked(false);
            }else{
                P8G.setChecked(true);
            }
        });
        P8G.setOnClickListener(view -> {
            if(P8G.isChecked()){
                P8E.setChecked(false);
            }else{
                P8E.setChecked(true);
            }
        });
/**----------------Baieztatu--------------------*/
        baieztatu.setOnClickListener(view -> {
            if(P1G.isChecked()&&P2E.isChecked()&&P3E.isChecked()&&P4G.isChecked()&&P5E.isChecked()&&P6G.isChecked()&&P7G.isChecked()&&P8E.isChecked()){
                db.activityDao().updateActivityVisitedById(7, true);
                Toast.makeText(this, "Nice", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Puzzle_Egia_Gezurra.this, MenuPrincipalMapa.class));
            }
        });
    }

    }
