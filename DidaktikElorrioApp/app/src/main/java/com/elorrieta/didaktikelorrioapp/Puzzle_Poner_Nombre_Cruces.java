package com.elorrieta.didaktikelorrioapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.media.MediaPlayer;
import com.elorrieta.didaktikelorrioapp.Class.Sound;
import com.elorrieta.didaktikelorrioapp.appdatabase.AppDatabase;

public class Puzzle_Poner_Nombre_Cruces extends AppCompatActivity {
    TextView nombre1,nombre2,nombre3,nombre4,nombre5,nombre6,nombre7,faltante;
    Button btn_comprobar;
    Sound sonido;
    MediaPlayer vectorAcierto=new MediaPlayer();
    MediaPlayer vectorError=new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle_poner_nombre_cruces);


        faltante = findViewById(R.id.et_Iguriako_Gurutzea);

        //Variables inicializadas
        nombre1 = findViewById(R.id.et_Kurutzebarri);
        nombre2 = findViewById(R.id.et_Ganondo);
        nombre3 = findViewById(R.id.et_SanJuan);
        nombre4 = findViewById(R.id.et_kurutziaga);
        nombre5 = findViewById(R.id.et_Kurutzondo);
        nombre6 = findViewById(R.id.et_SantaElena);
        nombre7 = findViewById(R.id.et_Txanberi);
        btn_comprobar = findViewById(R.id.btnJarraituPuzCrucesNombre);
        vectorAcierto = MediaPlayer.create(this, R.raw.victory);
        vectorError = MediaPlayer.create(this, R.raw.error);
        //Boton esperando
        btn_comprobar.setOnClickListener(view -> {
            //Variables cargadas con los string recoguidos de sus respectivas cajas de texto
            String text1=nombre1.getText().toString().trim();
            String text2=nombre2.getText().toString().trim();
            String text3=nombre3.getText().toString().trim();
            String text4=nombre4.getText().toString().trim();
            String text5=nombre5.getText().toString().trim();
            String text6=nombre6.getText().toString().trim();
            String text7=nombre7.getText().toString().trim();
            //if para enviar las variables con los strings a la funcion y determinar si es correcto
            if(!Comprobar(text1,text2,text3,text4,text5,text6,text7)){
                if(vectorError.isPlaying()){
                    vectorError.pause();
                }else{
                    vectorError.start();
                    if(Sound.error!=null) {
                        if (Sound.error.isPlaying()) {
                            sonido.switchMusic(Sound.gameMusic, Sound.error);
                        }
                    }
                }
                faltante.setText("Errorea");

            }else{
                if(vectorAcierto.isPlaying()){
                    vectorAcierto.pause();
                }else {
                    vectorAcierto.start();
                    if(Sound.winningSound!=null) {
                        if (Sound.winningSound.isPlaying()) {
                            sonido.switchMusic(Sound.gameMusic, Sound.winningSound);
                        }
                    }
                }
                faltante.setText("Iguriako");
                //crea una instancia de la base de datos
                AppDatabase db = AppDatabase.getDatabase(getApplicationContext());
                //actualiza el estado de la actividad en la base de datos
                db.activityDao().updateActivityVisitedById(1, true);
                //inicia la actividad del mapa principal
                startActivity(new Intent(Puzzle_Poner_Nombre_Cruces.this, MenuPrincipalMapa.class));
            }

        });
    }

    /**
     * Funcion que comprueba los valores introducidos por el usuario en los TextViews
     * @param nombres   Lista de los nombres puestos por el usuario.
     * @return          True si la comprobacion es correcta, false si hay errores.
     */
    public boolean Comprobar(String... nombres){
        //array con todos los valores para la comprobacion
        String[] array_comprobacion = new String[]{"KB", "GD", "SJ", "KT", "KO", "SE", "TB"};
        int comprobacion = 0;
        //Comprobaciones de los strings
        for (int i=0; i<array_comprobacion.length; i++){
            if (array_comprobacion[i].equals(nombres[i])){
                comprobacion++;
            }
        }
        if (comprobacion == 7){return true;}
        return false;
    }

}