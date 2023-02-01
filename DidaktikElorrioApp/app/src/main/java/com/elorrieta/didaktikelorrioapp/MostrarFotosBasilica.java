package com.elorrieta.didaktikelorrioapp;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;



import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;

import android.view.Gravity;
import android.widget.Button;
import android.widget.ImageButton;

import android.widget.ImageView;
import android.widget.Toast;

import com.elorrieta.didaktikelorrioapp.appdatabase.AppDatabase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class MostrarFotosBasilica extends AppCompatActivity {

    /**
     * Pantalla en la que tienen que sacar varias fotos de la basilica,
     * cuando las sacan todas tienen un boton para pasar a la siguiente actividad.
     */

    //variables a nivel de clase
    Button btnGuardar;
    ImageButton imgView_1I;
    ImageButton imgView_1D;
    ImageButton imgView_2I;
    ImageButton imgView_2D;
    ImageButton imgView_3I;
    ImageButton imgView_3D;
    boolean[] comp_imagenes_sacadas = new boolean[] {false, false, false, false, false, false};

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //crea una instancia de la base de datos
        AppDatabase db = AppDatabase.getDatabase(getApplicationContext());

        //comprobacion de permisos
        String[] permissionsStorage = {WRITE_EXTERNAL_STORAGE};
        int requestExternalStorage = 1;
        int permission = ActivityCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, permissionsStorage, requestExternalStorage);
        }
        //esconde la barra superior de la aplicacion
        if (getSupportActionBar() != null)
        getSupportActionBar().hide();

        //asignaciones de variables con el layout
        setContentView(R.layout.activity_mostrar_fotos_basilika);
        btnGuardar = findViewById(R.id.GuardarBtn);
        imgView_1I = findViewById(R.id.imageButton1I);
        imgView_1D = findViewById(R.id.imageButton1D);
        imgView_2I = findViewById(R.id.imageButton2I);
        imgView_2D = findViewById(R.id.imageButton2D);
        imgView_3I = findViewById(R.id.imageButton3I);
        imgView_3D = findViewById(R.id.imageButton3D);
        imgView_1I.setClickable(false);

        //funcionalidad del boton
        //inicia el array enetero de comprobacion a false
        for (boolean i : comp_imagenes_sacadas){
            i = false;
        }

        //Funcion del boton:
        //Guardar las fotos visibles en pantalla en la galeria
        btnGuardar.setOnClickListener(view -> {
            Bitmap bitmap1D = ((BitmapDrawable)imgView_1D.getDrawable()).getBitmap();
            saveImage(bitmap1D);
            Bitmap bitmap1I = ((BitmapDrawable)imgView_1I.getDrawable()).getBitmap();
            saveImage(bitmap1I);
            Bitmap bitmap2I = ((BitmapDrawable)imgView_2I.getDrawable()).getBitmap();
            saveImage(bitmap2I);
            Bitmap bitmap2D = ((BitmapDrawable)imgView_2D.getDrawable()).getBitmap();
            saveImage(bitmap2D);
            Bitmap bitmap3I = ((BitmapDrawable)imgView_3I.getDrawable()).getBitmap();
            saveImage(bitmap3I);
            Bitmap bitmap3D = ((BitmapDrawable)imgView_3D.getDrawable()).getBitmap();
            saveImage(bitmap3D);
            int comp = 0;
            for (boolean i : comp_imagenes_sacadas) {
                if (i){
                    comp++;
                }
            }
            //actualiza la base de datos y pasa a la siguiente actividad si se a completado la actividad (sacar 6 fotos)
            if (comp == 6) {
                db.activityDao().updateActivityVisitedById(2, true);
                Intent intent = new Intent(MostrarFotosBasilica.this, MenuPrincipalMapa.class);
                this.startActivity(intent);
            }
            else{
                Toast.makeText(MostrarFotosBasilica.this, "Argazki gustiak atera behar dira.", Toast.LENGTH_LONG).show();
            }
        });
        //funcionalidad de las imagebutton (abrir las camaras y poner la vista previa)
        imgView_1I.setOnClickListener(view -> {
            abrirCamara(1);

        });
        imgView_1D.setOnClickListener(view -> {
            abrirCamara(2);

        });
        imgView_2I.setOnClickListener(view -> {
            abrirCamara(3);

        });
        imgView_2D.setOnClickListener(view -> {
            abrirCamara(4);

        });
        imgView_3I.setOnClickListener(view -> {
            abrirCamara(5);

        });
        imgView_3D.setOnClickListener(view -> {
            abrirCamara(6);

        });

    }
    //Funcion que abre la camara con el codigo pasado por parametro para diferenciar quien ha abierto la camara
    private void abrirCamara(int code){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(intent, code);
        }
    }
    //al terminar de sacar la foto y aceptar volvera a esta actividad
    // se comprobara que boton abrio la camara y se le asignara la imagen sacada
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imgBitmap = (Bitmap) extras.get("data");

            //dependiendo la imagen que haga click se guarda en un imagebutton diferente
            //se añade tambien un control para saber cuales han sido modificadas y cuales no
            switch (requestCode){
                case 1:
                    comp_imagenes_sacadas[0] = true;
                    imgView_1I.setImageBitmap(imgBitmap);
                    break;
                case 2:
                    comp_imagenes_sacadas[1] = true;
                    imgView_1D.setImageBitmap(imgBitmap);
                    break;
                case 3:
                    comp_imagenes_sacadas[2] = true;
                    imgView_2I.setImageBitmap(imgBitmap);
                    break;
                case 4:
                    comp_imagenes_sacadas[3] = true;
                    imgView_2D.setImageBitmap(imgBitmap);
                    break;
                case 5:
                    comp_imagenes_sacadas[4] = true;
                    imgView_3I.setImageBitmap(imgBitmap);
                    break;
                case 6:
                    comp_imagenes_sacadas[5] = true;
                    imgView_3D.setImageBitmap(imgBitmap);
                    break;
            }
            //se muestra un "tick" verde para que el usuario sepa que a salido bien y puede continuar
            Toast toast = new Toast(this);
            ImageView view1 = new ImageView(this);
            view1.setImageResource(R.drawable.greentick);
            toast.setView(view1);
            toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
            toast.show();
        }

    }
    //Funcion que al pasarle un bitmap lo añade a la galeria como una imagen
    private void saveImage(Bitmap bitmap) {
        //coge la hora (nombre de archivo) y el directorio lo añade a la galeria
        if (android.os.Build.VERSION.SDK_INT >= 29) {
            ContentValues values = contentValues();
            values.put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/" + getString(R.string.app_name));
            values.put(MediaStore.Images.Media.IS_PENDING, true);

            Uri uri = this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            if (uri != null) {
                try {
                    saveImageToStream(bitmap, this.getContentResolver().openOutputStream(uri));
                    values.put(MediaStore.Images.Media.IS_PENDING, false);
                    this.getContentResolver().update(uri, values, null, null);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
        //crea un file para guardar la imagen para sdk inferiores a 29 (la forma en que se hacen es diferente)
        //comprueba la existencia del directorio y si no existe lo crea
        //le da el nombre segun la hora del sistema
        // y lo guarda en la galeria
        } else {
            File directory = new File(Environment.getExternalStorageDirectory().toString() + '/' + getString(R.string.app_name));

            if (!directory.exists()) {
                directory.mkdirs();
            }
            String fileName = System.currentTimeMillis() + ".png";
            File file = new File(directory, fileName);
            try {
                saveImageToStream(bitmap, new FileOutputStream(file));
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, file.getAbsolutePath());
                this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }
    //coge el tipo de imagen y la hora en segundos y los devuelve
    //funcion de apoyo a la funcion "saveImage"
    private ContentValues contentValues() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/png");
        values.put(MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis() / 1000);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());
        }
        return values;
    }
    //coge un bitmap y una corriente de salida (outputstream) y lo comprime en formato png
    //funcion de apoyo a la funcion "saveImage"
    private void saveImageToStream(Bitmap bitmap, OutputStream outputStream) {
        if (outputStream != null) {
            try {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}