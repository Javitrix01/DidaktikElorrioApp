package com.elorrieta.didaktikelorrioapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.Gravity;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import com.elorrieta.didaktikelorrioapp.appdatabase.AppDatabase;

public class Puzzle_Pasteleria extends AppCompatActivity {

    /**
     * Pantalla en la que tienen que clickar en los diferentes ingredientes.
     * Tienen un boton para comprobar las respuestas, una vez lo tengan bien pasan
     * de actividad.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle_pasteleria);

        //Esto quita la barra de visualizacion arriba
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        //crea una instancia de la base de datos
        AppDatabase db = AppDatabase.getDatabase(getApplicationContext());

        //Esto encuentra la imagen
        ImageButton imageButton1 = findViewById(R.id.imageQueso);
        //Esto es una instancia para poner que la imagen no esta clickada de primeras
        final boolean[] isImageButtonClicked1 = {false};

        //Metodo para cambiar la transparencia si es clickado o no
        imageButton1.setOnClickListener(v -> {
            isImageButtonClicked1[0] = !isImageButtonClicked1[0];
            if (isImageButtonClicked1[0]) {
                imageButton1.setAlpha(0.5f);
            } else {
                imageButton1.setAlpha(1f);
            }
        });
        //Metodo para cambiar la transparencia si es clickado o no
        ImageButton imageButton2 = findViewById(R.id.imageHuevo);
        final boolean[] isImageButtonClicked2 = {false};
        imageButton2.setOnClickListener(v -> {
            isImageButtonClicked2[0] = !isImageButtonClicked2[0];
            if (isImageButtonClicked2[0]) {
                imageButton2.setAlpha(0.5f);
            } else {
                imageButton2.setAlpha(1f);
            }
        });
        //Metodo para cambiar la transparencia si es clickado o no
        ImageButton imageButton3 = findViewById(R.id.imageChocolateBlanco);
        final boolean[] isImageButtonClicked3 = {false};
        imageButton3.setOnClickListener(v -> {
            isImageButtonClicked3[0] = !isImageButtonClicked3[0];
            if (isImageButtonClicked3[0]) {
                imageButton3.setAlpha(0.5f);
            } else {
                imageButton3.setAlpha(1f);
            }
        });
        //Metodo para cambiar la transparencia si es clickado o no
        ImageButton imageButton4 = findViewById(R.id.imageHarina);
        final boolean[] isImageButtonClicked4 = {false};
        imageButton4.setOnClickListener(v -> {
            isImageButtonClicked4[0] = !isImageButtonClicked4[0];
            if (isImageButtonClicked4[0]) {
                imageButton4.setAlpha(0.5f);
            } else {
                imageButton4.setAlpha(1f);
            }
        });
        //Metodo para cambiar la transparencia si es clickado o no
        ImageButton imageButton5 = findViewById(R.id.imagePera);
        final boolean[] isImageButtonClicked5 = {false};
        imageButton5.setOnClickListener(v -> {
            isImageButtonClicked5[0] = !isImageButtonClicked5[0];
            if (isImageButtonClicked5[0]) {
                imageButton5.setAlpha(0.5f);
            } else {
                imageButton5.setAlpha(1f);
            }
        });
        //Metodo para cambiar la transparencia si es clickado o no
        ImageButton imageButton6 = findViewById(R.id.imageManzana);
        final boolean[] isImageButtonClicked6 = {false};
        imageButton6.setOnClickListener(v -> {
            isImageButtonClicked6[0] = !isImageButtonClicked6[0];
            if (isImageButtonClicked6[0]) {
                imageButton6.setAlpha(0.5f);
            } else {
                imageButton6.setAlpha(1f);
            }
        });
        //Metodo para cambiar la transparencia si es clickado o no
        ImageButton imageButton7 = findViewById(R.id.imageAzucar);
        final boolean[] isImageButtonClicked7 = {false};
        imageButton7.setOnClickListener(v -> {
            isImageButtonClicked7[0] = !isImageButtonClicked7[0];
            if (isImageButtonClicked7[0]) {
                imageButton7.setAlpha(0.5f);
            } else {
                imageButton7.setAlpha(1f);
            }
        });
        //Metodo para cambiar la transparencia si es clickado o no
        ImageButton imageButton8 = findViewById(R.id.imageChocolateNegro);
        final boolean[] isImageButtonClicked8 = {false};
        imageButton8.setOnClickListener(v -> {
            isImageButtonClicked8[0] = !isImageButtonClicked8[0];
            if (isImageButtonClicked8[0]) {
                imageButton8.setAlpha(0.5f);
            } else {
                imageButton8.setAlpha(1f);
            }
        });
        //Metodo para cambiar la transparencia si es clickado o no
        ImageButton imageButton9 = findViewById(R.id.imageMermelada);
        final boolean[] isImageButtonClicked9 = {false};
        imageButton9.setOnClickListener(v -> {
            isImageButtonClicked9[0] = !isImageButtonClicked9[0];
            if (isImageButtonClicked9[0]) {
                imageButton9.setAlpha(0.5f);
            } else {
                imageButton9.setAlpha(1f);
            }
        });
        //Variables para usarlos abajo y metodo para verificar si estan bien los ingredientes o mal
        MediaPlayer victory = MediaPlayer.create(this, R.raw.victory);
        MediaPlayer error = MediaPlayer.create(this, R.raw.error);
        Button checkButton = findViewById(R.id.btnPasteleriaBaieztatu);
        Toast toast = new Toast(this);
        ImageView view1 = new ImageView(this);
        checkButton.setOnClickListener(v -> {
            if (isImageButtonClicked7[0] && isImageButtonClicked3[0] && isImageButtonClicked8[0] && isImageButtonClicked2[0] && isImageButtonClicked4[0] && !isImageButtonClicked1[0] && !isImageButtonClicked5[0] && !isImageButtonClicked6[0] && !isImageButtonClicked9[0]) {
                //Por aqui es que estan bien los ingredientes y pone un tick verde en grande
                view1.setImageResource(R.drawable.tickverde);
                toast.setView(view1);
                toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
                toast.show();
                //Suena el audio de que esta correcto
                victory.start();
                //Para actualizar la base de datos
                db.activityDao().updateActivityVisitedById(4, true);
                Handler handler = new Handler();
                handler.postDelayed(() -> startActivity(new Intent(Puzzle_Pasteleria.this, parada5_presentacion.class)), 2200); //3 segundos
            } else {
                //Por aqui es que estan mal los ingredientes y pone una x roja en grande
                view1.setImageResource(R.drawable.xroja1);
                toast.setView(view1);
                toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
                toast.show();
                //Suena el audio de que esta incorrecto y vibra
                error.start();
                vibrator.vibrate(1000);
            }
        });

    }
}