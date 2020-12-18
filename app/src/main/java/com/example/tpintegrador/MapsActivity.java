package com.example.tpintegrador;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Boolean buscarCoordenadas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Log.d("MAPAS"," CREA ACTIVIDAD: "+ getIntent().getAction());
        if (getIntent().getAction() != null) {
            buscarCoordenadas = getIntent().getAction().equals(MainActivity.ACCION_BUSCAR_COORDENADAS);
            Log.d("MAPAS", " buscarCoordenadas: " + buscarCoordenadas);
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Log.d("MAPAS","cargar masp");
        final String[] permisos = new String[1];
        permisos[0]= Manifest.permission.ACCESS_FINE_LOCATION;
        if((Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)){
            Log.d("MAPAS","VERSION APROPIADA");
            if (ContextCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                Log.d("MAPAS","NO TENGO PERMISO");
                if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                    Log.d("MAPAS"," Muestro explicacon");
                    AlertDialog.Builder builder = new AlertDialog.Builder(MapsActivity.this);
                    builder.setTitle("NECESITO LOS PERMISOS !!!!").setMessage("Otorgar permisos")
                            .setPositiveButton("Doy Permiso!",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            Log.d("MAPAS"," NO TENGO PERMISO lo solicito 1");
                                            ActivityCompat.requestPermissions(MapsActivity.this, permisos, 999);
                                        }
                                    })
                            .setNegativeButton("No!", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                    builder.create().show();
                } else { // no tengo que mostrar explicacion
                    Log.d("MAPAS"," NO TENGO PERMISO lo solicito 2");
                    ActivityCompat.requestPermissions(MapsActivity.this, permisos, 999);
                }
            } else {
                // tengo el permiso no tengo que chequear ni pedir.
                Toast.makeText(MapsActivity.this,"YA TENGO EL PERMISO",Toast.LENGTH_LONG).show();
                Log.d("MAPAS","YA TENGO PERMISO");
                configurarMapa();
            }
        }

        //
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        final String[] permisos = new String[1];
        permisos[0]= Manifest.permission.ACCESS_FINE_LOCATION;
        if(requestCode==999){
            if(permissions[0].equals(Manifest.permission.ACCESS_FINE_LOCATION) && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(MapsActivity.this,"Me dio el permiso",Toast.LENGTH_LONG).show();
                configurarMapa();
            } else{
                Toast.makeText(MapsActivity.this,"NOOOO Me dio el permiso",Toast.LENGTH_LONG).show();
            }
        }
    }

    @SuppressLint("MissingPermission")
    private void configurarMapa(){
        Log.d("MAPAS"," configurarMapa");

        mMap.setMyLocationEnabled(true);

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                // primero verifico que abrimos el mapa para buscar coordenadas
                Log.d("MAPAS"," onMapLongClick: "+ buscarCoordenadas);

                if(buscarCoordenadas){
                    // capturar la coordenada actual
                    // enviar el resultado a la actividad de buscar para que sepa las coordenadas seleccionadas
                    Intent returnIntent = new Intent();

                    returnIntent.putExtra("latitud", latLng.latitude);
                    returnIntent.putExtra("longitud", latLng.longitude);

                    setResult(MapsActivity.RESULT_OK, returnIntent);

                    finish();
                }
            }
        });
    }
}