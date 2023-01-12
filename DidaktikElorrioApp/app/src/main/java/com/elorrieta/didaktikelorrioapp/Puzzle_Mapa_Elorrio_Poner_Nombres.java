package com.elorrieta.didaktikelorrioapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Puzzle_Mapa_Elorrio_Poner_Nombres extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle_mapa_elorrio_poner_nombres);


        EditText et5=findViewById(R.id.et_5);
        EditText et3=findViewById(R.id.et_3);
        EditText et7=findViewById(R.id.et_7);
        EditText et1=findViewById(R.id.et_1);
        EditText et2=findViewById(R.id.et_2);
        EditText et4=findViewById(R.id.et_4);
        EditText et6=findViewById(R.id.et_6);

        et5.getText().toString();
        et3.getText().toString();
        et7.getText().toString();
        et1.getText().toString();
        et2.getText().toString();
        et4.getText().toString();
        et6.getText().toString();

        Button btnBaieztatu = findViewById(R.id.btnBaieztatuNombresElorrio);
        btnBaieztatu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(!et5.equals("5")){
                    Toast.makeText(Puzzle_Mapa_Elorrio_Poner_Nombres.this, "BainuEtxea ondo", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(Puzzle_Mapa_Elorrio_Poner_Nombres.this, "Error en BainuEtxea", Toast.LENGTH_LONG).show();
                }
                if(!et3.equals("3")){
                    Toast.makeText(Puzzle_Mapa_Elorrio_Poner_Nombres.this, "Zearsolo jauregia ondo", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(Puzzle_Mapa_Elorrio_Poner_Nombres.this, "Error en Zearsolo jauregia", Toast.LENGTH_LONG).show();
                }
                if(!et7.equals("7")){
                    Toast.makeText(Puzzle_Mapa_Elorrio_Poner_Nombres.this, "Urkizu Tola Jauregia ondo", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(Puzzle_Mapa_Elorrio_Poner_Nombres.this, "Error en Urkizu Tola Jauregia", Toast.LENGTH_LONG).show();
                }
                if(!et1.equals("1")){
                    Toast.makeText(Puzzle_Mapa_Elorrio_Poner_Nombres.this, "Arespakotxaga Jauregia ondo", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(Puzzle_Mapa_Elorrio_Poner_Nombres.this, "Error en Arespakotxaga Jauregia", Toast.LENGTH_LONG).show();
                }
                if(!et2.equals("2")){
                    Toast.makeText(Puzzle_Mapa_Elorrio_Poner_Nombres.this, "Arespakotxaga Azkarraga ondo", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(Puzzle_Mapa_Elorrio_Poner_Nombres.this, "Error en Arespakotxaga Azkarraga", Toast.LENGTH_LONG).show();
                }
                if(!et4.equals("4")){
                    Toast.makeText(Puzzle_Mapa_Elorrio_Poner_Nombres.this, "Olazabal Jauregia ondo", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(Puzzle_Mapa_Elorrio_Poner_Nombres.this, "Error en Olazabal Jauregia", Toast.LENGTH_LONG).show();
                } if(!et6.equals("6")){
                    Toast.makeText(Puzzle_Mapa_Elorrio_Poner_Nombres.this, "Urkizu Aldatsekua Jauregia ondo", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(Puzzle_Mapa_Elorrio_Poner_Nombres.this, "Error en Urkizu Aldatsekua Jauregia", Toast.LENGTH_LONG).show();
                }
                /*if (et5.equals("5")&& et3.equals("3")&& et7.equals("7")&& et1.equals("1")&& et2.equals("2")&& et4.equals("4")&& et6.equals("6")){
                    Toast.makeText(Puzzle_Mapa_Elorrio_Poner_Nombres.this, "Oso Ondo", Toast.LENGTH_LONG).show();

                }*/
            }
        });



    }
}