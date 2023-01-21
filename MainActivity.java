package com.example.cajadetexto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button boton;
    TextView entrada,salida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        entrada=findViewById(R.id.text_intro);
        salida=findViewById(R.id.text_expone);
        boton=findViewById(R.id.btn_accion);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String texto=entrada.getText().toString().trim();
                salida.setText(texto);
            }
        });

    }
}