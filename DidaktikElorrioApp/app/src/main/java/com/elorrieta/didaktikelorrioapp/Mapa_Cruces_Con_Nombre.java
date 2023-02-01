package com.elorrieta.didaktikelorrioapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Mapa_Cruces_Con_Nombre extends AppCompatActivity {

    /**
     * Pantalla en la que se le enseña los nombres de los cruces para que haga la
     * actividad posteriormente. Simplemente la pasa cuando quiere con un boton.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_cruces_con_nombre);

        Button botonPasar = findViewById(R.id.btnJarraituCrucesCnombre);
        botonPasar.setVisibility(View.VISIBLE);
        botonPasar.setClickable(true);

        if (getSupportActionBar() != null)
        getSupportActionBar().hide();
        botonPasar.setOnClickListener(view -> startActivity(new Intent(Mapa_Cruces_Con_Nombre.this, Puzzle_Poner_Nombre_Cruces.class)));

    }
}