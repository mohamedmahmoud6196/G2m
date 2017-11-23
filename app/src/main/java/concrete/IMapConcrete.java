package concrete;

import android.location.Location;

import java.util.ArrayList;

/**
 * Created by m7md mimo on 11/21/2017.
 */

public interface IMapConcrete {
    interface IMapPresnter {
        void setUp(IMapView iMapView);

        void getAllLocations();

        void addLocation();

        void firstLocation(Location location);

        void getCurrentLocation();

        void signOut();

        void removeView();
    }

    interface IMapView {
        void showAllLocations(ArrayList<Location> locationArrayList);

        void showMyLocation(double lat, double lang);

    }
}
