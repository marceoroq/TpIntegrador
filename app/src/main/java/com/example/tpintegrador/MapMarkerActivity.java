package com.example.tpintegrador;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapMarkerActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap nMap;
    private float latitud, longitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_marker);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapMarker);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        nMap = googleMap;

        latitud = Float.parseFloat(getIntent().getExtras().get("latitud").toString());
        longitud = Float.parseFloat(getIntent().getExtras().get("longitud").toString());
        LatLng testing = new LatLng(latitud, longitud);

        String titulo = getIntent().getExtras().getString("titulo");
        Marker marker = nMap.addMarker(new MarkerOptions().position(testing).title(titulo));

        marker.showInfoWindow();
        nMap.moveCamera(CameraUpdateFactory.newLatLngZoom(testing, 10));
    }
}
