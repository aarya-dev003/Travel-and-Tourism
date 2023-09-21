package com.example.bharatyatrisathi;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_map,container,false);
        SupportMapFragment supportMapFragment=(SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mymap);
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            LatLng indorelatLng= new LatLng(22.6210, 75.8036);
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                MarkerOptions markerOptions=new MarkerOptions();
                markerOptions.position(indorelatLng);
                markerOptions.title("You");
                googleMap.clear();
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(indorelatLng,16f));
                googleMap.addMarker(markerOptions);

            }
        });
        return view;
    }
}