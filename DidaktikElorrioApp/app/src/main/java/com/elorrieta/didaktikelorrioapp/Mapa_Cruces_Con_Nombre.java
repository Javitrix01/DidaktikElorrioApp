package com.elorrieta.didaktikelorrioapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class Mapa_Cruces_Con_Nombre extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_puzzle_poner_nombre_cruces);

        Button button = findViewById(R.id.btnJarraituPuzCrucesNombre);
        button.setOnClickListener(this::Cruces);
    }
            public void Cruces (View view){
                EditText Ganondo = (EditText) findViewById(R.id.et_Ganondo);
                EditText Iguriako_Gurutzea = (EditText) findViewById(R.id.et_Iguriako_Gurutzea);
                EditText Kurutzebarri = (EditText) findViewById(R.id.et_Kurutzebarri);
                EditText kurutziaga = (EditText) findViewById(R.id.et_kurutziaga);
                EditText Kurutzondo = (EditText) findViewById(R.id.et_Kurutzondo);
                EditText SanJuan = (EditText) findViewById(R.id.et_SanJuan);
                EditText SantaElena = (EditText) findViewById(R.id.et_SantaElena);
                EditText Txanberi = (EditText) findViewById(R.id.et_Txanberi);
                EditText Iguriako = (EditText) findViewById(R.id.et_Iguriako_Gurutzea);


                if ((Ganondo.getText().toString().equals("Ganondo")) && (Iguriako_Gurutzea.getText().toString().equals("Iguriako Gurutzea"))
                        && (Kurutzebarri.getText().toString().equals("Kurutzebarri")) && (kurutziaga.getText().toString().equals("kurutziaga"))
                        && (Kurutzondo.getText().toString().equals("Kurutzondo")) && (SanJuan.getText().toString().equals("SanJuan"))
                        && (SantaElena.getText().toString().equals("SantaElena")) && (Txanberi.getText().toString().equals("Txanberi"))
                        && (Iguriako.getText().toString().equals("Iguriako"))) {
                    Snackbar.make(view, "Correcto", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    Intent k = new Intent(Mapa_Cruces_Con_Nombre.this,MainPuzzle. class);
                    startActivity(k);
                    this.finish();
                }
                else {
                    Snackbar.make(view, "Cruzes mal puestas.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        }
