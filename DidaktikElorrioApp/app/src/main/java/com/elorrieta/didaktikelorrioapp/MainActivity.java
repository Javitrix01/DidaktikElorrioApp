package com.elorrieta.didaktikelorrioapp;



import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

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
        if (getSupportActionBar() != null) { getSupportActionBar().hide(); }

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

        //no pasa de pantalla hasta que se responda a los permisos
        boolean permisos = true;
        do {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 0);
            }
            else if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                permisos = false;
            }
        } while (permisos);

        if(control) {
            //se queda en espera unos segundos y pasa a la siguiente pantalla
            Handler handler = new Handler();
            handler.postDelayed(() -> startActivity(new Intent(MainActivity.this, MainPuzzle.class)), 3000); //3 segundos

            handler.postDelayed(this::finish,3500); //3,5 segundos
        }
        MainActivity.control = false;
    }

    /**
     * Funcion para llamar a la base de datos, comprobar si faltan datos y guardar los necesarios.
     */
    public void llamadaBD(){

        //crea las actividades necesarias
        Activity activity1 = new Activity(1, "KURUTZIAGAKO GURUTZEA", "Kurutziagako gurutzea, gurutze errenazentista bat da eta XVI. mendean sortu zen, 1522. urtean.", "-2.5397199", "43.1281365" , false);
        Activity activity2 = new Activity(2, "SORTZEZ GARBIAREN BASILIKA", "Sortzez Garbiaren Basilika Bizkaiko tenplurik handiena da eta Elorrioko plazan aurkitu dezakezu.", "-002.5425889", "43.1301944" , false);
        Activity activity3 = new Activity(3, "ELORRIOKO UDALETXEA", "Elorrioko udaletxea Gernikako Arbola plazan kokatuta dago eta XVIII. mendean eraiki zuten.", "-002.5420667", "43.1305250" , false);
        Activity activity4 = new Activity(4, "PELLO GOZOTEGIA", "Reliquias de San Valentín bizkotxo zoragarriak dira,  usain sekretudunak, txokolate zurizko geruza fin batez eta esnedun txokolate txorrotada batez estaliak.", "-002.5432472", "43.1309750" , false);
        Activity activity5 = new Activity(5, "ARESPAKOTXAGA JAUREGIA", "Arespakotxaga-Mendibil jauregia oinplano angeluzuzeneko eraikina da, Elorrioko alde zaharreko Don Tello kalean kokatuta dago.", "-002.5439056", "43.1306917" , false);
        Activity activity6 = new Activity(6, "ERREBONBILLOAK", "Errebonbilloaren estatua Elorrioko plaza ondoan aurkitzen da 2003tik. Honek, ia ondare guztiak bezala, istorio interesgarri eta polit bat dauka.", "-002.5420306", "43.1301361" , false);
        Activity activity7 = new Activity(7, "ARGINETAKO NEKROPOLIA", "Argiñetako nekropolia Elorrioko San Adrian de Argiñeta ermitan aurki dezakezun antzinako hilerri erromantikoa da.", "-002.5359750", "43.1399056" , false);

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
        AppDatabase db = AppDatabase.getDatabase(getApplicationContext());

        //por cada actividad la borra de la base de datos para que no haya inconsistencias y la añade
//        for (Activity activity : actividades){
//            db.activityDao().deleteActivity(activity);
//            db.activityDao().insertActivity(activity);
//        }


        //comprueba si existen las actividades necesarias
        List<Activity> comprobacionActividades;
        comprobacionActividades = db.activityDao().getAll();

        //comprueba que no falte ninguna actividad, si falta alguna la añade
        for (int i=0; i<actividades.size(); i++){
            if (actividades.get(i).getId() != comprobacionActividades.get(i).getId()){
                db.activityDao().insertActivity(actividades.get(i));
            }
        }
    }
}