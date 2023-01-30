package com.elorrieta.didaktikelorrioapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Puzzle_Mapa_Elorrio_Con_Nombres extends AppCompatActivity {

    /**
     * Pantalla en la que se les enseña una foto con distintos
     * sitios señalados por numeros para que realizen la siguiente actividad.
     * Pueden pasar cuando quieran mediante un boton.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle_mapa_elorrio_con_nombres);

        Button botonPasar = findViewById(R.id.btnMapaElorrioConNombre);
        botonPasar.setOnClickListener(view -> startActivity(new Intent(Puzzle_Mapa_Elorrio_Con_Nombres.this, Puzzle_Mapa_Elorrio_Poner_Nombres.class)));
        getSupportActionBar().hide();
    }
}