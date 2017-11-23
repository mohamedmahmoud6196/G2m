package dagger.module;

import com.example.m7mdmimo.g2mdx.MapsActivity;

import business.manger.GmdxLocationManger;
import business.manger.SharedManger;
import concrete.IMapConcrete;
import dagger.Module;
import dagger.Provides;
import presenter.MapPresenter;

/**
 * Created by m7md mimo on 11/21/2017.
 */
@Module
public class MapModule {
    @Provides
    IMapConcrete.IMapView provideIMapView() {
        return new MapsActivity();
    }



    @Provides
    MapPresenter provideLoginPresenter(GmdxLocationManger gmdxLocationManger, SharedManger sharedManger) {
        return new MapPresenter(gmdxLocationManger, sharedManger);
    }
}
