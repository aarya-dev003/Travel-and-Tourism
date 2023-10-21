package com.example.bharatyatrisathi;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class MapFragment extends Fragment {
    private GoogleMap mMap;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private FragmentActivity fragmentActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        fragmentActivity = getActivity();
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(fragmentActivity);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mymap);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;

                getLastLocation();
            }
        });

        return view;
    }

    public interface LocationCallback {
        void onLocationReceived(double latitude, double longitude);
    }

    private void getLastLocation() {
        if (ContextCompat.checkSelfPermission(fragmentActivity, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(fragmentActivity, new OnSuccessListener<android.location.Location>() {
                @Override
                public void onSuccess(android.location.Location location) {
                    if (location != null) {
                        // Invoke the callback with the location data
                        uploadLocationToFirestore(location.getLatitude(), location.getLongitude());
                    }
                }
            });
        } else {
            ActivityCompat.requestPermissions(fragmentActivity, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
    }

    private void uploadLocationToFirestore(double latitude, double longitude) {
        LocationData locationData = new LocationData(latitude, longitude);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Locations")
                .document(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid())
                .set(locationData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("Firestore", "Location data uploaded.");

                        // Add a marker for your location
                        LatLng userLatLng = new LatLng(latitude, longitude);
                        mMap.addMarker(new MarkerOptions().position(userLatLng).title("You are here"));

                        // Set the camera to your location with a desired zoom level
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLatLng, 16f));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Firestore error", "Error uploading location data: " + e.getMessage());
                    }
                });
    }
}
