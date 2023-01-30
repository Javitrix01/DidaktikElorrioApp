package com.elorrieta.didaktikelorrioapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class InfoDelMenu extends AppCompatActivity {

    /**
     * Layout que se utiliza para cuando se elige una actividad de la lista lateral
     * y se muestra la informacion de esa actividad.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_del_menu);
    }
}