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
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;

import android.util.Log;
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
import java.util.Random;

public class MostrarFotosBasilica extends AppCompatActivity {

    /**
     * Pantalla en la que tienen que sacar varias fotos de la basilica,
     * cuando las sacan todas tienen un boton para pasar a la siguiente actividad.
     */

    Button btnGuardar;
    ImageButton imgView_1I;
    ImageButton imgView_1D;
    ImageButton imgView_2I;
    ImageButton imgView_2D;
    ImageButton imgView_3I;
    ImageButton imgView_3D;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppDatabase db = AppDatabase.getDatabase(getApplicationContext());

        String[] permissionsStorage = {WRITE_EXTERNAL_STORAGE};
        int requestExternalStorage = 1;
        int permission = ActivityCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, permissionsStorage, requestExternalStorage);
        }
        getSupportActionBar().hide();
        setContentView(R.layout.activity_mostrar_fotos_basilika);
        btnGuardar = findViewById(R.id.GuardarBtn);
        imgView_1I = findViewById(R.id.imageButton1I);
        imgView_1D = findViewById(R.id.imageButton1D);
        imgView_2I = findViewById(R.id.imageButton2I);
        imgView_2D = findViewById(R.id.imageButton2D);
        imgView_3I = findViewById(R.id.imageButton3I);
        imgView_3D = findViewById(R.id.imageButton3D);
        imgView_1I.setClickable(false);

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
            db.activityDao().updateActivityVisitedById(2,true);
            Intent intent = new Intent(MostrarFotosBasilica.this,MenuPrincipalMapa.class);
            this.startActivity(intent);
        });
        imgView_1I.setOnClickListener(view -> {
            abrirCamara(1);
            Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
        });
        imgView_1D.setOnClickListener(view -> {
            abrirCamara(2);
            Toast.makeText(this, "2", Toast.LENGTH_SHORT).show();
        });
        imgView_2I.setOnClickListener(view -> {
            abrirCamara(3);
            Toast.makeText(this, "3", Toast.LENGTH_SHORT).show();
        });
        imgView_2D.setOnClickListener(view -> {
            abrirCamara(4);
            Toast.makeText(this, "4", Toast.LENGTH_SHORT).show();
        });
        imgView_3I.setOnClickListener(view -> {
            abrirCamara(5);
            Toast.makeText(this, "5", Toast.LENGTH_SHORT).show();
        });
        imgView_3D.setOnClickListener(view -> {
            abrirCamara(6);
            Toast.makeText(this, "6", Toast.LENGTH_SHORT).show();
        });

    }

    private void abrirCamara(int code){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(intent, code);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imgBitmap = (Bitmap) extras.get("data");

            switch (requestCode){
                case 1:imgView_1I.setImageBitmap(imgBitmap);
                    break;
                case 2:imgView_1D.setImageBitmap(imgBitmap);
                    break;
                case 3:imgView_2I.setImageBitmap(imgBitmap);
                    break;
                case 4:imgView_2D.setImageBitmap(imgBitmap);
                    break;
                case 5:imgView_3I.setImageBitmap(imgBitmap);
                    break;
                case 6:imgView_3D.setImageBitmap(imgBitmap);
                    break;
            }
            Toast toast = new Toast(this);
            ImageView view1 = new ImageView(this);
            view1.setImageResource(R.drawable.greentick);
            toast.setView(view1);
            toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
            toast.show();
        }

    }
    private void saveImage(Bitmap bitmap) {
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

    private ContentValues contentValues() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/png");
        values.put(MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis() / 1000);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());
        }
        return values;
    }

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