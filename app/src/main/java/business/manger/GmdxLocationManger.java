package business.manger;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import javax.inject.Inject;

import presenter.MapPresenter;


/**
 * Created by mimo on 10/07/17.
 */

public class GmdxLocationManger implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {
    private Context context;
    private LocationRequest locationRequest;

    private GoogleApiClient googleApiClient;
    private Location locationChanged;
    private MapPresenter mapPresenter;

    @Inject
    public GmdxLocationManger(Context context) {
        this.context = context;
    }

    public void setMapPresenter(MapPresenter mapPresenter) {
        this.mapPresenter = mapPresenter;

    }

    public synchronized void applyGoogleApiClient() {
        googleApiClient = new GoogleApiClient.Builder(context)
                .addOnConnectionFailedListener(this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();
    }

    public void connectApiClient() {
        googleApiClient.connect();
    }

    public void disConnectApiClient() {
        googleApiClient.disconnect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        try {
            locationRequest = new LocationRequest();
            locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
            locationRequest.setInterval(1000 * 60);

            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                return;
            }
            Location location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
            mapPresenter.firstLocation(location);

            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    //Todo Here post my location to center
    @Override
    public void onLocationChanged(Location location) {


    }

    public Location getLastLocation() {
        return locationChanged;
    }


    public void setLocationChanged(Location locationChanged) {
        this.locationChanged = locationChanged;
    }
}
