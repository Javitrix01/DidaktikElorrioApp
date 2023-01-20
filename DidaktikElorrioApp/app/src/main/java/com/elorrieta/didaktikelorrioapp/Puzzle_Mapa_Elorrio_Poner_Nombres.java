package com.elorrieta.didaktikelorrioapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Puzzle_Mapa_Elorrio_Poner_Nombres extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle_mapa_elorrio_poner_nombres);
        getSupportActionBar().hide();

        EditText et5=findViewById(R.id.et_5);
        EditText et3=findViewById(R.id.et_3);
        EditText et7=findViewById(R.id.et_7);
        EditText et1=findViewById(R.id.et_1);
        EditText et2=findViewById(R.id.et_2);
        EditText et4=findViewById(R.id.et_4);
        EditText et6=findViewById(R.id.et_6);


        //comprobaciones de las respuestas del puzzle
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

                }
            }
        });



    }
}