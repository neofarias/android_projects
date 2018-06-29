package com.example.marciosantos.livraria;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import entidades.Maps;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Maps maps = new Maps();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent intent = getIntent();
        this.maps = intent.getParcelableExtra("EntidadeMaps");
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

        // Add a marker in Sydney and move the camera
        LatLng livraria = new LatLng(maps.getLatitude(), maps.getLongitude());
        if(this.maps.getId() == 1){
            mMap.addMarker(new MarkerOptions().position(livraria).title("Livraria Saraiva - Praia de Belas"));
        } else if(this.maps.getId() == 2){
            mMap.addMarker(new MarkerOptions().position(livraria).title("Livraria Cultura - Bourbon Country"));
        } else if(this.maps.getId() == 3){
            mMap.addMarker(new MarkerOptions().position(livraria).title("Livraria Cameron"));
        } else if(this.maps.getId() == 4){
            mMap.addMarker(new MarkerOptions().position(livraria).title("Livraria Siciliano"));
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(livraria, 15f));
        mMap.getUiSettings().setZoomControlsEnabled(true);

    }
}
