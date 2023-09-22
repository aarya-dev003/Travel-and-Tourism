package com.example.bharatyatrisathi;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;


public class MapFragment extends Fragment {
    public GoogleMap mmap;
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

                mmap=googleMap;
                mmap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(@NonNull LatLng latLong) {
                        MarkerOptions markerOptions =new MarkerOptions().position(latLong).title("Clicked here");
                        mmap.addMarker(markerOptions);
                        //Geocoder class se apan map pe kahi bhi landmark chor sakte h
                        Geocoder geocoder=new Geocoder(getActivity().getApplicationContext());
                        //jese apan ne jo location dali vaha pe data store nhi h toh yeah uske aas pass ka data btta k show kar dega for example jese total 5 location h or yaha 1 set h toh vo location sabse pahle aayegi or last m 5th vali location
                        try {
                            ArrayList<Address> addressArrayList = (ArrayList<Address>) geocoder.getFromLocation(latLong.latitude, latLong.longitude, 1);
                            Log.d("ADDRESS",addressArrayList.get(0).getAddressLine(0));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
            }
        });
        return view;

    }

}