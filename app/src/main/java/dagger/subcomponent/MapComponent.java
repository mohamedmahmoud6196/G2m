package dagger.subcomponent;

import com.example.m7mdmimo.g2mdx.MapsActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent()
public interface MapComponent extends AndroidInjector<MapsActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MapsActivity> {
    }
}
