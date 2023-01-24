package com.elorrieta.didaktikelorrioapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.elorrieta.didaktikelorrioapp.appdatabase.AppDatabase;

public class Errebonbiloa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_errebonbiloa);

        //crea una instancia de la base de datos
        AppDatabase db = AppDatabase.getDatabase(getApplicationContext());

        Button botonPasar = findViewById(R.id.btnJarraituErre);
        botonPasar.setOnClickListener(view -> { db.activityDao().updateActivityVisitedById(6, true); startActivity(new Intent(Errebonbiloa.this, MenuPrincipalMapa.class)); });

        getSupportActionBar().hide();
    }
}