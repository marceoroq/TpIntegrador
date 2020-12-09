package com.example.tpintegrador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tpintegrador.modelo.Propiedad;

import java.util.List;


public class PropiedadesRecycler extends RecyclerView.Adapter<PropiedadesRecycler.PropiedadViewHolder> {
    private List<Propiedad> mDataset;
    private AppCompatActivity activity;
    // Provide a suitable constructor (depends on the kind of dataset)
    public PropiedadesRecycler(List<Propiedad> myDataset, AppCompatActivity act) {
        mDataset = myDataset;
        activity = act;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class PropiedadViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        CardView card;
        TextView titulo;
        Button btnReservar;

        public PropiedadViewHolder(View v){
            super(v);
            card = v.findViewById(R.id.cardSerie);
            titulo = v.findViewById(R.id.rowNombrePropiedad);
            btnReservar = v.findViewById(R.id.rowBtnReservar);

            btnReservar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /// mandar un mensaje para resrevar

                }
            });
        }
    }


    // Create new views (invoked by the layout manager)
    @Override
    public PropiedadesRecycler.PropiedadViewHolder onCreateViewHolder(ViewGroup parent,
                                                                   int viewType) {

        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fila_recycler, parent, false);
        //...
        PropiedadViewHolder  vh = new PropiedadViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(PropiedadViewHolder serieHolder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        // configurar cada view con que fila del arreglo de datos coincide.

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
