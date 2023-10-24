package com.example.bharatyatrisathi;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
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
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.CollectionReference;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.OnCompleteListener;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

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

                showNearbyUsers();
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
                        onLocationReceived(location.getLatitude(), location.getLongitude());
                    }
                }
            });
        } else {
            ActivityCompat.requestPermissions(fragmentActivity, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
    }

    private void onLocationReceived(double latitude, double longitude) {
        uploadLocationToFirestore(latitude, longitude);
    }

    private void uploadLocationToFirestore(double latitude, double longitude) {
        LocationData locationData = new LocationData(latitude, longitude);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Locations")
                .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
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

    private void showNearbyUsers() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference locationsRef = db.collection("Locations");

        locationsRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        double userLatitude = document.getDouble("latitude");
                        double userLongitude = document.getDouble("longitude");

                        float distance = calculateDistance(userLatitude, userLongitude);

                        if (distance < 5000) {
                            String userUid = document.getId();
                            //getUserImageUrl(userUid, userLatitude, userLongitude);
                        }
                    }
                } else {
                    Log.e("Firestore error", "Error fetching nearby users: " + task.getException());
                }
            }
        });
    }

    private void getUserImageUrl(String userUid, double latitude, double longitude) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference usersRef = db.collection("User");

        usersRef.document(userUid).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    String imageUrl = documentSnapshot.getString("image");
                    if (imageUrl != null && !imageUrl.isEmpty()) {
                        loadUserImageAndAddMarker(imageUrl, latitude, longitude);
                    }
                }
            }
        });
    }
    private void loadUserImageAndAddMarker(String imageUrl, double latitude, double longitude) {
        Picasso.get().load(imageUrl).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                BitmapDescriptor customMarkerIcon = BitmapDescriptorFactory.fromBitmap(bitmap);

                LatLng userLatLng = new LatLng(latitude, longitude);
                mMap.addMarker(new MarkerOptions()
                        .position(userLatLng)
                        .icon(customMarkerIcon));
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
            }
        });
    }


    private float calculateDistance(double userLatitude, double userLongitude) {

     return 0.0F;
    }

}

