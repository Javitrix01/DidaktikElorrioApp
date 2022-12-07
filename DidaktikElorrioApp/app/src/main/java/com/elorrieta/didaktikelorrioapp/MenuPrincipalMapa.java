package com.elorrieta.didaktikelorrioapp;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.elorrieta.didaktikelorrioapp.appdatabase.AppDatabase;

import java.util.ArrayList;

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
        lista.setClickable(true);
        lista.setVisibility(View.INVISIBLE);

        //funcion que carga las actividades en la lista
        cargarActividadesLista(lista, botonAbrirMenu, botonAbrirMapa);

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
     * @param lista          Lista en la que se van a cargar las actividades.
     * @param botonAbrirMenu Boton que abre el menu de la lista de actividades.
     * @param botonAbrirMapa Boton que cierra el menu de actividades y vuelve al mapa.
     */
    public void cargarActividadesLista(ListView lista, Button botonAbrirMenu, Button botonAbrirMapa) {
        //crea un arraylist para guardar el texto en la lista
        ArrayList<String> listaActividades = new ArrayList<>();

        //asigna la instancia de la base de datos
        AppDatabase db = AppDatabase.getDatabase(getApplicationContext());
        //recoge todas las actividades de la base de datos
        List<Activity> actividades = db.activityDao().getAll();

        //guarda el texto de todas las actividades para mostrarlas en la lista
        for (int i = 0; i < 7; i++) {
            String stringVariable = (i + 1) + ". aktibitatea: \"" + actividades.get(i).getActivity_name() + "\"";
            listaActividades.add(stringVariable);
        }

        //crea el adaptador para la lista
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(getApplicationContext(), R.layout.list_card, listaActividades);
        lista.setAdapter(arrayAdapter);

        //crea un escuchador para que se pueda mostrar la informacion de las actividades cuando se hace click en ellas
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //aplica la animacion para cuando desaparezca la lista
                lista.animate().translationX(-200);
                lista.setVisibility(View.INVISIBLE);
                //desactiva los botones
                botonAbrirMapa.setClickable(false);
                botonAbrirMenu.setClickable(false);

                //abre la nueva actividad
                FrameLayout rootLayout = findViewById(android.R.id.content);
                View.inflate(getApplicationContext(), R.layout.activity_info_del_menu, rootLayout);

                //asigna los TextView
                TextView tvTitulo = findViewById(R.id.textViewTituloAct);
                TextView tvDescripcion = findViewById(R.id.textViewDescripcion);

                //añade la informacion
                tvTitulo.setText(actividades.get(i).getActivity_name());
                tvDescripcion.setText(actividades.get(i).getActivity_description());

                //TODO: informacion del mapa

                Button botonVolver = findViewById(R.id.btnitzuli);
                botonVolver.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //activa los botones
                        botonAbrirMapa.setClickable(true);
                        botonAbrirMenu.setClickable(true);

                        //esconde la actividad
                        FrameLayout rootLayout = findViewById(android.R.id.content);
                        rootLayout.removeViewAt(rootLayout.getChildCount() - 1);
                    }
                });
            }
        });
    }
}

/*
    https://gist.github.com/joshdholtz/4522551
    https://stackoverflow.com/questions/16536414/how-to-use-mapview-in-android-using-google-map-v2
 */