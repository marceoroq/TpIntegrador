package com.example.tpintegrador;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tpintegrador.modelo.Propiedad;
import com.example.tpintegrador.modelo.TipoAlojamiento;

public class FormEditarAlojamientoActivity extends AppCompatActivity {

    TextView nombre;
    TextView descripcion;
    TextView precio;
    CheckBox hasInternet;
    Switch allowPets;
    Spinner cmbTipoAlojamiento;
    TextView capacidad;
    RatingBar calificacion;

    Button botonBuscarCoordenadas;
    Button botonGuardar;
    Button botonCancelar;

    TextView tvCoordenadas;
    MiDBOpenHelper dbOpenHelper;
    Propiedad miPropiedad;
    double latitud, longitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_alojamiento);

        miPropiedad = new Propiedad();

        nombre = findViewById(R.id.txtNombre);
        descripcion = findViewById(R.id.txtDescripcion);
        precio = findViewById(R.id.precio);
        hasInternet = findViewById(R.id.poseeInternet);
        allowPets = findViewById(R.id.permiteMascotas);
        capacidad = findViewById(R.id.capacidad);
        calificacion = findViewById(R.id.calificacion);
        // Agregamos las opciones de alojamientos almacenadas en TipoAlojamiento
        cmbTipoAlojamiento = findViewById(R.id.cmbTipoPropiedad);
        final ArrayAdapter<TipoAlojamiento> adapterTipoProp = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, TipoAlojamiento.values());
        cmbTipoAlojamiento.setAdapter(adapterTipoProp);
        tvCoordenadas = findViewById(R.id.tvCoordenadas);
        botonBuscarCoordenadas = findViewById(R.id.btnBuscarPropiedades);

        botonGuardar = findViewById(R.id.btnGuardarAlojamiento);
        botonCancelar = findViewById(R.id.btnCancelarGuardar);

        dbOpenHelper = new MiDBOpenHelper(FormEditarAlojamientoActivity.this);

        botonBuscarCoordenadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FormEditarAlojamientoActivity.this, MapsActivity.class);
                i.setAction(MainActivity.ACCION_BUSCAR_COORDENADAS);
                startActivityForResult(i, MainActivity.REQUEST_CODE_BUSCAR_MAPA);
            }
        });

        botonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chequearValoresIngresados() != "OK")
                    Toast.makeText(FormEditarAlojamientoActivity.this, chequearValoresIngresados(), Toast.LENGTH_LONG).show();
                else {
                    try {
                        miPropiedad.setNombre(nombre.getText().toString());
                        miPropiedad.setDescripcion(descripcion.getText().toString());
                        miPropiedad.setPrecioDia(Double.parseDouble(precio.getText().toString()));
                        miPropiedad.setPoseeInternet(hasInternet.isChecked());
                        miPropiedad.setPermiteMascotas(allowPets.isChecked());
                        miPropiedad.setTipoPropiedad(TipoAlojamiento.valueOf(cmbTipoAlojamiento.getItemAtPosition(cmbTipoAlojamiento.getSelectedItemPosition()).toString()));
                        miPropiedad.setCapacidadPersonas(Integer.parseInt(capacidad.getText().toString()));
                        miPropiedad.setCalificacion(calificacion.getRating());
                        AsyncTaskGuardarAlojamiento tarea = new AsyncTaskGuardarAlojamiento(miPropiedad);
                        tarea.doInBackground();
                    } catch (Exception e) {
                        Log.d("ALOJA","ERROR SETEO VALORES: " + e.toString());
                        Toast.makeText(FormEditarAlojamientoActivity.this, "Los datos ingresados no son correctos, por favor revise esten OK y completos y vuelva a intentar.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        botonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    protected String chequearValoresIngresados() {
        if (nombre.getText().length() < 5)
            return "Nombre debe contener minimo 5 caracteres";
        else if (capacidad.getText().length() < 1)
            return "Debe ingresar la capacidad";
        else if (Integer.parseInt(capacidad.getText().toString()) > 15 ||
                   Integer.parseInt(capacidad.getText().toString()) < 1)
            return "La capacidad no puede ser mayor a 15 o menor a 1";
        else
            return "OK";
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if(requestCode == MainActivity.REQUEST_CODE_BUSCAR_MAPA) {
                latitud = data.getDoubleExtra("latitud", 0.00);
                longitud = data.getDoubleExtra("longitud", 0.00);
                tvCoordenadas.setText("Seleccionó LAT: " + latitud + ", LON: " + longitud);
                miPropiedad.setLatitud((long) latitud);
                miPropiedad.setLongitud((long) longitud);
            }
        }
    }

    public class AsyncTaskGuardarAlojamiento extends AsyncTask<Propiedad, Integer, Long>{

        private Propiedad prop;

        AsyncTaskGuardarAlojamiento(Propiedad propiedad) {
            prop = propiedad;
        }

        @Override
        protected Long doInBackground(Propiedad... propiedad) {
            // Gets the data repository in write mode
            SQLiteDatabase db = dbOpenHelper.getWritableDatabase();

            // Create a new map of values, where column names are the keys
            ContentValues values = new ContentValues();
            values.put("NOMBRE", prop.getNombre());
            values.put("DESCRIPCION", prop.getDescripcion());
            values.put("PRECIO", prop.getPrecioDia());
            values.put("HAS_INTERNET", prop.getPoseeInternet());
            values.put("ALLOW_PETS", prop.getPermiteMascotas());
            values.put("TIPO", prop.getTipoPropiedad());
            values.put("CAPACIDAD", prop.getCapacidadPersonas());
            values.put("CALIFICACION", prop.getCalificacion());
            values.put("COOR_LAT", prop.getLatitud());
            values.put("COOR_LON", prop.getLongitud());

            try {
                long newRowId = db.insert("ALOJAMIENTO", "NOMBRE", values);
                Toast.makeText(FormEditarAlojamientoActivity.this, "Alojamiento guardado con exito!", Toast.LENGTH_LONG).show();
                //finish();
                return null;
            } catch (Exception e) {
                Log.d("ALOJA","ERROR INSERT: " + e.toString());
                Toast.makeText(FormEditarAlojamientoActivity.this, "Error al guardar, por favor revise los datos ingresados e intente nuevamente.", Toast.LENGTH_LONG).show();
                return null;
            }
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