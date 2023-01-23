package com.elorrieta.didaktikelorrioapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.elorrieta.didaktikelorrioapp.appdatabase.AppDatabase;

public class Puzzle_Mapa_Elorrio_Poner_Nombres extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle_mapa_elorrio_poner_nombres);
        getSupportActionBar().hide();

        //inicia una instancia de la base de datos
        AppDatabase db = AppDatabase.getDatabase(getApplicationContext());

        EditText et5=findViewById(R.id.et_5);
        EditText et3=findViewById(R.id.et_3);
        EditText et7=findViewById(R.id.et_7);
        EditText et1=findViewById(R.id.et_1);
        EditText et2=findViewById(R.id.et_2);
        EditText et4=findViewById(R.id.et_4);
        EditText et6=findViewById(R.id.et_6);


        MediaPlayer victory = MediaPlayer.create(this, R.raw.victory);
        MediaPlayer error = MediaPlayer.create(this, R.raw.error);

        /* Validacion de los resultados del puzzle*/
        Button btnBaieztatu = findViewById(R.id.btnBaieztatuNombresElorrio);
        btnBaieztatu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(et5.getText().toString().equals("5")){

                }else{
                    Toast.makeText(Puzzle_Mapa_Elorrio_Poner_Nombres.this, "Error en BainuEtxea", Toast.LENGTH_LONG).show();
                }
                if(et3.getText().toString().equals("3")){

                }else{
                    Toast.makeText(Puzzle_Mapa_Elorrio_Poner_Nombres.this, "Error en Zearsolo jauregia", Toast.LENGTH_LONG).show();
                }
                if(et7.getText().toString().equals("7")){

                }else{
                    Toast.makeText(Puzzle_Mapa_Elorrio_Poner_Nombres.this, "Error en Urkizu Tola Jauregia", Toast.LENGTH_LONG).show();
                }
                if(et1.getText().toString().equals("1")){

                }else{
                    Toast.makeText(Puzzle_Mapa_Elorrio_Poner_Nombres.this, "Error en Arespakotxaga Jauregia", Toast.LENGTH_LONG).show();
                }
                if(et2.getText().toString().equals("2")){

                }else{
                    Toast.makeText(Puzzle_Mapa_Elorrio_Poner_Nombres.this, "Error en Arespakotxaga Azkarraga", Toast.LENGTH_LONG).show();
                }
                if(et4.getText().toString().equals("4")){

                }else{
                    Toast.makeText(Puzzle_Mapa_Elorrio_Poner_Nombres.this, "Error en Olazabal Jauregia", Toast.LENGTH_LONG).show();
                } if(et6.getText().toString().equals("6")){

                }else{
                    Toast.makeText(Puzzle_Mapa_Elorrio_Poner_Nombres.this, "Error en Urkizu Aldatsekua Jauregia", Toast.LENGTH_LONG).show();
                }
                if (et5.getText().toString().equals("5")&& et3.getText().toString().equals("3")&& et7.getText().toString().equals("7")&& et1.getText().toString().equals("1")&& et2.getText().toString().equals("2")&& et4.getText().toString().equals("4")&& et6.getText().toString().equals("6")){
                    Toast.makeText(Puzzle_Mapa_Elorrio_Poner_Nombres.this, "Oso Ondo", Toast.LENGTH_LONG).show();
                    victory.start();
                    db.activityDao().updateActivityVisitedById(5, true);
                    Handler handler = new Handler();
                    handler.postDelayed(() -> startActivity(new Intent(Puzzle_Mapa_Elorrio_Poner_Nombres.this, parada6_presentacion.class)), 2200);
                }
            }
        });



    }
}