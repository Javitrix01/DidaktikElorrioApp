package com.elorrieta.didaktikelorrioapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Mapa_Cruces_Con_Nombre extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_cruces_con_nombre);

        Button botonPasar = findViewById(R.id.btnJarraituCrucesCnombre);
        botonPasar.setVisibility(View.VISIBLE);
        botonPasar.setClickable(true);

        getSupportActionBar().hide();
        botonPasar.setOnClickListener(view -> startActivity(new Intent(Mapa_Cruces_Con_Nombre.this, MenuPrincipalMapa.class)));
    }
}