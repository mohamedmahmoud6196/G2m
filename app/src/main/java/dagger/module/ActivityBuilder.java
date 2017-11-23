package dagger.module;

import android.app.Activity;

import com.example.m7mdmimo.g2mdx.HomeActivity;
import com.example.m7mdmimo.g2mdx.LoginActivity;
import com.example.m7mdmimo.g2mdx.MapsActivity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;
import dagger.subcomponent.HomeComponent;
import dagger.subcomponent.LoginComponent;
import dagger.subcomponent.MapComponent;

/**
 * Created by m7md mimo on 11/21/2017.
 */
@Module(subcomponents = HomeComponent.class)
public abstract class ActivityBuilder {

    @Binds
    @IntoMap
    @ActivityKey(LoginActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindLoginActivity
            (LoginComponent.Builder builder);

    @Binds
    @IntoMap
    @ActivityKey(HomeActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindHomeActivity
            (HomeComponent.Builder builder);

    @Binds
    @IntoMap
    @ActivityKey(MapsActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindMapActivity
            (MapComponent.Builder builder);


}