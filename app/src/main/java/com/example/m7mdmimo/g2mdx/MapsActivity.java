package com.example.m7mdmimo.g2mdx;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.OnClick;
import concrete.IMapConcrete;
import dagger.android.AndroidInjection;
import presenter.MapPresenter;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, IMapConcrete.IMapView {

    private static final int PERMISSIONS_REQUEST_GPS = 12;
    private GoogleMap mMap;
    @Inject
    MapPresenter mapPresenter;
    private GoogleApiClient googleApiClient;
    private String[] permissions = new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        AndroidInjection.inject(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(permissions[0]) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(permissions, PERMISSIONS_REQUEST_GPS);
        }
        mapPresenter.setUp(this);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mapPresenter.getCurrentLocation();
    }

    @OnClick
    public void gpsLocator(View v) {
        mapPresenter.addLocation();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void showAllLocations(ArrayList<Location> locationArrayList) {
        for (int i = 0; i < locationArrayList.size(); i++) {
            addMarker(locationArrayList.get(i).getLatitude(), locationArrayList.get(i).getLongitude());
        }
    }

    private void addMarker(double lat, double lang) {
        mMap.addMarker(new MarkerOptions().position(new LatLng(lat, lang)));

    }

    @Override
    public void showMyLocation(double lat, double lang) {
        LatLng latLng = new LatLng(lat, lang);
        mMap.addMarker(new MarkerOptions().position(latLng));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14.0f));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSIONS_REQUEST_GPS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mapPresenter.setUp(this);
                mapPresenter.getCurrentLocation();
            } else {
                Toast.makeText(this, "Until you grant the permission, we canot detect location", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
