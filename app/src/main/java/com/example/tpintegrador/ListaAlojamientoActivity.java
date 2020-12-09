package com.example.tpintegrador;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.tpintegrador.modelo.Propiedad;

import java.util.ArrayList;
import java.util.List;

public class ListaAlojamientoActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alojamiento);

        recyclerView = (RecyclerView) findViewById(R.id.listaPropiedadesRecycler);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        List<Propiedad> listaPropiedades = new ArrayList<>();
        // specify an adapter (see also next example)
        mAdapter = new PropiedadesRecycler(listaPropiedades,this);
        recyclerView.setAdapter(mAdapter);
    }
}