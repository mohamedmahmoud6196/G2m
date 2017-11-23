package presenter;

import android.location.Location;

import com.facebook.login.LoginManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import javax.inject.Inject;

import business.manger.GmdxLocationManger;
import business.manger.SharedManger;
import concrete.IMapConcrete;

import static business.GmdxUtil.KEY_LOCATIONS;
import static business.GmdxUtil.KEY_REMEMBER;
import static business.GmdxUtil.USER_REMEMBER;

/**
 * Created by m7md mimo on 11/23/2017.
 */

public class MapPresenter implements IMapConcrete.IMapPresnter {
    private SharedManger sharedManger;
    private GmdxLocationManger gmdxLocationManger;
    private IMapConcrete.IMapView iMapView;

    @Inject
    public MapPresenter(GmdxLocationManger gmdxLocationManger, SharedManger sharedManger) {
        this.sharedManger = sharedManger;
        this.gmdxLocationManger = gmdxLocationManger;
        gmdxLocationManger.setMapPresenter(this);
    }

    @Override
    public void setUp(IMapConcrete.IMapView iMapView) {
        gmdxLocationManger.applyGoogleApiClient();
        gmdxLocationManger.connectApiClient();
        this.iMapView = iMapView;
    }

    @Override
    public void getAllLocations() {
        Gson gson = new Gson();
        String location_list_json = sharedManger.getLocations(KEY_LOCATIONS);
        TypeToken<ArrayList<Location>> token = new TypeToken<ArrayList<Location>>() {
        };
        ArrayList<Location> locationArrayList = gson.fromJson(location_list_json, token.getType());
        iMapView.showAllLocations(locationArrayList);
    }

    public boolean isRembembered() {
        int remembered = sharedManger.getData(KEY_REMEMBER);
        if (remembered == USER_REMEMBER)
            return true;
        else
            return false;
    }

    @Override
    public void removeView() {
        iMapView = null;
    }

    @Override
    public void signOut() {
        LoginManager.getInstance().logOut();
        sharedManger.removeRememberData();
    }

    @Override
    public void addLocation() {
        Gson gson = new Gson();
        Location location = gmdxLocationManger.getLastLocation();
        String location_list_json = sharedManger.getLocations(KEY_LOCATIONS);
        TypeToken<ArrayList<Location>> token = new TypeToken<ArrayList<Location>>() {
        };
        ArrayList<Location> locationArrayList = gson.fromJson(location_list_json, token.getType());
        locationArrayList.add(location);
        location_list_json = gson.toJson(locationArrayList);
        sharedManger.putData(KEY_LOCATIONS, location_list_json);
    }

    @Override
    public void firstLocation(Location location) {
        ArrayList<Location> locationArrayList = new ArrayList<>();
        locationArrayList.add(location);

        Gson gson = new Gson();
        String location_list_json = gson.toJson(locationArrayList);
        sharedManger.putData(KEY_LOCATIONS, location_list_json);
        double lat = location.getLatitude();
        double lang = location.getLongitude();
        iMapView.showMyLocation(lat, lang);
    }

    @Override
    public void getCurrentLocation() {
        gmdxLocationManger.getLastLocation();
    }
}
