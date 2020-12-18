package com.example.tpintegrador;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tpintegrador.modelo.Propiedad;

import java.util.List;


public class PropiedadesRecycler extends RecyclerView.Adapter<PropiedadesRecycler.PropiedadViewHolder> {

    private List<Propiedad> propiedades;
    private AppCompatActivity activity;

    // Provide a suitable constructor (depends on the kind of dataset)
    public PropiedadesRecycler(List<Propiedad> propiedades) {
        this.propiedades = propiedades;
        //activity = act;
    }

    public PropiedadesRecycler() {}

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class PropiedadViewHolder extends RecyclerView.ViewHolder{
        // each data item is just a string in this case
        CardView card;
        TextView titulo;
        TextView precio;
        TextView capacidad;
        TextView hasInternet;
        TextView allowPets;
        TextView latitud;
        TextView longitud;
        Button btnReservar;
        Button btnVerEnMapa;

        public PropiedadViewHolder(View v){
            super(v);
            card = v.findViewById(R.id.cardSerie);
            titulo = v.findViewById(R.id.rowNombrePropiedad);
            precio = v.findViewById(R.id.rowPrecioPropiedad);
            capacidad = v.findViewById(R.id.rowCapacidadPropiedad);
            hasInternet = v.findViewById(R.id.internetInfo);
            allowPets = v.findViewById(R.id.mascotaInfo);
            latitud = v.findViewById(R.id.tvLatitud);
            longitud = v.findViewById(R.id.tvLongitud);
            btnReservar = v.findViewById(R.id.rowBtnReservar);
            btnVerEnMapa = v.findViewById(R.id.btnVerEnMapa);

            btnVerEnMapa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context ctx = v.getContext();
                    Intent i = new Intent(ctx, MapMarkerActivity.class);
                    i.putExtra("titulo", titulo.getText().toString());
                    i.putExtra("latitud", latitud.getText().toString());
                    i.putExtra("longitud", longitud.getText().toString());
                    ctx.startActivity(i);
                }
            });

            btnReservar.setOnClickListener(new View.OnClickListener() {

                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onClick(View v) {

                    NotificationChannel channel = new NotificationChannel(
                            "CANAL_NOTIFICATION_ID",
                            "Nombre Canal",
                            NotificationManager.IMPORTANCE_HIGH);

                    NotificationManager notificationManager = v.getContext().getSystemService(NotificationManager.class);
                    notificationManager.createNotificationChannel(channel);

                    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(v.getContext(), channel.getId())
                            .setSmallIcon(R.drawable.ic_launcher_foreground)
                            .setContentTitle("Reserva Realizada con Exito")
                            .setContentText("Su reserva en " + titulo.getText().toString() + " ha sido realizada con exito. Lo esperamos!");

                    notificationManager.notify(99, mBuilder.build());
                }
            });
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public PropiedadesRecycler.PropiedadViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.fila_recycler, parent, false);
        return new PropiedadViewHolder(itemView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(PropiedadViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        // configurar cada view con que fila del arreglo de datos coincide.
        Propiedad propiedad = propiedades.get(position);

        holder.titulo.setText(propiedad.getNombre());
        holder.precio.setText("$ " + propiedad.getPrecioDia().toString());
        holder.capacidad.setText("Capacidad para " + propiedad.getCapacidadPersonas().toString() + " personas.");
        holder.hasInternet.setText(propiedad.getPoseeInternet()? "SI" : "NO");
        holder.allowPets.setText(propiedad.getPermiteMascotas()? "SI" : "NO");
        holder.latitud.setText(Float.toString(propiedad.getLatitud()));
        holder.longitud.setText(Float.toString(propiedad.getLongitud()));
        /*
        holder.btnVerEnMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setAction(MainActivity.ACCION_BUSCAR_COORDENADAS);
                startActivityForResult(i, MainActivity.REQUEST_CODE_BUSCAR_MAPA);
            }
        });*/

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return propiedades.size();
    }
}
