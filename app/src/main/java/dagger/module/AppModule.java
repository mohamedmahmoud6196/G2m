package dagger.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import business.manger.ContactManger;
import business.manger.FaceBookUserManger;
import business.manger.GmdxLocationManger;
import dagger.Module;
import dagger.Provides;
import dagger.subcomponent.HomeComponent;
import dagger.subcomponent.LoginComponent;
import dagger.subcomponent.MapComponent;

/**
 * Created by m7md mimo on 11/21/2017.
 */
@Module(subcomponents = {LoginComponent.class, HomeComponent.class, MapComponent.class})
public class AppModule {
    private Context context;

    @Provides
    Context provideContext(Application application) {
        context = application;
        return context;
    }

    @Provides
    FaceBookUserManger provideFaceBookUserManger() {
        return new FaceBookUserManger(context);
    }

    @Provides
    ContactManger provideContactManger() {
        return new ContactManger(context);
    }
    @Provides
    GmdxLocationManger provideLocationManger() {
        return new GmdxLocationManger(context);
    }
    @Provides
    SharedPreferences getSharedPreferences() {
        return context.getSharedPreferences("G2mdx", Context.MODE_PRIVATE);

    }
}
