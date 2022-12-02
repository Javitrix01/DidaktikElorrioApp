package com.elorrieta.didaktikelorrioapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.room.Room;
import androidx.transition.Fade;
import androidx.transition.Transition;

import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.elorrieta.didaktikelorrioapp.appdatabase.AppDatabase;
import com.elorrieta.didaktikelorrioapp.entities.Activity;

import java.util.ArrayList;
import java.util.List;

public class MenuPrincipalMapa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal_mapa);

        //oculta la barra de arriba
        getSupportActionBar().hide();

        //asignaciones de elementos del layout a variables
        Button botonAbrirMenu = findViewById(R.id.btnMenuActividades);
        Button botonAbrirMapa = findViewById(R.id.btnMapa);
        ListView lista = findViewById(R.id.listaMenuPrincipal);

        //propiedades asignadas al crearse la actividad
        lista.setX(-200);
        lista.setVisibility(View.INVISIBLE);

        //funcion que carga las actividades en la lista
        cargarActividadesLista(lista);

        //accion del boton para ver la lista
        botonAbrirMenu.setOnClickListener(view -> {
            //aplica la animacion para cuando aparezca la lista
            lista.animate().translationX(0);
            lista.setVisibility(View.VISIBLE);
        });
        //accion del boton para volver al mapa
        botonAbrirMapa.setOnClickListener(view -> {
            //aplica la animacion para cuando desaparezca la lista
            lista.animate().translationX(-200);
            lista.setVisibility(View.INVISIBLE);
        });
    }

    /**
     * Funcion para cargar las acitivades en la lista y que se puedan visualizar.
     *
     * @param   lista   Lista en la que se van a cargar las actividades.
     */
    public void cargarActividadesLista(ListView lista){
        ArrayList<String> prueba = new ArrayList<>();



        List<Activity> actividades;

        for (int i=0; i<7; i++){
            String stringVariable = (i+1)+". aktibitatea: \""+actividades[i]+"\"";
            prueba.add(stringVariable);
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(getApplicationContext(), R.layout.list_card, prueba);
        lista.setAdapter(arrayAdapter);
    }
}