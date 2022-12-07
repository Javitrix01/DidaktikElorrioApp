package com.elorrieta.didaktikelorrioapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.elorrieta.didaktikelorrioapp.appdatabase.AppDatabase;
import com.elorrieta.didaktikelorrioapp.entities.Activity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //variable para el control de la funcion carga()
    private static boolean control = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //oculta la barra de arriba
        getSupportActionBar().hide();

        final ProgressBar progressBar = findViewById(R.id.progressBar3);

        //Cambia los colores del texto
        //DidaktikApp!
        TextView textViewTitulo = findViewById(R.id.textViewTituloAct);
        String text = "<font color=#FF0000>D</font><font color=#FF7F00>i</font><font color=#FFCC00>d</font><font color=#00FF00>a</font>"+
                      "<font color=#0000FF>k</font><font color=#4B0082>t</font><font color=#9400D3>i</font><font color=#FF0000>k</font>"+
                      "<font color=#FF7F00>A</font><font color=#FFCC00>p</font><font color=#00FF00>p</font><font color=#0000FF>!</font>";
        textViewTitulo.setText(Html.fromHtml(text));

        //la pone visible
        progressBar.getProgress();

        //lama a la funcion para que pase a la siguiente pantalla
        carga(control);
    }

    /**
     * Funcion que espera un tiempo especifico y pasa a la siguiente pantalla.
     *
     * @param   control Variable para controlar que la carga solo se realiza una vez.
    */
    protected void carga(boolean control){
        //comprueba la base de datos
        llamadaBD();

        if(control) {
            //se queda en espera unos segundos y pasa a la siguiente pantalla
            Handler handler = new Handler();
            handler.postDelayed(() -> startActivity(new Intent(MainActivity.this, MenuPrincipalMapa.class)), 3000); //3 segundos
            handler.postDelayed(this::finish,3500); //3,5 segundos
        }
        MainActivity.control = false;
    }

    /**
     * Funcion para llamar a la base de datos, comprobar si faltan datos y guardar los necesarios.
     */
    public void llamadaBD(){

        //crea las actividades necesarias
        Activity activity1 = new Activity(1, "KURUTZIAGAKO GURUTZEA", "a", "43° 7′ 41.37′′ N", "2° 32′ 23.28′′ W" , false);
        Activity activity2 = new Activity(2, "SORTZEZ GARBIAREN BASILIKA", "a", "43° 7′ 48.7′′ N", "2° 32′ 33.32′′ W" , false);
        Activity activity3 = new Activity(3, "ELORRIOKO UDALETXEA", "a", "43° 7′ 49.89′′ N", "2° 32′ 31.44′′ W" , false);
        Activity activity4 = new Activity(4, "PELLO GOZOTEGIA", "a", "43° 7′ 51.51′′ N", "2° 32′ 35.69′′ W" , false);
        Activity activity5 = new Activity(5, "ARESPAKOTXAGA JAUREGIA", "a", "43° 7′ 50.49′′ N", "2° 32′ 38.06′′ W" , false);
        Activity activity6 = new Activity(6, "ERREBONBILLOAK", "a", "43° 7′ 48.49′′ N", "2° 32′ 31.31′′ W" , false);
        Activity activity7 = new Activity(7, "ARGINETAKO NEKROPOLIA", "a", "43° 8′ 23.66′′ N", "2° 32′ 9.51′′ W" , false);

        //crea una lista con todas las actividades
        List<Activity> actividades = new ArrayList<>();
        actividades.add(activity1);
        actividades.add(activity2);
        actividades.add(activity3);
        actividades.add(activity4);
        actividades.add(activity5);
        actividades.add(activity6);
        actividades.add(activity7);

        //crear una instancia de la base de datos
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "DidaktikAppDB").allowMainThreadQueries().build(); // <-- poner esto para que permita realizar consultas

        //por cada actividad la borra de la base de datos para que no haya inconsistencias y la añade
        for (Activity activity : actividades){
            db.activityDao().deleteActivity(activity);
            db.activityDao().insertActivity(activity);
        }

        //comprueba si existen las actividades necesarias
        List<Activity> comprobacionActividades;
        comprobacionActividades = db.activityDao().getAll();
        Log.d("DEBUGG-DB", ""+comprobacionActividades.size());

        //comprueba que no falte ninguna actividad, si falta alguna la añade
        for (int i=0; i<comprobacionActividades.size(); i++){
            if (comprobacionActividades.get(i).getId() != actividades.get(i).getId()){
                db.activityDao().insertActivity(actividades.get(i));
            }
        }
    }

    //TODO: CÓDIGO DE PRUEBA DE LA BASE DE DATOS - NO BORRAR
    /*
        //asignar el textview a la variable por el id
        textViewDB = findViewById(R.id.textViewDB);

        //crear una lista de usuarios para poder manejarla mas tarde
        List<User> userList;

        //crear los objetos user
        User user1 = new User(1, "nombre1", "apellido1", "email1@email.com");


        //User user2 = new User(2, "nombre2", "apellido2", "email2@email.com");
        //User user3 = new User(3, "nombre3", "apellido3", "email3@email.com");

        //crear una instancia de la base de datos
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "pruebaDB").allowMainThreadQueries().build(); // <-- poner esto para que permita realizar consultas

        //insertar todos los usuarios

            //db.userDao().insertAll(user1, user2, user3);


        //borrar un usuario de la base de datos
            db.userDao().delete(user1);

        //recuperar usuarios de la base de datos
        userList = db.userDao().getAll();

        //imprimirlo por pantalla
            for (User user : userList) {
            textViewDB.setText(textViewDB.getText()+user.toString()+"\n");
        }
    */

    /*
        COMO HACER UNA RELACION ENTRE DOS TABLAS

        En una entidad intermedia que se crea por la relacion de las otras 2 entidades ->
        @Entity(primaryKeys = {"id1","id2"])
        Para hacer consultas entre entidades
        @Query("SELECT * FROM entidad1 INNER JOIN entidad2 ON entidad2.id = entidad1.id")
        List<entidad1> query();
    */

    /*
        Comentar las creadciones de los objetos de la base de datos y sus inserts
        Exportar la base de datos -> meterla en assets ->
        en el archivo Appdatabase -> añadir .createFromAsset("path.db")
        Appdatabase appDatabase = Room.database
     */
}