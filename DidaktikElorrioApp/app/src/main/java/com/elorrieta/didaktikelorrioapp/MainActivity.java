package com.elorrieta.didaktikelorrioapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import org.w3c.dom.Text;

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
        TextView textViewTitulo = findViewById(R.id.textViewTitulo);
        String text = "<font color=#FF0000>D</font><font color=#FF7F00>i</font><font color=#FFFF00>d</font><font color=#00FF00>a</font>"+
                      "<font color=#0000FF>k</font><font color=#4B0082>t</font><font color=#9400D3>i</font><font color=#FF0000>k</font>"+
                      "<font color=#FF7F00>A</font><font color=#FFFF00>p</font><font color=#00FF00>p</font><font color=#0000FF>!</font>";
        textViewTitulo.setText(Html.fromHtml(text));

        //la pone visible
        progressBar.getProgress();

        //lama a la funcion para que pase a la siguiente pantalla
        carga(control);
    }

    //funcion que espera un tiempo especifico y pasa a la siguiente pantalla
    protected void carga(boolean control){
        if(control) {
            //se queda en espera unos segundos y pasa a la siguiente pantalla
            Handler handler = new Handler();
            //handler.postDelayed(() -> startActivity(new Intent(MainActivity.this, MenuPrincipalMapa.class)), 2500); //2,5 segundos
            //handler.postDelayed(this::finish,3000); //3 segundos
        }
        MainActivity.control = false;
    }

    //CÓDIGO DE PRUEBA DE LA BASE DE DATOS - NO BORRAR
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
}