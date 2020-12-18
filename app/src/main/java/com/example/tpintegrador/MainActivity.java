package com.example.tpintegrador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static final Integer REQUEST_CODE_BUSCAR_MAPA = 1000;
    public static final String ACCION_BUSCAR_COORDENADAS = "BUSCAR_COORDENADAS";

    Button btnBuscarAlojamiento;
    Button btnNuevoAlojamiento;
    Button btnVerMapa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnBuscarAlojamiento = findViewById(R.id.btnBuscarAlojamiento);
        btnNuevoAlojamiento = findViewById(R.id.btnNuevoAlojamiento);
        btnVerMapa = findViewById(R.id.btnVerMapa);

        btnVerMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(i1);
            }
        });
        btnBuscarAlojamiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(MainActivity.this, FormBuscarAlojamientoActivity.class);
                startActivity(i1);
            }
        });

        btnNuevoAlojamiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(MainActivity.this,FormEditarAlojamientoActivity.class);
                startActivity(i1);
            }
        });
    }
}