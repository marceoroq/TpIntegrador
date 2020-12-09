package com.example.tpintegrador;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tpintegrador.modelo.Propiedad;

public class FormEditarAlojamientoActivity extends AppCompatActivity {

    Button botonBuscarCoordenadas;
    Button botonGuardar;
    MiDBOpenHelper dbOpenHelper;
    Propiedad miPropiedad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_alojamiento);
        botonBuscarCoordenadas = findViewById(R.id.btnBuscarCoordenadas);
        botonGuardar = findViewById(R.id.btnGuardarAlojamiento);
        dbOpenHelper = new MiDBOpenHelper(FormEditarAlojamientoActivity.this);

        botonBuscarCoordenadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FormEditarAlojamientoActivity.this,MapsActivity.class);
                i.setAction(MainActivity.ACCION_BUSCAR_COORDENADAS);
                startActivityForResult(i,MainActivity.REQUEST_CODE_BUSCAR_MAPA);
            }
        });

        botonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AsyncTaskGuardarAlojamiento tarea = new AsyncTaskGuardarAlojamiento();
                tarea.doInBackground(miPropiedad);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RESULT_OK){
            if(requestCode==MainActivity.REQUEST_CODE_BUSCAR_MAPA) {
                // setear las coordenadas
            }
        }
    }

    public class AsyncTaskGuardarAlojamiento  extends AsyncTask<Propiedad, Integer, Long>{

        @Override
        protected Long doInBackground(Propiedad... propiedads) {
            SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
            // ejecutar el insert
            return null;
        }

        @Override
        protected void onPreExecute() {

            // actualizar la interface ANTES de guardar
        }

        @Override
        protected void onPostExecute(Long aLong) {

            // actualizar la interface DESPUES  de guardar

        }

    }
}