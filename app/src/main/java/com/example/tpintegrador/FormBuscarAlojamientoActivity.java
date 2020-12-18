package com.example.tpintegrador;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.TextView;

import com.example.tpintegrador.modelo.Propiedad;

import java.util.ArrayList;
import java.util.List;

public class FormBuscarAlojamientoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private TextView buscarPorNombre;
    private Switch buscarPorMascotas;
    private CheckBox buscarPorInternet;

    private Button btnBuscarPropiedades;

    MiDBOpenHelper dbOpenHelper;

    List<Propiedad> propiedades = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_buscar_alojamiento);

        propiedades = new ArrayList<>();

        buscarPorNombre = findViewById(R.id.searchByNombre);
        buscarPorMascotas = findViewById(R.id.searchByPets);
        buscarPorInternet = findViewById(R.id.searchByInternet);

        btnBuscarPropiedades = findViewById(R.id.btnBuscarPropiedades);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        dbOpenHelper = new MiDBOpenHelper(FormBuscarAlojamientoActivity.this);

        btnBuscarPropiedades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                propiedades.clear();

                String[] arguments = {"%" + buscarPorNombre.getText().toString() + "%",
                                      buscarPorMascotas.isChecked() ? "1" : "0",
                                      buscarPorInternet.isChecked() ? "1" : "0"};

                SQLiteDatabase db = dbOpenHelper.getReadableDatabase();

                Cursor result = db.rawQuery("SELECT NOMBRE, PRECIO, CAPACIDAD, HAS_INTERNET, ALLOW_PETS, COOR_LAT, COOR_LON " +
                                                 "FROM ALOJAMIENTO " +
                                                 "WHERE NOMBRE LIKE ? " +
                                                 "AND ALLOW_PETS >= ? " +
                                                 "AND HAS_INTERNET >= ?", arguments);

                while (result.moveToNext()) {
                    propiedades.add(new Propiedad(
                            result.getString(0),
                            result.getDouble(1),
                            result.getInt(2),
                            result.getInt(3) == 1 ? true : false,
                            result.getInt(4) == 1 ? true : false,
                            result.getFloat(5),
                            result.getFloat(6)
                    ));
                    Log.d("SELECT", "Propiedad encontrada.");
                }

                myAdapter = new PropiedadesRecycler(propiedades);
                recyclerView.setAdapter(myAdapter);
            }
        });
    }

}

