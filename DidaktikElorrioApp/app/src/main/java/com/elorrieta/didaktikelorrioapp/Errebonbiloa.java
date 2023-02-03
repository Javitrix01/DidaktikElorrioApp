package com.elorrieta.didaktikelorrioapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.elorrieta.didaktikelorrioapp.appdatabase.AppDatabase;

public class Errebonbiloa extends AppCompatActivity {

    /**
     * Actividad en la que tiene que hacer manualidades para hacer el gorro.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_errebonbiloa);

        //crea una instancia de la base de datos
        AppDatabase db = AppDatabase.getDatabase(getApplicationContext());

        //cuando le da al boton actualiza el estado de la actividad en la base de datos y pasa a la siguiente actividad
        Button botonPasar = findViewById(R.id.btnJarraituErre);
        botonPasar.setOnClickListener(view -> { db.activityDao().updateActivityVisitedById(6, true); startActivity(new Intent(Errebonbiloa.this, parada7_presentacion.class)); });

        //esconde la barra superior
        if (getSupportActionBar() != null)
        getSupportActionBar().hide();
    }
}